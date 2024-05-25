package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditCardsPage {
    private SelenideElement header = $("[data-test-id='dashboard']");
    private SelenideElement updatePageButton = $("[data-test-id='action-reload']");
    public CreditCardsPage () {
        header.shouldBe(visible);
    }

    public SelenideElement cardInfo (String hiddenCardNumber) {
        return $(withText(hiddenCardNumber));
    }

    public TransferMoneyPage openTransferCardPage (String hiddenCardNumber) {
        cardInfo(hiddenCardNumber).find("button").click();
        return new TransferMoneyPage();
    }

    public String getCardforNumberList(int number) {
        return $$("ul.list li div").get(number - 1).text().substring(0,19);
    }

    public int getCardBalance (String hiddenCardNumber) {
        String cardText = cardInfo(hiddenCardNumber).text();
        return Integer.parseInt(cardText.split(" ")[5]);
    }

    public TransferMoneyPage updateTransferPage () {
        updatePageButton.click();
        return new TransferMoneyPage();
    }

}
