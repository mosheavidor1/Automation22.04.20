package Pages.Reports;

import Pages.GenericPage;
import WebElements.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


    public class ReportTypeName extends GenericPage {


        //Policy Test Blocked Url


        public static final By ReportsIncidentsTypeName = By.xpath("//*[@id=\"configNameInput--material-input\"]/div/div[1]/label/input");


        @FindBy(how = How.XPATH, using = "//*[@id=\"configNameInput--material-input\"]/div/div[1]/label/input")

        private WebElement Block_elment;

        public TextBox Block;


        public ReportTypeName() {
            Block = new TextBox(Block_elment);


        }


    }





