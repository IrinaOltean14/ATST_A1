package org.example.pages;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DefaultUrl("http://qa1.dev.evozon.com/")
public class SearchPage extends PageObject {

    // Locators using CSS Selectors
    @FindBy(id = "search")
    private WebElementFacade searchInput;

    @FindBy(css = "button.search-button")
    private WebElementFacade searchButton;

    @FindBy(css = ".products-grid .product-name a, ul.products-grid li.item h2.product-name a")
    private List<WebElementFacade> productResults;


    @FindBy(css = ".product-shop .product-name span")
    private WebElementFacade productDetailsTitle;

    public void enterSearchKeyword(String keyword) {
        searchInput.clear();
        searchInput.type(keyword);
    }

    public void clickSearch() {
        searchButton.click();
    }

    public int getResultsCount() {
        return productResults.size();
    }

    public void scrollToResults() {
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0, 400);");
    }

    public String selectNthProduct(int index) {
        int zeroBasedIndex = index - 1;

        if (zeroBasedIndex < 0 || zeroBasedIndex >= productResults.size()) {
            throw new IndexOutOfBoundsException("Result index " + index + " is out of bounds. Found items: " + productResults.size());
        }

        WebElementFacade targetProduct = productResults.get(zeroBasedIndex);
        String selectedTitle = targetProduct.getText().trim();

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", targetProduct);
        targetProduct.click();

        return selectedTitle;
    }

    public String getOpenedProductTitle() {
        return productDetailsTitle.getText().trim();
    }
}