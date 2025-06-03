package kz.yandex.constants;

/**
 * Содержит константы проекта
 */
public class ApiConstants {

    // Базовый URL для Яндекс.Самоката
    public static final String SCOOTER_URL = "https://qa-scooter.praktikum-services.ru/";

    // API для работы с курьерами
    public static final String COURIER = "api/v1/courier/";

    // API для авторизации курьера
    public static final String LOGIN = "api/v1/courier/login/";

    // API для работы с заказами
    public static final String ORDER = "api/v1/orders";

    // API для отмены заказа
    public static final String CANCEL_ORDER = "/api/v1/orders/cancel?track=";
}
