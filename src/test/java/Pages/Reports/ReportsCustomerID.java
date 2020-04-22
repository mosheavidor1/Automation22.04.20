package Pages.Reports;

import Pages.GenericPage;
import WebElements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

//Set the customer ID for Incidents and Findings


    public class ReportsCustomerID extends GenericPage {

        WebDriver driver;


        public static final By SelectCustomerID = By.xpath("//*[@id=\"customerId_31--31\"]/div/div[3]/div[1]/span");
       // public static final By SelectCustomerID = By.xpath("//*[@id=\"customerId_82--82\"]/div/div[3]/div[1]/span");


        @FindBy(how = How.XPATH, using = "//*[@id=\"customerId_31--31\"]/div/div[3]/div[1]/span")
       //  @FindBy(how = How.XPATH, using = "//*[@id=\"customerId_82--82\"]/div/div[3]/div[1]/span")


        private WebElement CustomerID_elements;
        public Button CustomerID_click;

        public ReportsCustomerID(){


            CustomerID_click=new Button(CustomerID_elements);
        }

        public void CustomerButton() {

            Button button123 = new Button(CustomerID_elements);

            button123.click();


        }

}
