package test;

import org.testng.annotations.BeforeMethod;

public class Token {
	String id;
	 @BeforeMethod(groups= {"regression"})
	   public void beforeMethod() {
		 id="Nimish";
	      System.out.println("in beforeMethod");
	   }

}
