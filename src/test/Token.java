package test;


import org.testng.annotations.BeforeSuite;

public class Token {
	String id;
	 @BeforeSuite()
	   public void beforeSuite() {
		 id="Nimish";
	      System.out.println("in beforesuite in token class");
	   }

}
