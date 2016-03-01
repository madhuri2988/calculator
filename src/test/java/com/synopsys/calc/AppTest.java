package com.synopsys.calc;

import java.util.HashMap;

import static org.junit.Assert.*;

import org.junit.Test;

 

public class AppTest {

   @Test
   public void test() {

      App app = new App();

  
      //positive test cases
      assertEquals(app.parseExpression("add(1,2)",new HashMap<String,Integer>()), 3);
      assertEquals(app.parseExpression("add(1, mult(2, 3))",new HashMap<String,Integer>()), 7);
      assertEquals(app.parseExpression("mult(add(2, 2), div(9, 3))",new HashMap<String,Integer>()), 12);
      assertEquals(app.parseExpression("let(a, 5, add(a, a))",new HashMap<String,Integer>()), 10);
      assertEquals(app.parseExpression("let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b)))",new HashMap<String,Integer>()), 40);
   
      
   }

}
