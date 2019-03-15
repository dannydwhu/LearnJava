package org.java.learn.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class SocketChannelDemo {

    private Selector selector = null;
    
    public SocketChannelDemo(int port) throws Exception{
        
        selector = Selector.open();
        
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().setReuseAddress(true);
        ssc.socket().bind(new InetSocketAddress(port));
        
        ssc.configureBlocking(false);
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        
        for(;;){
            while(selector.select() > 0){
                
                Iterator it = selector.selectedKeys().iterator();
                
                while(it.hasNext()){
                    SelectionKey key = null;
                    key = (SelectionKey) it.next();
                    try{
                        if(key.isAcceptable()){
                            ServerSocketChannel sscc = (ServerSocketChannel) key.channel();
                            SocketChannel sc = sscc.accept();
                            System.out.println("ip: " + sc.socket().getRemoteSocketAddress() + " port: " + sc.socket().getLocalPort());
                            
                            sc.configureBlocking(false);
                            ByteBuffer buf = ByteBuffer.allocate(2);
                            sc.register(selector, SelectionKey.OP_READ, buf);
                            
                        }
                        if(key.isReadable()){
                            reveive(key);
                        }
                        if(key.isWritable()){
                            send(key);
                        }
                    } catch(Exception e){
                        
                    }
                    
                }
                
            }
        }
        
    }

    int y = 0;
    private void send(SelectionKey key) throws IOException {
        ByteBuffer buf = (ByteBuffer) key.attachment();
        SocketChannel sc = (SocketChannel) key.channel();
        
        buf.wrap(new String("我是服务器").getBytes());
        sc.write(buf);
        
        System.out.println("count: " + y++);
    }

    private void reveive(SelectionKey key) throws IOException {
        // TODO Auto-generated method stub
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buf = ByteBuffer.allocate(2);
        int byteRead = sc.read(buf);
        
        StringBuffer sb = new StringBuffer();
        
        while(byteRead > 0){
            buf.flip();
            
            while(buf.hasRemaining()){
//                buf.get(new byte[buf.limit()]);
//                sb.append(new String( buf.array() ) );
                sb.append(buf.get());
            }
            buf.clear();
            byteRead = sc.read(buf);
        }
        System.out.println("get: " + sb.toString());
    }
    
    public static void main(String[] args) throws Exception{
        SocketChannelDemo demo = new SocketChannelDemo(8120);
    }
}
