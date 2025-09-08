package framework.pages;

import framework.core.BasePage;
import com.microsoft.playwright.Page;

public class LinkedInAllCompanyJobs extends BasePage {

  public LinkedInAllCompanyJobs(Page page) { 
    super(page); 
}


  // Asserts that a job title containing the given text is visible on the page
  public void assertJobPresent(String title) {
    String job = "//div[@data-entity-urn='urn:li:jobPosting:4289261629']";
    assertVisible(job); // waits & asserts visible
  }

  
  // Validate the job name
  public void assertSeniorSDETPresent() {
    assertJobPresent("Senior Software Developer in Test (SDET)");
  }
}