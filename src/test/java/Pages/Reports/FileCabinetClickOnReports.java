package Pages.Reports;

import Pages.GenericPage;
import WebElements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


    public class FileCabinetClickOnReports extends GenericPage {

        WebDriver driver;


        //Clicks on File cabinet Reports

        public static final By ReportClick = By.xpath("/html/body/portal-main/div/div/div/portal-router/div/twa-portal-stage[7]/div/div[2]/twa-nav-menu-stage[2]/div/div[2]/twa-file-cabinet-view/div/twa-lazy-view-stack/div/twa-lazy-view[1]/div/twa-file-cabinet-files/twa-page/div/div[1]/div[2]/div/div[2]/div[14]/twa-file-group-detail/div/div");


        @FindBy(how = How.XPATH, using = "/html/body/portal-main/div/div/div/portal-router/div/twa-portal-stage[7]/div/div[2]/twa-nav-menu-stage[2]/div/div[2]/twa-file-cabinet-view/div/twa-lazy-view-stack/div/twa-lazy-view[1]/div/twa-file-cabinet-files/twa-page/div/div[1]/div[2]/div/div[2]/div[14]/twa-file-group-detail/div/div")

        private WebElement Reports_click;

        public void ReportClick() {


            Button click_Reports = new Button(Reports_click);

            click_Reports.element.click();


        }




    }
