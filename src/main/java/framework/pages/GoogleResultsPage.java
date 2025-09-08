package framework.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import framework.core.BasePage;

public class GoogleResultsPage extends BasePage {

  private static final String SEARCH_RESULTS = "//div[@id='search']";
  private static final String CAREERS_LINK = "//a[.='Careers']";  
 
  public GoogleResultsPage(Page page){ 
    super(page); 
}
  //Validate the search results contain 'Prometheus Group'
  public void assertResultsContain(String expectedText) {
    assertTextContains(SEARCH_RESULTS, expectedText);
  }

  //Click on the 'Careers' link
  public void clickCareers() {
    Locator link = waitFor(CAREERS_LINK); 
    link.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    link.click();
  } 
}