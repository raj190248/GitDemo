Feature: Validation Place API's
	@AddPlace @Regression
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
     When user calls "AddPlaceAPI" APi with "post" http request
     Then the API call is success with status code 200
      And "status" in Response body is "OK"
      And "scope" in Response body is "APP"
      And verify place_id created maps to "<name>" using "getPlaceAPI"
  
    Examples: 
      | name | language | address             | 
      | Raj  | English  | World Cross Centerr |
      | Saurabh  | Marathi  | Thane |
      
 @DeletePlace @Regression  	
Scenario: To verify if Delete place funcionality is working

Given DeletePlace Payload
When user calls "deletePlaceAPI" APi with "post" http request
Then the API call is success with status code 200
And "status" in Response body is "OK"