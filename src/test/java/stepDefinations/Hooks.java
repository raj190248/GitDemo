package stepDefinations;

import io.cucumber.java.Before;

public class Hooks 
{
	@Before("@DeletePlace")
	public void BeforeScenario() throws Throwable
	{
		//we are telling here that execute this code only if placeId is null
		
		StepDefination sd = new StepDefination();
		
		if(StepDefination.placeID==null)  //to call static variable you need to call using class name and not its object
		{
		sd.add_place_payload_with("Saurii", "French", "India");
		sd.user_calls_a_pi_with_http_request("AddPlaceAPI", "POST");
		sd.verify_place_id_created_maps_to_using("Saurii", "getPlaceAPI");
		}
	}

}
