package framework.core;

import com.microsoft.playwright.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Driver {
  public static Playwright playwright;
  public static Browser browser;
  public static BrowserContext context;
  public static Page page;
  public static Properties cfg = new Properties();

  public static void init() {
    if (playwright != null) return;
    try { cfg.load(new FileInputStream("src/test/resources/config.properties")); }
    catch (IOException e) { throw new RuntimeException(e); }

    playwright = Playwright.create();
    boolean headless = Boolean.parseBoolean(cfg.getProperty("headless","true"));
    BrowserType.LaunchOptions opts = new BrowserType.LaunchOptions().setHeadless(headless);
    browser = playwright.chromium().launch(opts);
    context = browser.newContext();
    context.setDefaultTimeout(Integer.parseInt(cfg.getProperty("defaultTimeout","15000")));
    page = context.newPage();
  }
  public static void quit() {
    if (context!=null) context.close();
    if (browser!=null) browser.close();
    if (playwright!=null) playwright.close();
  }
}
