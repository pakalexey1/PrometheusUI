package framework.pages;

import com.microsoft.playwright.Page;
import framework.core.BasePage;

public class GoogleHomePage extends BasePage {
  private static final String INPUT_FIELD      = "(//textarea[@name='q']";
  private static final String SEARCH_BUTTON = "//input[@class='RNmpXc']";

  public GoogleHomePage(Page page) { 
    super(page); 
}

  // Navigate to Google home.
  public void open() {
    page.navigate("https://www.google.com/");
  }

  // Type search text into the input field
  public void typeQuery(String query) {
    fill(INPUT_FIELD, query);
  }

  // Click the 'Google Search' button 
  public void clickSearch() {
    assertVisible(SEARCH_BUTTON);
    click(SEARCH_BUTTON);
  }
}