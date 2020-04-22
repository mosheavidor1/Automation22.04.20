package Pages.Reports;

import Pages.GenericPage;
import WebElements.Button;
import WebElements.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

//SEARCHING FOR THE CUSTOMER ID IN REPORTS INCIDENTS AND FINDINGS.

    public class SearchCustomerID extends GenericPage {


        public static final By ReportsIncidentsTypeName = By.xpath("//*[@id=\"searchinput--material-input\"]/div/div[1]/label/input");


        @FindBy(how = How.XPATH, using = "//*[@id=\"searchinput--material-input\"]/div/div[1]/label/input")

        private WebElement Search_elment;

        public TextBox search;


        public SearchCustomerID() {
            search = new TextBox(Search_elment);


        }
//clicks on the selected Customer


            public static final By chooseCustomer = By.xpath("/html/body/div/div[4]/div/div/div/main/div/div/div[2]/twa-select/div/material-list/div/div/twa-select-item/dynamic-component/org-selector-renderer/div/div[2]/div[2]/div[1]");
            //public static final By chooseCustomer = By.xpath("/html/body/div/div[8]/div/div/div/main/div/div/div[2]/twa-select/div/material-list/div/div/twa-select-item/dynamic-component/org-selector-renderer/div/div[2]/div[1]/div");



            @FindBy(how = How.XPATH, using = "/html/body/div/div[4]/div/div/div/main/div/div/div[2]/twa-select/div/material-list/div/div/twa-select-item/dynamic-component/org-selector-renderer/div/div[2]/div[2]/div[1]")
           // @FindBy(how = How.XPATH, using = "/html/body/div/div[8]/div/div/div/main/div/div/div[2]/twa-select/div/material-list/div/div/twa-select-item/dynamic-component/org-selector-renderer/div/div[2]/div[1]/div")

            private WebElement Customer_ID;


            public void chooseCustomerID () {


                Button CustomerID = new Button(Customer_ID);

                CustomerID.element.click();




            }


        }












