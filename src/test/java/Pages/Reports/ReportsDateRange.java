package Pages.Reports;

import Pages.GenericPage;
import WebElements.Button;
import WebElements.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;






//SEARCHING FOR  Date Range

    public class ReportsDateRange extends GenericPage {


        public static final By SearchedDate = By.xpath("//*[@id=\"dateRangeInput\"]/select-dropdown-button/div");


        @FindBy(how = How.XPATH, using = "//*[@id=\"dateRangeInput\"]/select-dropdown-button/div")

        private WebElement Search_Date;




        public void  ReportsDateRange() {
         Button SearchDate = new Button(Search_Date);

         SearchDate.element.click();


        }
//clicks on the selected Range


        public static final By ChooseRange = By.xpath("/html/body/div/div[5]/div/div/div/div[2]/div/div[2]/div/material-list/div/div[2]/twa-select-dropdown-item/span");
       // public static final By ChooseRange = By.xpath("/html/body/div/div[9]/div/div/div/div[2]/div/div[2]/div/material-list/div/div[2]/twa-select-dropdown-item/span");



        @FindBy(how = How.XPATH, using = "/html/body/div/div[5]/div/div/div/div[2]/div/div[2]/div/material-list/div/div[2]/twa-select-dropdown-item/span")
       // @FindBy(how = How.XPATH, using = "/html/body/div/div[9]/div/div/div/div[2]/div/div[2]/div/material-list/div/div[2]/twa-select-dropdown-item/span")

        private WebElement Customer_ID;


        public void ChooseThisRange () {


            Button CustomerID = new Button(Customer_ID);

            CustomerID.element.click();




        }


    }















