import com.google.gson.JsonElement;
import helper.TestResponse;
import helper.Util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static java.lang.Thread.sleep;

public class MoneyTransferApiFunctionalTest {
    TestResponse res;
    @Before
    public  void beforeClass() throws InterruptedException {
        MoneyTransferAPI.main(null); //to spark the spark server
        sleep(3000);
    }

    @Test
    public void userAccountShouldBeCreated() {

        //WHEN
        res = Util.request("PUT", "/account/4?userId=1&balance=200&currencyCode=EUR");

        //THEN
        JsonElement json = res.jsonElement();
        Assert.assertEquals(200, res.status);
    }


    @Test
    public void moneyIsSuccesfullyTransferredBetweenAccounts() {

        //WHEN
        res = Util.request("POST", "/moneytransfer?fromAccountId=1&toAccountId=2&amountToTransfer=20");

        //THEN
        JsonElement json = res.jsonElement();
        Assert.assertEquals(200, res.status);
    }

    @Test
    public void moneyShouldBeSuccessfullyWIthdrawnFromAnAccount() {

        //WHEN
        res = Util.request("PUT", "/account/2/withdraw/20");

        //THEN
        JsonElement json = res.jsonElement();
        Assert.assertEquals(200, res.status);
    }

    @Test
    public void moneyShouldBeSuccessfullyDepositedToAnAccount() {

        //WHEN
        res = Util.request("PUT", "/account/2/deposit/20");

        //THEN
        JsonElement json = res.jsonElement();
        Assert.assertEquals(200, res.status);
    }
}