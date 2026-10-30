package org.example.features;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.junit.annotations.TestData;
import org.example.steps.SearchSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.Collection;

@RunWith(SerenityParameterizedRunner.class)
public class SearchProductDDTTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public SearchSteps searchSteps;

    // Test data columns
    private String searchTerm;
    private int itemIndex;

    // Constructor matching the test dataset parameters
    public SearchProductDDTTest(String searchTerm, int itemIndex) {
        this.searchTerm = searchTerm;
        this.itemIndex = itemIndex;
    }

    // Two distinct datasets to fulfill the Data-Driven requirement
    @TestData
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"shirt", 1},   // Searches "shirt" and picks the 1st result
                {"pants", 2}     // Searches "dress" and picks the 2nd result
        });
    }

    @Test
    public void testSearchAndSelectNthProduct() {
        searchSteps.opens_home_page();
        searchSteps.searches_for(searchTerm);
        searchSteps.selects_nth_product_from_results(itemIndex);
        searchSteps.should_see_correct_product_details();
    }
}