package org.example.steps;

import net.serenitybdd.annotations.Step;
import org.example.pages.ProductDetailsPage;
import org.junit.Assert;

public class CartSteps {

    ProductDetailsPage productDetailsPage;

    @Step("Navigate directly to product: {0}")
    public void navigates_to_product_url(String productUrl) {
        productDetailsPage.openAt(productUrl);
    }

    @Step("Select product size: '{0}'")
    public void selects_size(String size) {
        productDetailsPage.selectSize(size);
    }

    @Step("Select product color: '{0}'")
    public void selects_color(String color) {
        productDetailsPage.selectColor(color);
    }

    @Step("Set quantity to {0}")
    public void sets_quantity(int quantity) {
        productDetailsPage.setQuantity(quantity);
    }

    @Step("Click Add to Cart")
    public void clicks_add_to_cart() {
        productDetailsPage.clickAddToCart();
    }

    @Step("Verify success message contains '{0}'")
    public void should_see_success_message(String expectedSnippet) {
        String alertText = productDetailsPage.getSuccessAlertText();
        Assert.assertTrue(
                "Expected alert containing '" + expectedSnippet + "', but received: '" + alertText + "'",
                alertText.toLowerCase().contains(expectedSnippet.toLowerCase())
        );
    }

    @Step("Verify product '{0}' is displayed in the cart")
    public void should_see_product_in_cart_table(String productName) {
        Assert.assertTrue(
                "Product " + productName + " was not found in the shopping cart table!",
                productDetailsPage.isProductPresentInCart(productName)
        );
    }
}