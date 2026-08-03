package myTest;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


public class ReqResTest {
    private static final String URL = "https://reqres.in/";
    private static final String TOKEN = "free_user_3GD8cAdhMqbLuV8P0aeh42KAvjQ";

    @Test
    public void checkUsers() {

        List<UserData> users = given()
                .header("content-type", "application/json")
                .header("x-api-key", TOKEN)
                .when()
                .get(URL + "api/users?page=2")
                .then()
                .statusCode(200)
                .log().all()
                .body("data.id", everyItem(instanceOf(Integer.class)))
                .body("data.email", everyItem(instanceOf(String.class)))
                .extract()
                .jsonPath()
                .getList("data", UserData.class);

        // Проверки
        for (UserData user : users) {
            assertThat(user.getAvatar())
                    .isNotNull()
                    .contains(String.valueOf(user.getId()));
        }
    }

    @Test
    public void checkUsers1() {
        // 1. Получаем ответ и сохраняем jsonPath
        var jsonPath = given()
                .header("content-type", "application/json")
                .header("x-api-key", TOKEN)
                .when()
                .get(URL + "api/users?page=2")
                .then()
                .statusCode(200)
                .log().all()
                .body("data.id", everyItem(instanceOf(Integer.class)))
                .body("data.email", everyItem(instanceOf(String.class)))
                .body("data.size()", greaterThan(0))
                .extract()
                .jsonPath();

        // 2. Извлекаем список как List<Map>
        List<Map<String, Object>> users = jsonPath.getList("data");

        // 3. Проверки
        assertNotNull(users);
        assertFalse(users.isEmpty());

        for (Map<String, Object> user : users) {
            int id = (int) user.get("id");
            String avatar = (String) user.get("avatar");
            String email = (String) user.get("email");
            String firstName = (String) user.get("first_name");
            String lastName = (String) user.get("last_name");

            // Проверки
            assertTrue(id > 0, "ID должен быть положительным");
            assertNotNull(email, "Email не должен быть null");
            assertTrue(email.contains("@"), "Email должен содержать @");
            assertNotNull(avatar, "Avatar не должен быть null");
            assertTrue(avatar.contains(String.valueOf(id)),
                    "Avatar пользователя id=" + id + " должен содержать его id. Avatar: " + avatar);

            System.out.printf("✅ id=%d, name=%s %s, email=%s%n",
                    id, firstName, lastName, email);
        }
    }
}
