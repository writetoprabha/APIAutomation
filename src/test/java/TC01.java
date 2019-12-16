import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class TC01 {
    @Test
    public void callGetRequest(){

        //Setting the base URI for the get request (without the parameters)
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

        //Request specification
        RequestSpecification httpRequest = RestAssured.given();

        // httpRequest.request(Method.GET); will return the response and is assigned to the Response object.
        // The second parameter of httpRequest.request method is the query string to be sent in the get call
        Response response = httpRequest.request(Method.GET, "/Hyderabad");

        System.out.println(response.getBody().asString());
    }
}
