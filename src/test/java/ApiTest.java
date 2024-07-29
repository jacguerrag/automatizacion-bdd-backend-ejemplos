import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;


public class ApiTest {
    @Test
    public void testGetUser(){
        System.out.println("Verificación de Código de Estado y Estructura de la Respuesta");
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", notNullValue())
                .body("email", matchesPattern("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"));
    }
    @Test
    public void testPostCreation(){
        System.out.println("Validación de Contenido y Valores");
        given()
                .contentType("application/json")
                .body("{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }")
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"))
                .body("userId", equalTo(1));
    }
    @Test
    public void testGetPosts(){
        System.out.println("Validación de Listas y Campos Condicionales");
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("userId", everyItem(greaterThan(0)))
                .body("title", hasItem(containsString("qui")));
    }

}
