package org.java.learn.arithmetic.rpn;


import org.java.learn.arithmetic.rpn.exception.NotSupportCommandException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * RPN calculator user interface main interface
 * @author Danny Hu
 * @date 2019/03/09
 */
public class RpnMain {
    
    private static boolean exit = false;
    
    public static void main(String[] args) throws Exception{

        // monitor Ctrl+C event
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("bye");
                exit = true;
            }
        });
        
        System.out.println("Please input RPN expression...\nAnd also can be stopped by ctrl+c");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        while((userInput = br.readLine()) != null){
            if(exit){
                // Ctrl+C will trigger program exit
                System.exit(-1);
            }
            try{
                RpnCalculator rpn = new RpnCalculator();
                BigDecimal result = rpn.calcResult(userInput);
                if(null != result){
                    System.out.println("Result: " + new BigDecimal(result.toString()).setScale(10, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString());
                }
            } catch (NotSupportCommandException e) {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
