package api.utilities;

public class Routes {

    //User Module
    public static String BASE_URL = "https://gorest.co.in/public/v2";
    public static String USER_POST_URL = BASE_URL + "/users";
    public static String USER_GET_URL = BASE_URL + "/users/{id}";
    public static String USER_PUT_URL = BASE_URL + "/users/{id}";
    public static String USER_DELETE_URL = BASE_URL + "/users/{id}";

    //Posts Module
    public static String POSTS_GET_URL = BASE_URL + "/users/{id}/posts";
    public static String POSTS_POST_URL = BASE_URL + "/users/{id}/posts";

    public static String TOKEN = "a2237e1c4b3aa486d4fd300b50772809c5a7918384760a84d7a960227663443e";

}
