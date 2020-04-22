package Pages.Reports;

import Pages.GenericPage;
import WebElements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ReportsClickOnRunNow extends GenericPage {


    public static final By ClickOnRunNow = By.xpath("/html/body/portal-main/div/div/div/portal-router/div/twa-portal-stage[6]/div/div[2]/twa-nav-menu-stage/div/div[2]/twa-reporting-view/twa-lazy-view-stack/div/twa-lazy-view[1]/div/twa-reporting/div[2]/twa-reporting-config-details/div/twa-button[2]/material-button/div/div");
   // public static final By ClickOnRunNow = By.xpath("/html/body/portal-main/div/div/div/portal-router/div/twa-portal-stage[7]/div/div[2]/twa-nav-menu-stage/div/div[2]/twa-reporting-view/twa-lazy-view-stack/div/twa-lazy-view[1]/div/twa-reporting/div[2]/twa-reporting-config-details/div/twa-button[2]/material-button/div/div");


    @FindBy(how = How.XPATH, using = "/html/body/portal-main/div/div/div/portal-router/div/twa-portal-stage[6]/div/div[2]/twa-nav-menu-stage/div/div[2]/twa-reporting-view/twa-lazy-view-stack/div/twa-lazy-view[1]/div/twa-reporting/div[2]/twa-reporting-config-details/div/twa-button[2]/material-button/div/div")
    //@FindBy(how = How.XPATH, using = "/html/body/portal-main/div/div/div/portal-router/div/twa-portal-stage[7]/div/div[2]/twa-nav-menu-stage/div/div[2]/twa-reporting-view/twa-lazy-view-stack/div/twa-lazy-view[1]/div/twa-reporting/div[2]/twa-reporting-config-details/div/twa-button[2]/material-button/div/div")

    public WebElement RUN_Click;

    public void ReportsClickOnRunNow() {


        Button click_Run = new Button(RUN_Click);



      click_Run.element.click();


    }
}
