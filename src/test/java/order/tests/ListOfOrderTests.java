package order.tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import kz.yandex.order.OrderSteps;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.apache.http.HttpStatus.*;

public class ListOfOrderTests extends BaseTest {

    private final OrderSteps orderSteps = new OrderSteps();


    @Test
    @DisplayName("Получение списка заказов")
    @Description("Проверка, что в тело ответа возвращается список заказов.")
    public void getOrderListTest() {
        ValidatableResponse responseCreate = orderSteps.getOrderList();

        int actualStatusCodeCreate = responseCreate.extract().statusCode();
        List<HashMap> orderBody = responseCreate.extract().path("orders");

        Assert.assertEquals(SC_OK, actualStatusCodeCreate);
        Assert.assertNotNull(orderBody);
    }
}
