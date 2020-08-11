package com.jqueryui.stepDefinitions;


import com.jqueryui.utilities.CommonMethods;
import com.jqueryui.utilities.MyDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;


public class JQueryUISteps extends CommonMethods {
    @Given("User is in landing page")
    public void user_is_in_landing_page() {
        System.out.println(MyDriver.get().getCurrentUrl());
    }

    @Then("^Verify User is in the landing page$")
    public void verify_user_is_in_the_landing_page() {
        String actTitle = MyDriver.get().getTitle();
        String expTitle = "jQuery UI";
        Assert.assertEquals(expTitle, actTitle);
        slpBrowser(4000);

    }
}
