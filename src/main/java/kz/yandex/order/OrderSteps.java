package kz.yandex.order;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static kz.yandex.constants.ApiConstants.CANCEL_ORDER;
import static kz.yandex.constants.ApiConstants.ORDER;

public class OrderSteps extends OrderClient {

    /**
     * Выполняет POST-запрос на ORDER и возвращает Response
     */
    @Step("Создание заказа")
    public static Response createOrder(OrderModel order) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(order)
                .when()
                .post(ORDER);
    }

    /**
     * Возвращает объект ValidatableResponse для валидации ответа
     */
    @Step("Получение списка заказов")
    public ValidatableResponse getOrderList() {
        return given()
                .spec(getSpec())
                .when()
                .get(ORDER)
                .then();
    }

    /**
     * Метод для отмены заказа, принимает идентификатор заказа и выполняет PUT-запрос
     * Выполняет PUT-запрос с идентификатором заказа
     */
    @Step("Отмена заказа")
    public static void cancelOrder(String track) {
        given()
                .put(CANCEL_ORDER + "{track}", track);
    }

}
