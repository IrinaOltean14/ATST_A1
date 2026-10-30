package org.example.steps;

import net.serenitybdd.annotations.Step;
import org.example.pages.SearchPage;
import org.junit.Assert;

public class SearchSteps {

    SearchPage searchPage;
    private String selectedProductName;

    @Step("Open the home page")
    public void opens_home_page() {
        searchPage.open();
    }

    @Step("Search for keyword: '{0}'")
    public void searches_for(String keyword) {
        searchPage.enterSearchKeyword(keyword);
        searchPage.clickSearch();
    }

    @Step("Scroll and select the {0}-th product from the search results")
    public void selects_nth_product_from_results(int n) {
        searchPage.scrollToResults();
        Assert.assertTrue("No search results were found!", searchPage.getResultsCount() > 0);
        selectedProductName = searchPage.selectNthProduct(n);
    }

    @Step("Verify that the correct product page loaded")
    public void should_see_correct_product_details() {
        String openedTitle = searchPage.getOpenedProductTitle();
        Assert.assertTrue(
                "Expected title '" + selectedProductName + "' to match opened title '" + openedTitle + "'",
                openedTitle.equalsIgnoreCase(selectedProductName)
        );
    }
}