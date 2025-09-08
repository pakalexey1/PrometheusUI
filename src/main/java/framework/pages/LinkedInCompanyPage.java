package framework.pages;

import com.microsoft.playwright.Page;
import framework.core.BasePage;

public class LinkedInCompanyPage extends BasePage {

  // From your spec
  private static final String POPUP_CLOSE_ICON = "//icon[@class='contextual-sign-in-modal__modal-dismiss-icon lazy-loaded']/ancestor::button";

  private static final String SEE_JOBS_BUTTON = "//a[@class='top-card-layout__cta mt-2 ml-1.5 h-auto babybear:flex-auto top-card-layout__cta--primary btn-md btn-primary']";

  public LinkedInCompanyPage(Page page) { 
    super(page); 
}

  // Close the “not logged in” modal if it appears
  public void closeLoginPopupIfPresent() {
    click(POPUP_CLOSE_ICON);
  }

  // Click "See jobs" and wait for navigation; returns the jobs list page
  public LinkedInAllCompanyJobs goToJobsList() {
    assertVisible(SEE_JOBS_BUTTON);
    String before = page.url();

    click(SEE_JOBS_BUTTON);

    // wait for URL to change
    long end = System.currentTimeMillis() + 10000;
    while (System.currentTimeMillis() < end) {
      if (!page.url().equals(before)) break;
      page.waitForTimeout(100);
    }
    page.waitForLoadState(); 
    return new LinkedInAllCompanyJobs(page);
  }
}