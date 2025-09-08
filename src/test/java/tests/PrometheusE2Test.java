package tests;

import com.microsoft.playwright.Page;
import framework.core.Driver;
import framework.pages.GoogleHomePage;
import framework.pages.GoogleResultsPage;
import framework.pages.CareersPage;
import framework.pages.LinkedInCompanyPage;
import framework.pages.LinkedInAllCompanyJobs;
import org.junit.jupiter.api.*;

public class PrometheusE2Test {
  @BeforeEach void setup() { Driver.init(); }
  @AfterEach  void teardown() { Driver.quit(); }

  @Test
  void e2e() {
    // Google
    GoogleHomePage home = new GoogleHomePage(Driver.page);
    home.openPrometheus();
    // home.open();
    // home.fill("//textarea[@name='q']","Prometheus Group"); 
    // home.clickSearch();



    // // Results
    // GoogleResultsPage results = new GoogleResultsPage(Driver.page);
    // results.assertResultsContain("Prometheus Group");
    // results.clickCareers();


    // Prometheus Careers
    CareersPage careers = new CareersPage(Driver.page);
    careers.validateAccordions();

    // Open LinkedIn in a new tab
    Page linkedinTab = careers.clickViewAllPrometheusJobsInNewTab();

    // LinkedIn (company -> jobs list)
    LinkedInCompanyPage company = new LinkedInCompanyPage(linkedinTab);
    company.closeLoginPopupIfPresent();
    LinkedInAllCompanyJobs jobs = company.goToJobsList();
    jobs.assertSeniorSDETPresent();
  }
}