package framework.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import framework.core.BasePage;

import static org.junit.jupiter.api.Assertions.*;

public class CareersPage extends BasePage {

  public CareersPage(Page page) { super(page); }

  private static final String[] HEADER = new String[] {
      "//span[.='Opportunities to work for an industry leader']",
      "//span[.='In-person, in-office culture']",
      "//span[.='Benefits tailored to your needs']",
      "//span[.='A passionate, diverse team']"
  };

  private static final String[] TEXT = new String[] {
      "//span[.='Opportunities to work for an industry leader']/ancestor::summary/following-sibling::div/span",
      "//span[.='In-person, in-office culture']/ancestor::summary/following-sibling::div/span",
      "//span[.='Benefits tailored to your needs']/ancestor::summary/following-sibling::div/span",
      "//span[.='A passionate, diverse team']/ancestor::summary/following-sibling::div/span"
  };

  private static final String[] DETAILS = new String[] {
      "//span[.='Opportunities to work for an industry leader']//ancestor::details",
      "//span[.='In-person, in-office culture']//ancestor::details",
      "//span[.='Benefits tailored to your needs']//ancestor::details",
      "//span[.='A passionate, diverse team']//ancestor::details"
  };

  private static final String VIEW_ALL_PROMETHEUS_JOBS = "//a[@href='https://www.linkedin.com/company/prometheusgroup/jobs/']";

  // Verifies each accordion opens and closes 
  public void validateAccordions() {
    for (int i = 0; i < HEADER.length; i++) {
      String header = HEADER[i];
      String text   = TEXT[i];
      String detail = DETAILS[i];

      // 1) Clickable header visible
      assertVisible(header);

      // 2) Click header -> text appears AND <details> tag has 'open' attribute
      click(header);

      Locator content = waitFor(text);         
      assertHasText(content);                 

      Locator detailsEl = x(detail);
      String openAttr = detailsEl.getAttribute("open");
      assertNotNull(openAttr, "Expected 'open' attribute after opening accordion #" + (i + 1));

      // 3) Click again -> text disappears by verifying 'open' attribute goes away
      click(header);
      waitUntilAttributeAbsent(detailsEl, "open", 5000);
      String openAfterClose = detailsEl.getAttribute("open");
      assertNull(openAfterClose, "Expected 'open' to be removed after closing accordion #" + (i + 1));
    }
  }

  // CareersPage.java
public Page clickViewAllPrometheusJobsInNewTab() {
  assertVisible(VIEW_ALL_PROMETHEUS_JOBS);

  // open the link; detect the newly opened tab by page-count
  int before = page.context().pages().size();
  click(VIEW_ALL_PROMETHEUS_JOBS);

  Page newTab = null;
  long deadline = System.currentTimeMillis() + 10_000; // 10s
  while (System.currentTimeMillis() < deadline) {
    var pages = page.context().pages();
    if (pages.size() > before) {
      newTab = pages.get(pages.size() - 1);
      break;
    }
    page.waitForTimeout(100);
  }
  assertNotNull(newTab, "LinkedIn tab did not open within 10s");

  return newTab; 
  }
}