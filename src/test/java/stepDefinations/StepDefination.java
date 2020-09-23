package stepDefinations;

import static io.restassured.RestAssured.given;

import Resources.APIResources;
import Resources.TestDataBuild;
import Resources.Utils;

import static org.junit.Assert.*;
import io.cucumber.java.en.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class StepDefination extends Utils
{
	//Declare Response/Request SpecBuilder globally
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild(); // All setters method to add value are stored in separate file
	static String placeID; //since placeID is going to be used for 2 times, we need to set it as static to hold its values for all test case. 
						   //placeID will be shared for all test scenario


	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws  Throwable
	{


		res = given().spec(requestSpecification()).body(data.addPlacePayLoad(name,language,address));
	}

	@When("user calls {string} APi with {string} http request")
	public void user_calls_a_pi_with_http_request(String resource, String method) throws Throwable 
	{
		APIResources resourceAPI =  APIResources.valueOf(resource); //passing value to enum to select resource
		//this means, constructor will be called of enum class (i.e. APIResources.java) with value of resource which you pass
		System.out.println(resourceAPI.getResource());

		resspec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();


		if(method.equalsIgnoreCase("POST"))
			response = res.when().post(resourceAPI.getResource());  // get value of resource from enum class
		    //then().spec(resspec).log().all().extract().response();
		else if(method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());
	}

	@Then("^the API call is success with status code 200$")
	public void the_api_call_is_success_with_status_code_200() throws Throwable 
	{
		assertEquals(response.getStatusCode(),200);    	
		/*
		 * 		
		 */
	}

	@And("^\"([^\"]*)\" in Response body is \"([^\"]*)\"$")
	public void something_in_response_body_is_something(String keyValue, String Expectedvalue) throws Throwable 
	{
		
		assertEquals(getJsonPath(response,keyValue),Expectedvalue);
	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws Throwable 
	{
		//request Spec
		placeID =getJsonPath(response,"place_id");
		
		res = given().spec(requestSpecification()).queryParam("place_id", placeID);
		user_calls_a_pi_with_http_request(resource,"GET");
		String nameExtracted = getJsonPath(response,"name");
		System.out.println("***********");
		System.out.println(nameExtracted);
		System.out.println("***********");
		assertEquals(nameExtracted,expectedName);
		
		
	}
	

		@Given("DeletePlace Payload")
		public void delete_place_payload() throws Exception 
		{
			res = given().spec(requestSpecification()).body(data.deletePlacePayload(placeID));
		}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
