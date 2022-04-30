package test;

import org.testng.annotations.Test;

public class DependentTest extends Token{
	   @Test(dependsOnMethods= {"testCase2"})
	   public void testCase1() {
	      System.out.println("in test case 1 " +id);
	   }

	  
	   @Test
	   public void testCase2() {
	      System.out.println("in test case 2 " + id);
	   }
	   @Test(dependsOnMethods= {"testCase1"})
	   public void testCase3() {
	      System.out.println("in test case 3 " +id);
	   }

	  
	   @Test
	   public void testCase4() {
	      System.out.println("in test case 4 " + id);
	   }

}
