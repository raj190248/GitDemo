package Resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils 
{
	public static RequestSpecification req;

	public RequestSpecification requestSpecification() throws Exception
	{
		if(req==null)
		{
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			req = new RequestSpecBuilder()
					.setBaseUri(getGlobalValue("baseUrl"))
					.addQueryParam("key", "qaclick123")
					.setContentType(ContentType.JSON)
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.build();

			return req;
		}
		return req;
	}

	public String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\RAJ\\Downloads\\APIFramework\\src\\test\\java\\Resources\\global.properties");
		prop.load(fis); // .load() will tell prop where the file(property file) is.
		return prop.getProperty(key);


	}
	
	public String getJsonPath(Response response, String key)
	{
		String resp = response.asString();
		JsonPath js  = new JsonPath(resp);
		return js.get(key).toString();
	}

}















