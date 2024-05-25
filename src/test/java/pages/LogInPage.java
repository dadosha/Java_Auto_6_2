package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LogInPage {
    private SelenideElement usernameField = $("[data-test-id='login'] input");
    private SelenideElement passwordField = $("[data-test-id='password'] input");
    private SelenideElement acceptButton = $("[data-test-id='action-login']");

    public VerificationCodePage validLogin(String username, String password) {
        usernameField.setValue(username);
        passwordField.setValue(password);
        acceptButton.click();

        return new VerificationCodePage();
    }
}
