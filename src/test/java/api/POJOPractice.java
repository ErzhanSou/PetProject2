package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.CashwiseAuthorization;
import utilities.Config;

public class POJOPractice {

    @Test
    public void createCategory(){
        String url = Config.getProperty("cashWiseApiUrl") + "/api/myaccount/categories";
        String token = CashwiseAuthorization.getToken();
        RequestBody requestBody = new RequestBody();
        requestBody.setCategory_title("transportation");
        requestBody.setCategory_description("uber rides");
        requestBody.setFlag(true);

        Response response = RestAssured.given()
                .auth()
                .oauth2(token)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(201, statusCode);
    }

    @Test
    public void testCustom() throws JsonProcessingException {
        String url = Config.getProperty("cashWiseApiUrl") + "/api/myaccount/categories";
        String token = CashwiseAuthorization.getToken();
        RequestBody requestBody = new RequestBody();
        requestBody.setCategory_title("education");
        requestBody.setCategory_description("Udemy.com");
        requestBody.setFlag(true);

        Response response = RestAssured.given()
                .auth()
                .oauth2(token)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(201, statusCode);
        response.prettyPrint();

        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        String expectedTitle = customResponse.getCategory_title();
        String expectedCategory_description = customResponse.getCategory_description();
        Assert.assertEquals(expectedTitle, "education");
        Assert.assertEquals(expectedCategory_description, "Udemy.com");
    }


    @Test
    public void createGetSeller() throws JsonProcessingException {
        String url = Config.getProperty("cashWiseApiUrl") + "/api/myaccount/sellers";
        String token = CashwiseAuthorization.getToken();

        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name("Tesla");
        requestBody.setSeller_name("Elon Musk1");
        requestBody.setPhone_number("+13129099999");
        requestBody.setEmail("elon3@gmail.com");
        requestBody.setAddress("18888 Dev Ave");

        Response response = RestAssured.given()
                .auth()
                .oauth2(token)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);
        response.prettyPrint();

        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
        int id = customResponse.getSeller_id();

        String urlGetSingleSeller = Config.getProperty("cashWiseApiUrl") + "/api/myaccount/sellers/" + id;
        Response response1 = RestAssured.given()
                .auth()
                .oauth2(token)
                .get(urlGetSingleSeller);
        int statusCode = response1.statusCode();
        Assert.assertEquals(200, statusCode);
        String expectedSellerName = customResponse.getSeller_name();
        String expectedCompanyName = customResponse.getCompany_name();
        Assert.assertEquals(expectedSellerName, "Elon Musk1");
        Assert.assertEquals(expectedCompanyName, "Tesla");

    }
}
