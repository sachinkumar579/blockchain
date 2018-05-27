package com.test.blockchain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Blockchain app
 *
 */
@SpringBootApplication
public class BlockchainApp 
{

    public static void main( String[] args )
    {       
      try{
    	  SpringApplication.run(BlockchainApp.class, args);
      }
      catch(Exception exp) {
    	  exp.printStackTrace();
      }
    	
    }
    
}
