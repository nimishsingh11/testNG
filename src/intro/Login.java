package intro;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Login {
	public String idtoken;
	 String baseUrl="https://api-qa.digilytics.solutions";
	 
	@DataProvider(name="loginData")
	public Object[][] createLoginTestData(){
		return new Object[][] {
			{"nimish.singh@digilytics.ai","Nimish1610"}
			//{"nimish.singh@digilytics.ai",""},
		};
		}
	
	
		@Test(dataProvider="loginData")
		public void loginUser(String email,String password) {
			HashMap<String, String> data=new HashMap<String, String>();
			data.put("email", email);
			data.put("password", password);
			Response res=given()	
						.contentType("application/json")
						.body(data)
						.when()
						.post(baseUrl+"/company-management/api/v1/auth/login")
						.then()
						//.statusCode(401)
						.log().body()
						.extract()
						.response()	;
			String jsonString=res.asString();
			JsonPath j = new JsonPath(jsonString);
			String status=j.getString("meta.code");
			SoftAssert softAssert=new SoftAssert();
			softAssert.assertEquals(status, "200");
			 String id=j.getString("data.idToken");
			 idtoken= "Bearer-"+id;
			System.out.println(idtoken);
			softAssert.assertAll();	
		}
		
}
	
