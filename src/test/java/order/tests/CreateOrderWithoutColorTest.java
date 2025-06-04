package order.tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import kz.yandex.order.OrderModel;
import kz.yandex.order.OrderSteps;
import org.junit.After;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static kz.yandex.order.OrderTestData.*;
import static org.hamcrest.CoreMatchers.*;

public class CreateOrderWithoutColorTest extends BaseTest {

    private String track;

    @Test
    @DisplayName("Создание заказа без указания параметра color")
    @Description("Заказ можно создать, если не указать параметр color")
    public void createOrderWithoutColorTest() {
        OrderModel order = new OrderModel(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATA, COMMENT);
        Response response = OrderSteps.createOrder(order);

        track = response.then().extract().path("track").toString();
        response.then()
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .assertThat()
                .body("track", notNullValue());
    }

    /**
     * Метод для отмены заказа после создания
     */
    @After
    public void cancelOrder() {
        OrderSteps.cancelOrder(track);
    }
}
