import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
// URL TEST BASE : https://jsonplaceholder.typicode.com
// PATH : /posts/1
// URL TEST : https://jsonplaceholder.typicode.com/posts/1

public class Test1 {
    @Test
    public void testGetPost(){
        // URL BASE
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // send GET REQUEST and validate RESPONSE
        given().when().get("/posts/1").then().statusCode(200)
                .body("userId", equalTo(1))
                .body("id", equalTo(1))
                .body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
    }

}
