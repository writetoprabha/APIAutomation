import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.XLUtility;

import java.io.IOException;

public class TC01 {
    @Test(enabled = false)
    public void getCityWeather(){

        //Setting the base URI for the get request (without the parameters)
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

        //Request specification
        RequestSpecification httpRequest = RestAssured.given();

        // httpRequest.request(Method.GET); will return the response and is assigned to the Response object.
        // The second parameter of httpRequest.request method is the query string to be sent in the get call
        Response response = httpRequest.request(Method.GET, "/Hyderabad");

        System.out.println(response.getBody().asString());

        //Asserting that the status code is 200 for the request
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
    }

    @Test(enabled = true, dataProvider = "customerData")
    public void postRegisterCustomer(String firstName, String lastName, String userName, String password, String emailId) {
        RestAssured.baseURI = "http://restapi.demoqa.com/customer";

        RequestSpecification httpRequest = RestAssured.given();

        JSONObject requestPayload = new JSONObject();
        requestPayload.put("FirstName", firstName);
        requestPayload.put("LastName", lastName);
        requestPayload.put("UserName", userName);
        requestPayload.put("Password", password);
        requestPayload.put("Email", emailId);

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestPayload.toJSONString());

        Response response = httpRequest.request(Method.POST, "/register");
        System.out.println(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200);

        Assert.assertEquals(response.jsonPath().get("fault"), "FAULT_USER_ALREADY_EXISTS");
    }

    @Test(enabled = false)
    public void getCheckForAuthentication() {
        RestAssured.baseURI = "http://restapi.demoqa.com/authentication/checkForAuthentication";

        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("ToolsQA");
        authScheme.setPassword("TestPassword");

        RestAssured.authentication = authScheme;

        RequestSpecification httpRequest = RestAssured.given();
        Response response  = httpRequest.request(Method.GET, "/");

        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @DataProvider(name = "customerData")
    public Object[][] retrieveCustomerDataFromExcel() throws IOException {

        String xlFilePath = System.getProperty("user.dir") + "/src/test/data/CustomerDetails.xls";
        int rowCount = XLUtility.getRowCount(xlFilePath, "Sheet1");
        System.out.println(rowCount);
        int columnCount = XLUtility.getCellCount(xlFilePath, "Sheet1", 1);
        System.out.println(columnCount);

        String customerData[][] = new String [rowCount][columnCount];
        for (int currentRow = 1; currentRow <= rowCount; currentRow++){
            for(int currentColumn = 0; currentColumn < columnCount; currentColumn++){
                customerData[currentRow-1][currentColumn] = XLUtility.getCellData(xlFilePath, "Sheet1", currentRow, currentColumn);
            }
        }
        return (customerData);
    }
}
