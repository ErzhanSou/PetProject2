package API;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import utilities.CashwiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ApiPractice {

    @Test
    public void TestEmail() {
        String token = CashwiseAuthorization.getToken();
        String url = Config.getProperty("cashWiseApiUrl") + "/api/myaccount/sellers";

        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 10);

        Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);
        String email = response.jsonPath().getString("responses[0].email");
        String email2 = response.jsonPath().getString("responses[1].email");
        String email3 = response.jsonPath().getString("responses[2].email");
        
        int status = response.statusCode();
        Assert.assertEquals(200, status);
        Assert.assertTrue(email.endsWith(".com"));
        Assert.assertFalse(email2.isEmpty());
        Assert.assertTrue(email3.endsWith(".com"));
    }
    
    @Test
    public void improvedAllSellers() {
        String token = CashwiseAuthorization.getToken();
        String url = Config.getProperty("cashWiseApiUrl") + "/api/myaccount/sellers";

        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 10);

        Response response = RestAssured.given().auth().oauth2(token).params(params).get(url);
        int status = response.statusCode();
        Assert.assertEquals(200, status);
        int size = response.jsonPath().getList("responses").size();
        for (int i = 0; i < size; i++) {
            String email = response.jsonPath().getString("responses[" + i + "].email");
            Assert.assertFalse(email.isEmpty());
        }
    }

    @Test
    public void GetAllBankAccounts() {
        String token = CashwiseAuthorization.getToken();
        String url = Config.getProperty("cashWiseApiUrl") + "/api/myaccount/bankaccount";
        Response response = RestAssured.given().auth().oauth2(token).get(url);
        int status = response.statusCode();
        Assert.assertEquals(200, status);
        response.prettyPrint();

    }

    @Test
    public void VerifyAllBankAccounts() {
        String token = CashwiseAuthorization.getToken();
        String url = Config.getProperty("cashWiseApiUrl") + "/api/myaccount/bankaccount";
        Response response = RestAssured.given().auth().oauth2(token).get(url);
        int status = response.statusCode();
        Assert.assertEquals(200, status);
        int size = response.jsonPath().getList("JSON").size();

        String expectedBankAccountName = "Bank of America";
        boolean isPresent = false;

        for (int i = 0; i < size; i++) {
            String bankAccountName = response.jsonPath().getString("bank_account_name[" + i + "]");

            if (bankAccountName.equalsIgnoreCase(expectedBankAccountName)) {
                isPresent = true;
                break;
            }

        }
        Assert.assertTrue(isPresent);


    }

    @Test
    public void createBankAccount() {
        String token = CashwiseAuthorization.getToken();
        String url = Config.getProperty("cashWiseApiUrl") + "/api/myaccount/bankaccount";

        RequestBody requestBody = new RequestBody();
        requestBody.setType_of_pay("CASH");
        requestBody.setBank_account_name("Bank of America");
        requestBody.setDescription("Checking bank account");
        requestBody.setBalance(700);

        Response response = RestAssured.given().auth().oauth2(token).contentType(ContentType.JSON)
                .body(requestBody).post(url);

        int status = response.statusCode();
        Assert.assertEquals(201, status);
        response.prettyPrint();


    }

}
