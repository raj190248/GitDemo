package Resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild 
{
	
	public AddPlace addPlacePayLoad(String name, String language, String address)
	{
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("98765544321");
		p.setWebsite("https://www.google.com");
		p.setName(name);
		
		List<String> myList = new ArrayList<String>(); //we need to first pass values to List Object before using settermethod
		myList.add("shop");
		myList.add("shoe type");
		
		p.setTypes(myList); //pass the List<> object to set its value.
		
		Location l = new Location(); //initailizing nested json/java class to access its getter and setters
		l.setLat(-36.9999);
		l.setLng(-32.8888);
		
		p.setLocation(l); // pass the nested json/java class's object to set Location
		
		return p;
	}
	
	public String deletePlacePayload(String placeID)
	{
		return "{\r\n    \"place_id\":\""+placeID+"\"   \t \t\r\n}";
	}

}

















