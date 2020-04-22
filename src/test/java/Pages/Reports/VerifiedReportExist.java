package Pages.Reports;

import Pages.GenericPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;



    // Selenium Automation Report 62   (span class)

    public class VerifiedReportExist extends GenericPage {


        public static final By verified = By.xpath("//*[@id=\"2e02a769-ce39-4735-8e3c-26dc6059e555\"]/twa-file-name-column/div/div[1]/twa-linkbutton/a/div/div/span");






        //Trustwave image
        // @FindBy(how= How.XPATH,using=BlockMessage)
        @FindBy(how= How.XPATH,using="//label[contains(text(),'Selenium Automation Report 62')]")
        public WebElement verifiedReport_elment;







    }





