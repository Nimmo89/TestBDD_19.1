import org.junit.jupiter.api.Test;
import data.DataHelper;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.*;
import static data.DataHelper.*;
import static org.junit.jupiter.api.Assertions.*;

public class MoneyTransferTest {

//    @Test
//    void shouldTransferWithOneOnTwo() {
//        open("http://localhost:9999");
//        var loginPage = new LoginPage();
//        var authInfo = DataHelper.getAuthInfo();
//        var verificationPage = loginPage.validLogin(authInfo);
//        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
//        var dashboardPage = verificationPage.validVerify(verificationCode);
//        int pay = 500;
//        int expected = (dashboardPage.getOneCardBalance() + pay);
//        int expected2Card = (dashboardPage.getTwoCardBalance() - pay);
//        $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] .button").click();
//        $("[data-test-id=amount] input").setValue(String.valueOf(pay));
//        $("[data-test-id=from] input").setValue("5559000000000002");
//        $("[data-test-id=action-transfer]").click();
//        $("[data-test-id=dashboard]").shouldBe(visible);
//        int actual = dashboardPage.getOneCardBalance();
//        assertEquals(expected, actual);
//        int actual2Card = dashboardPage.getTwoCardBalance();
//        assertEquals(expected2Card, actual2Card);
//    }
//
//    @Test
//    void shouldTransferWithTwoOnOne() {
//        open("http://localhost:9999");
//        var loginPage = new LoginPage();
//        var authInfo = DataHelper.getAuthInfo();
//        var verificationPage = loginPage.validLogin(authInfo);
//        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
//        var dashboardPage = verificationPage.validVerify(verificationCode);
//        int pay = 500;
//        int expected = (dashboardPage.getTwoCardBalance() + pay);
//        int expected1Card = (dashboardPage.getOneCardBalance() - pay);
//        $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] .button").click();
//        $("[data-test-id=amount] input").setValue("500");
//        $("[data-test-id=from] input").setValue("5559000000000001");
//        $("[data-test-id=action-transfer]").click();
//        $("[data-test-id=dashboard]").shouldBe(visible);
//        int actual = dashboardPage.getTwoCardBalance();
//        assertEquals(expected, actual);
//        int actual1Card = dashboardPage.getOneCardBalance();
//        assertEquals(expected1Card, actual1Card);
//    }

    @Test
    void shouldTransferWithOneOnTwo() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardInfo = getOneCardNum();
        var secondCardInfo = getTwoCardNum();
        int pay = 500;
        var expectedBalanceOneCard = dashboardPage.getOneCardBalance() - pay;
        var expectedBalanceTwoCard = dashboardPage.getTwoCardBalance() + pay;
        var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = transferPage.makeTransfer(String.valueOf(pay), firstCardInfo);
        var actualBalanceOneCard = dashboardPage.getOneCardBalance();
        var actualBalanceTwoCard = dashboardPage.getTwoCardBalance();
        assertEquals(expectedBalanceOneCard, actualBalanceOneCard);
        assertEquals(expectedBalanceTwoCard, actualBalanceTwoCard);
    }
}
