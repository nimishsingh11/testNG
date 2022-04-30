package intro;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DocTypeRules {
	public String idtoken;
	String baseUrl = "https://api-qa.digilytics.solutions";
	String companyId = "627";
	Integer docType = 1826;
	List<Integer> ruleIds = new ArrayList<Integer>();

	@DataProvider(name = "loginData")
	public Object[][] createLoginTestData() {
		return new Object[][] { { "nimish.singh@digilytics.ai", "Nimish1610" } };
	}

	@Test(dataProvider = "loginData", priority = 1)
	public void loginUser(String email, String password) {
		// Map<String,String> data=new HashMap<>();
		JSONObject data = new JSONObject();
		data.put("email", email);
		data.put("password", password);
		Response res = given().contentType("application/json").body(data.toString()).when()
				.post(baseUrl + "/company-management/api/v1/auth/login").then()
				// .statusCode(401)
				.log().body().extract().response();
		String jsonString = res.asString();
		JsonPath j = new JsonPath(jsonString);
		String status = j.getString("meta.code");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(status, "200");
		String id = j.getString("data.idToken");
		idtoken = "Bearer-" + id;
		softAssert.assertAll();
	}
	@DataProvider(name = "ruleData")
	public Object[][] createRuleTestData() {
	//	return new Object[][] { {1,1},{2,2},{2,6},{2,7},{3,2} };
		return new Object[][] { {1,1} };
	}
	@Test(dataProvider = "ruleData",priority = 2)
	public void saveDocRule(Integer businessRuleId,Integer ruleId ) {
		JSONObject data = new JSONObject();
		data.put("applicantTypes", new String[] { "PRIMARY", "SECONDARY" ,"COMMON" });
		data.put("businessRuleId", businessRuleId);
		data.put("documentTypeId", docType);
		data.put("id", "0");
		data.put("ruleId", ruleId);
		data.put("errorMessage", "failed");
		data.put("successMessage", "success");
		data.put("value", 2);
		Response res = given().contentType("application/json").header("Authorization", idtoken).body(data.toString())
				.when().post(baseUrl + "/oculyse/api/v1/" + companyId + "/rules/document-rule/save").then().log().body()
				.extract().response();
		String jsonString = res.asString();
		JsonPath j = new JsonPath(jsonString);
		for (int i = 0; i < j.getInt("data.size()"); i++) {
			ruleIds.add(j.getInt("data[" + i + "].id"));
		}
		
	//	List<String> idss=j.get("data.id");
		 for(int i=0;i<ruleIds.size();i++) {
	            System.out.println(ruleIds.get(i));
	        }

		

	}

	@Test(priority = 3)
	public void ruleList() {
		Response res = given().contentType("application/json").header("Authorization", idtoken).when()
				.get(baseUrl + "/oculyse/api/v1/" + companyId + "/rules/document-rule/" + docType + "/list").then()
				.log().body().extract().response();
		String jsonString = res.asString();
		JsonPath j = new JsonPath(jsonString);
		String status = j.getString("meta.code");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(status, "200");
	
		List<Integer> ruleList = new ArrayList<Integer>();
		for (int i = 0; i < j.getInt("data.size()"); i++) {
			ruleList.add(j.getInt("data[" + i + "].id"));
		}
		System.out.println(ruleList);
		System.out.println(ruleIds);
		for (int i = 0; i < ruleIds.size(); i++) {
			System.out.println(ruleList.contains(ruleIds.get(i)));
			softAssert.assertEquals(ruleList.contains(ruleIds.get(i)), "true");
		}
		softAssert.assertAll();

	}
}