package org.example.features;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.example.steps.HomeSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class SmokeTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public HomeSteps homeSteps;

    @Test
    public void verifyHomePageLoads() {
        homeSteps.opens_home_page();
        homeSteps.should_see_title();
    }
}