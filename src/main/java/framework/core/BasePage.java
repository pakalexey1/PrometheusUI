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
  
  // Wait until a given attribute becomes absent (null) or timeout
  protected void waitUntilAttributeAbsent(Locator loc, String attr, int timeoutMs) {
  long end = System.currentTimeMillis() + timeoutMs;
  while (System.currentTimeMillis() < end) {
    if (loc.getAttribute(attr) == null) return;
    page.waitForTimeout(100);
  }
  throw new AssertionError("Timed out waiting for attribute '" + attr + "' to be absent");
}

  // Actions 
  protected void click(String xpath) {
     waitFor(xpath).click(); 
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