package utilities;

public class CashwiseAuthorization {
    public static String getToken() {
        String url = Config.getProperty("cashWiseApiUrl") + "/api/myaccount/auth/login";


    }
}
