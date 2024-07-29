import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

public class ComplexApiTest {

    @Test
    public void testGetUsers() {
        System.out.println("Validación de Listas y Objetos Anidados I");
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("name", hasItems("Leanne Graham", "Ervin Howell"))
                .body("address.city", hasItem("Gwenborough"))
                .body("address.findAll { it.city == 'Gwenborough' }.street", hasItems("Kulas Light"));
    }

    @Test
    public void testGetUserDetails() {
        System.out.println("Validación de Listas y Objetos Anidados II");
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body("name", equalTo("Leanne Graham"))
                .body("address", allOf(
                        hasKey("street"),
                        hasEntry("street", "Kulas Light"),
                        hasKey("suite"),
                        hasKey("city"),
                        hasKey("zipcode"),
                        hasKey("geo")
                ))
                .body("address.geo", allOf(
                        hasKey("lat"),
                        hasKey("lng")
                ));
    }
    @Test
    public void testComplexResponse() {
        System.out.println("Validación de Respuestas Complejas con JsonPath y Hamcrest");
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("findAll { it.userId == 1 }.size()", greaterThan(0))
                .body("findAll { it.userId == 1 }.title", hasItems(
                        "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                        "qui est esse",
                        "ea molestias quasi exercitationem repellat qui ipsa sit aut"
                ));
    }
    @Test
    public void testMultipleAssertions() {
        System.out.println("Validación de Respuestas con Aserciones Múltiples");
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
                .when()
                .get("/todos/1")
                .then()
                .statusCode(200)
                .body("userId", equalTo(1))
                .body("id", equalTo(1))
                .body("title", equalTo("delectus aut autem"))
                .body("completed", is(false));
    }
    @Test
    public void testCombinedMatchers() {
        System.out.println("Combinación de Matchers con anyOf y allOf");
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("title", anyOf(
                        containsString("sunt aut facere"),
                        containsString("qui est esse")
                ))
                .body("userId", is(1))
                .body("body", allOf(
                        containsString("quia"),
                        containsString("non"),
                        containsString("est")
                ));
    }

}
