package org.java.learn.nio.codec;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessageV3;
import org.apache.commons.lang3.ClassUtils;
import org.java.learn.nio.model.BaseObject;

import java.lang.reflect.*;

public class ObjectProtobufCodec implements ObjectCodec<ProtobufCodecObject> {

    private static ObjectProtobufCodec instance = new ObjectProtobufCodec();
    public static ObjectProtobufCodec getInstance(){
        return instance;
    }

    @Override
    public byte[] encode(ProtobufCodecObject object) {
        return object.toProto().toByteArray();
    }

    @Override
    public <P extends ProtobufCodecObject> P decode(Class<P> objectClass, byte[] bytes) {
        return this.decode(objectClass, null, bytes);
    }

    public <P extends ProtobufCodecObject> P decode(Class<P> objectClass, Class pbClass, byte[] bytes) {
        if(!BaseObject.class.isAssignableFrom(objectClass)){
            throw new RuntimeException("BaseObject should be super class, but got " + objectClass.getName());
        }

        try{
            System.out.println("object codec decode " + objectClass.getName() );
            P object = (P) newInstance(objectClass);
            System.out.println("object  " + object );

            if(pbClass == null){
                pbClass = find(object, BaseObject.class, "T");
            }

            System.out.println("find pbClass  " + pbClass.getName() );
            GeneratedMessageV3 pbMessage = parseFrom(pbClass, bytes);
            object.initFromProto(pbMessage);

            System.out.println("codec completed  "  );
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.err.println("error happen  "  );
        return null;
    }

    private GeneratedMessageV3 parseFrom(Class pbClass, byte[] bytes) {
        try {
            Method method = pbClass.getMethod("parseFrom", bytes.getClass());
            return (GeneratedMessageV3) method.invoke(null, bytes);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    private static Class<?> find(
            final Object object, Class<?> parameterizedSuperclass, String typeParamName) {

        final Class<?> thisClass = object.getClass();
        Class<?> currentClass = thisClass;
        for (; ; ) {
            if (currentClass.getSuperclass() == parameterizedSuperclass) {
                int typeParamIndex = -1;
                TypeVariable<?>[] typeParams = currentClass.getSuperclass().getTypeParameters();
                for (int i = 0; i < typeParams.length; i++) {
                    if (typeParamName.equals(typeParams[i].getName())) {
                        typeParamIndex = i;
                        break;
                    }
                }

                if (typeParamIndex < 0) {
                    throw new IllegalStateException(
                            "unknown type parameter '" + typeParamName + "': " + parameterizedSuperclass);
                }

                Type genericSuperType = currentClass.getGenericSuperclass();
                if (!(genericSuperType instanceof ParameterizedType)) {
                    return Object.class;
                }

                Type[] actualTypeParams = ((ParameterizedType) genericSuperType).getActualTypeArguments();

                Type actualTypeParam = actualTypeParams[typeParamIndex];
                if (actualTypeParam instanceof ParameterizedType) {
                    actualTypeParam = ((ParameterizedType) actualTypeParam).getRawType();
                }
                if (actualTypeParam instanceof Class) {
                    return (Class<?>) actualTypeParam;
                }
                if (actualTypeParam instanceof GenericArrayType) {
                    Type componentType = ((GenericArrayType) actualTypeParam).getGenericComponentType();
                    if (componentType instanceof ParameterizedType) {
                        componentType = ((ParameterizedType) componentType).getRawType();
                    }
                    if (componentType instanceof Class) {
                        return Array.newInstance((Class<?>) componentType, 0).getClass();
                    }
                }
                if (actualTypeParam instanceof TypeVariable) {
                    // Resolved type parameter points to another type parameter.
                    TypeVariable<?> v = (TypeVariable<?>) actualTypeParam;
                    currentClass = thisClass;
                    if (!(v.getGenericDeclaration() instanceof Class)) {
                        return Object.class;
                    }

                    parameterizedSuperclass = (Class<?>) v.getGenericDeclaration();
                    typeParamName = v.getName();
                    if (parameterizedSuperclass.isAssignableFrom(thisClass)) {
                        continue;
                    } else {
                        return Object.class;
                    }
                }

                return fail(thisClass, typeParamName);
            }
            currentClass = currentClass.getSuperclass();
            if (currentClass == null) {
                return fail(thisClass, typeParamName);
            }
        }
    }

    private static Class<?> fail(Class<?> type, String typeParamName) {
        throw new IllegalStateException(
                "cannot determine the type of the type parameter '" + typeParamName + "': " + type);
    }

    public static Object newInstance(Class objectClass) {
        try {
            return objectClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
