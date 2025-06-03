package kz.yandex.courier;

import static io.restassured.RestAssured.given;
import static kz.yandex.constants.ApiConstants.COURIER;
import static kz.yandex.constants.ApiConstants.LOGIN;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class CourierSteps {

    /**
     * Метод создания курьера
     * Отправка POST-запрос на API COURIER с указанием заголовка и тела запроса (данные курьера)
     */
    @Step("Создание курьера")
    public static Response createCourier(CourierModel courier) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(COURIER);
    }

    /**
     * Метод для авторизации курьера
     * Отправка POST-запрос на API LOGIN
     */
    @Step("Авторизация курьера")
    public static Response loginCourier(CourierModel courier) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(LOGIN);
    }

    /**
     * Метод по удалению курьера по его id
     * Отправка DELETE-запрос на API COURIER с указанием идентификатора курьера
     */
    @Step("Удаление курьера")
    public static void deleteCourier(String courierId) {
        given()
                .delete(COURIER + "{courierId}", courierId);
    }

}
