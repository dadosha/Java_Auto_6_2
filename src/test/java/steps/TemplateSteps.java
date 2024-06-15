package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import pages.CreditCardsPage;
import pages.LogInPage;
import pages.TransferMoneyPage;
import pages.VerificationCodePage;

import static data.DataGenerator.hiddenCard;


public class TemplateSteps {
    private static LogInPage loginPage;
    private static VerificationCodePage verificationCodePage;
    private static CreditCardsPage creditCardsPage;
    private static TransferMoneyPage transferMoneyPage;

    @Пусть("открыта страница с формой авторизации {string}")
    public void openAuthPage(String url) {
        loginPage = Selenide.open(url, LogInPage.class);
    }

    @Когда("пользователь пытается авторизоваться с именем {string} и паролем {string}")
    public void loginWithNameAndPassword(String username, String password) {
        verificationCodePage = loginPage.validLogin(username, password);
    }

    @И("пользователь пытается ввести код подтверждения {string}")
    public void verificationCodeInput(String code) {
        creditCardsPage = verificationCodePage.validCodeEnter(code);
    }

    @Когда("пользователь переводит {int} рублей с карты с номером {string} на свою {int} карту с главной страницы")
    public void verifyCardPage(int amount, String cardFromNumber, int numberListCardTo) {
        String cardToNumber = creditCardsPage.getCardforNumberList(numberListCardTo);
        String hiddenToCard = hiddenCard(cardToNumber);
        var transferPage = creditCardsPage.openTransferCardPage(hiddenToCard);
        creditCardsPage = transferPage.successTransferMoney(amount, cardFromNumber);
    }

    @Тогда("баланс его {int} карты из списка на главной странице должен стать {int} рублей")
    public void checkBalance(int numberListCardTo, int cardBalance) {
        String cardToNumber = creditCardsPage.getCardforNumberList(numberListCardTo);
        String hiddenToCard = hiddenCard(cardToNumber);
        creditCardsPage.checkNewBalanceCard(hiddenToCard, cardBalance);
    }
}
