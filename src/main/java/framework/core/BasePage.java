package framework.core;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import static org.junit.jupiter.api.Assertions.*;

public abstract class BasePage {
  protected final Page page;

  protected BasePage(Page page) { 
    this.page = page; 
  }

  // Get a Locator from an XPath string.
  protected Locator x(String xpath) { 
    return page.locator("xpath=" + xpath); 
  }

  // Wait for the element to exist and return it.
  protected Locator waitFor(String xpath) { 
    Locator loc = x(xpath); loc.waitFor(); 
    return loc; 
  }

  // Actions 
  protected void click(String xpath) {
     waitFor(xpath).click(); 
    }
  protected void fill(String xpath, String value) {
    waitFor(xpath).fill(value); 
  }
  protected void pressEnter(String xpath) { 
    waitFor(xpath).press("Enter"); 
  }

  //Assertions

  protected void assertVisible(String xpath) {
    assertTrue(waitFor(xpath).isVisible(), "Expected visible: " + xpath);
  }

  protected void assertTextContains(String xpath, String expected) {
    String text = waitFor(xpath).textContent();
    assertNotNull(text, "No text at: " + xpath);
    assertTrue(text.toLowerCase().contains(expected.toLowerCase()),
        "Expected text to contain '" + expected + "'; was: " + text);
  }

  // Use when you already have a Locator. 
  protected void assertHasText(Locator loc) {
    String t = loc.textContent();
    assertNotNull(t, "Expected non-null text");
    assertFalse(t.trim().isEmpty(), "Expected non-empty text");
  }
}