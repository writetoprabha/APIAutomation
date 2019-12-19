<h1>API Automation</h1>
<h4>Dependencies used:</h4>
<li>RestAssured api - for invoking webservices and validate responses</li>
<li>Apachi POI api - for data driven testing</li>
<li>Json simple api - for parsing the json responses</li>
<li>TestNG - to execute tests</li> <br>

<h4> To invoke a REST service with Java:</h4>
<ol>
<li>Set the baseURI property of the RESTASSURED static class<br> Example: <br/>RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";</li>  
<li>Create the RequestSpecification object and assign it with RestAssured.Given() method. <br>Example:<br/> RequestSpecification httpRequest = ResetAssured.Given();</li>
<li>Create the Response object and assign it with requestSpecificationObject.request() method <br/>Example: <br/> Response response = httpRequest.Request(METHOD.GET, "/Hyderabad"); <br>Note: "/Hyderabad" is the parameter passed in GET request </li>
<li>The Response of the request invoked can be accessed using the response object <br> Example: <br> System.out.println(response.getResponseBody()); </li>
</ol>

Notes: <br>
<li>For POST requests, we need to add the payload and header as below: <br>
<hr>
        JSONObject requestPayload = new JSONObject(); <br/>
        requestPayload.put("FirstName", firstName); <br/>
        requestPayload.put("LastName", lastName); <br/>
        requestPayload.put("UserName", userName); <br/>
        requestPayload.put("Password", password); <br/>
        requestPayload.put("Email", emailId); <br/> <br/>
        httpRequest.header("Content-Type", "application/json"); <br/>
        httpRequest.body(requestPayload.toJSONString()); <br/>
<hr>
</li> 



