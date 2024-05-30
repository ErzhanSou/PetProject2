package api;

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
}
