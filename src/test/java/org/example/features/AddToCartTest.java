package org.example.features;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.example.steps.CartSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class AddToCartTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public CartSteps cartSteps;

    @Test
    public void testAddConfigurableProductToCart() {
        // Product with Khaki option on Madison Island
        String targetProductUrl = "http://qa1.dev.evozon.com/khaki-bowery-chino-pants.html";
        String expectedProductName = "Khaki Bowery Chino Pants";

        cartSteps.navigates_to_product_url(targetProductUrl);
        cartSteps.selects_size("36");
        cartSteps.selects_color("Khaki");
        cartSteps.sets_quantity(2);
        cartSteps.clicks_add_to_cart();

        cartSteps.should_see_success_message("was added to your shopping cart");
        cartSteps.should_see_product_in_cart_table(expectedProductName);
    }
}