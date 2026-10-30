package org.example.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductDetailsPage extends PageObject {

    // Swatch links for color and size
    @FindBy(css = "#configurable_swatch_color li a")
    private List<WebElementFacade> colorSwatches;

    @FindBy(css = "#configurable_swatch_size li a")
    private List<WebElementFacade> sizeSwatches;

    // Quantity input
    @FindBy(id = "qty")
    private WebElementFacade quantityInput;

    // Add to cart button
    @FindBy(css = ".add-to-cart-buttons button.btn-cart")
    private WebElementFacade addToCartButton;

    // Success alert message
    @FindBy(css = "li.success-msg span")
    private WebElementFacade successMessage;

    // Cart table product titles
    @FindBy(css = "#shopping-cart-table h2.product-name a")
    private List<WebElementFacade> cartProductNames;

    public void selectColor(String colorName) {
        // Find the swatch matching title or inner text (e.g. Khaki)
        WebElementFacade targetColor = colorSwatches.stream()
                .filter(swatch -> {
                    String title = swatch.getAttribute("title");
                    String name = swatch.getAttribute("name");
                    String text = swatch.getText();
                    return (title != null && title.equalsIgnoreCase(colorName)) ||
                            (name != null && name.equalsIgnoreCase(colorName)) ||
                            (text != null && text.trim().equalsIgnoreCase(colorName));
                })
                .findFirst()
                .orElse(colorSwatches.isEmpty() ? null : colorSwatches.get(0));

        if (targetColor != null) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", targetColor);
            targetColor.click();
        } else {
            throw new RuntimeException("Color swatch '" + colorName + "' was not found on the page.");
        }
    }

    public void selectSize(String sizeLabel) {
        // Find the swatch matching size label (e.g. M, L, S)
        WebElementFacade targetSize = sizeSwatches.stream()
                .filter(swatch -> {
                    String title = swatch.getAttribute("title");
                    String text = swatch.getText();
                    return (title != null && title.equalsIgnoreCase(sizeLabel)) ||
                            (text != null && text.trim().equalsIgnoreCase(sizeLabel));
                })
                .findFirst()
                .orElse(sizeSwatches.isEmpty() ? null : sizeSwatches.get(0));

        if (targetSize != null) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", targetSize);
            targetSize.click();
        } else {
            throw new RuntimeException("Size swatch '" + sizeLabel + "' was not found on the page.");
        }
    }

    public void setQuantity(int qty) {
        quantityInput.clear();
        quantityInput.type(String.valueOf(qty));
    }

    public void clickAddToCart() {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
        addToCartButton.click();
    }

    public String getSuccessAlertText() {
        return successMessage.waitUntilVisible().getText().trim();
    }

    public boolean isProductPresentInCart(String productName) {
        return cartProductNames.stream()
                .anyMatch(item -> item.getText().trim().toLowerCase().contains(productName.toLowerCase()));
    }
}