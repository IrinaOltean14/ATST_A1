package org.example.steps;

import net.serenitybdd.annotations.Step;
import org.example.pages.HomePage;
import org.junit.Assert;

public class HomeSteps {

    HomePage homePage;

    @Step("Opens the Magento home page")
    public void opens_home_page() {
        homePage.open();
    }

    @Step("Verifies the page title contains Madison Island")
    public void should_see_title() {
        String title = homePage.getTitle();
        System.out.println("Page title is: " + title);
        Assert.assertTrue("Title does not match!", title.toLowerCase().contains("madison"));
    }
}