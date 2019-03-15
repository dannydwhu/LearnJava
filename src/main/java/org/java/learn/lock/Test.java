package org.java.learn.lock;

public class Test {

    public static void main(String[] args){
        Service service = new Service();
        
        for(int i=0; i< 50; i++){
            new Thread(new Runnable(){

                @Override
                public void run() {
                    service.secKill();
                }
                
            }).start();
        }
    }
}
