// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class ResponsesFromSeekersTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void responsesFromSeekers() {
    driver.get("http://localhost:8080/");
    driver.manage().window().setSize(new Dimension(1206, 693));
    driver.findElement(By.id("rootCompanyLink")).click();
    driver.findElement(By.id("seekerLoginLink")).click();
    driver.findElement(By.name("login")).sendKeys("myLogin");
    driver.findElement(By.name("password")).sendKeys("1234");
    driver.findElement(By.cssSelector(".submit-button")).click();
    driver.findElement(By.linkText("Мои вакансии")).click();
    assertThat(driver.getTitle(), is("Мои вакансии"));
    driver.findElement(By.linkText("Отклики")).click();
    assertThat(driver.getTitle(), is("Отклики"));
    assertThat(driver.findElement(By.cssSelector("td > span")).getText(), is("Казанцева Варвара Денисовна"));
    driver.findElement(By.linkText("Подробнее")).click();
    assertThat(driver.getTitle(), is("Информация о работнике"));
  }
}
