package intro;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class A {
	String idtoken;
	@BeforeTest
	public void loginUser() {
		HashMap<String, String> data=new HashMap<String, String>();
		data.put("email", "nimish.singh@digilytics.ai");
		data.put("password", "Nimish1610");
		Response res=given()	
					.contentType("application/json")
					.body(data)
					.when()
					.post("https://api-qa.digilytics.solutions/company-management/api/v1/auth/login")
					.then()
					.log().body()
					.extract()
					.response()	;
		String jsonString=res.asString();
		JsonPath j = new JsonPath(jsonString);
		 String id=j.getString("data.idToken");
		  idtoken= "Bearer-"+id;
		//	System.out.println(idtoken);

}
	
	@Test
	public void abc() {
		System.out.println(idtoken);
		/*Header authorization = new Header("Authorization", idtoken);
		Header acceptencoding = new Header("Accept-Encoding", "gzip, deflate, br");
		Headers headers = new Headers(authorization, acceptencoding);*/
		Response res=given()	
				.contentType("application/json")
				//.headers(headers)
				.header("Authorization", idtoken)
				.when()
				.get("https://api-qa.digilytics.solutions/oculyse/api/v1/627/rules/document-rule/6713/list")
				.then()
				.log().body()
				.extract()
				.response()	;
	}
}
