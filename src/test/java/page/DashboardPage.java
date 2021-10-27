package page;

import com.codeborne.selenide.*;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

//    public Dashboard() {
//    }

    public int getOneCardBalance() {
        val text = cards.first().text();
//        val oneCard = cards.get(1).$("[data-test-id=92df3f1c-a033-48e6-8390-206f6b1f56c0]");
        return extractBalance(text);
    }

    public int getTwoCardBalance() {
        val text = cards.last().text();
//        val twoCard = cards.get(2).$("[data-test-id=0f3f5c2a-249e-4c3d-8287-09f7a039391d]")
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
