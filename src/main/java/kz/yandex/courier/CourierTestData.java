package kz.yandex.courier;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@Getter
@AllArgsConstructor
public class CourierTestData {
    // Существующий логин курьера
    private final String existingLogin;
    // Несуществующий логин курьера
    private final String nonExistentLogin;
    // Существующий пароль курьера
    private final String existingPassword;
    // Несуществующий пароль курьера
    private final String nonExistentPassword;
    // Имя курьера
    private final String firstName;

    /**
     * Конструктор класса, инициализирующий рандомные тестовые данные
     */
    public CourierTestData() {
        this.existingLogin = randomLoginOrPass(9);
        this.nonExistentLogin = randomLoginOrPass(9) + "kek";
        this.existingPassword = "existPass";
        this.nonExistentPassword = randomLoginOrPass(8);
        this.firstName = "firstName";
    }

    /**
     * Метод для генерации случайного логина или пароля заданной длины.
     */
    public static String randomLoginOrPass (int length) {
        int leftLimit = 97;   // Код символа 'a' в таблице ASCII
        int rightLimit = 122; // Код символа 'z' в таблице ASCII
        Random random = new Random(); // Экземпляр класса Random, для генерации случайных чисел
        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}