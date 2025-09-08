package framework.pages;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.microsoft.playwright.Page;
import framework.core.BasePage;

public class GoogleHomePage extends BasePage {
  private static final String INPUT_FIELD = "//textarea[@name='q']";
  private static final String SEARCH_BUTTON = "(//input[@class='gNO89b'])[2]";

  public GoogleHomePage(Page page) { 
    super(page); 
}

  // Navigate to Google home.
  public void open() {
    page.navigate("https://google.com");
  }

  //Navigate to Prometheus Career's page
    public void openPrometheus() {
    page.navigate("https://www.prometheusgroup.com/company/careers");
  }

  // Type search text into the input field
    public void fill(String xpath, String value) {
    waitFor(xpath).fill(value); 
  }
  public void typeQuery(String query) {
    fill(INPUT_FIELD, query);
  }

  // Click the 'Google Search' button 
  public void clickSearch() {
    assertVisible(SEARCH_BUTTON);
    click(SEARCH_BUTTON);
  }
}