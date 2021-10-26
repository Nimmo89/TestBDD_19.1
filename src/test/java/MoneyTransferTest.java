import org.junit.jupiter.api.Test;
import data.DataHelper;
//import page.DashboardPage;
import page.LoginPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MoneyTransferTest {

    @Test
    void shouldTransferWithOneOnTwo() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var cardBalance = dashboardPage.getOneCardBalance();
        verificationPage.validVerify(verificationCode);
        $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] .button").click();
        $("[data-test-id=amount] input").setValue("500");
        $("[data-test-id=from] input").setValue("5559000000000002");
        $("[data-test-id=action-transfer]").click();
        $("[data-test-id=dashboard]").shouldBe(visible);
        $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']").shouldHave(text(String.valueOf(cardBalance)));
    }

    @Test
    void shouldTransferWithTwoOnOne() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var cardBalance = dashboardPage.getTwoCardBalance();
        verificationPage.validVerify(verificationCode);
        $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] .button").click();
        $("[data-test-id=amount] input").setValue("500");
        $("[data-test-id=from] input").setValue("5559000000000001");
        $("[data-test-id=action-transfer]").click();
        $("[data-test-id=dashboard]").shouldBe(visible);
        $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']").shouldHave();
    }
}
