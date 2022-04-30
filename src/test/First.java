
package test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class First extends Token {
	@Test(groups= {"regression"})
	   public void testCase1() {
	      System.out.println("in test case 1" +id);
	   }

	  
	   @Test(groups= {"regression","apr_release"})
	   public void testCase2() {
	      System.out.println("in test case 2" + id);
	   }
	   @Test(groups= {"apr_release"})
	   public void testCase3() {
	      System.out.println("in test case 3" +id);
	   }

	  
	   @Test(groups= {"apr_release"})
	   public void testCase4() {
	      System.out.println("in test case 4" + id);
	   }

	  
	   @AfterMethod
	   public void afterMethod() {
	      System.out.println("in afterMethod");
	   }

	   @BeforeClass
	   public void beforeClass() {
	      System.out.println("in beforeClass");
	   }

	   @AfterClass
	   public void afterClass() {
	      System.out.println("in afterClass");
	   }

	   @BeforeTest
	   public void beforeTest() {
	      System.out.println("in beforeTest");
	   }

	   @AfterTest
	   public void afterTest() {
	      System.out.println("in afterTest");
	   }

	   @BeforeSuite
	   public void beforeSuite() {
	      System.out.println("in beforeSuite");
	   }

	   @AfterSuite
	   public void afterSuite() {
	      System.out.println("in afterSuite");
	   }

}
