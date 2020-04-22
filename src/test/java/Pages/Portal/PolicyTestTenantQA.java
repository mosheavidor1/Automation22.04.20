package Pages.Portal;

import Pages.GenericPage;
import WebElements.Button;
import WebElements.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PolicyTestTenantQA extends GenericPage {






    //FOR QA
   public static final By PolicyTestUrlType = By.xpath("/html/body/div[1]/div[5]/div/div/div/main/div/div/div[1]/div/twa-input/div/material-input/div/div[1]/label/input");


    //FOR INT C
    //public static final By PolicyTestUrlType = By.xpath("/html/body/div/div[7]/div/div/div/main/div/div/div[1]/div/twa-input/div/material-input/div/div[1]/label/input");


    //FOR QA
   @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[5]/div/div/div/main/div/div/div[1]/div/twa-input/div/material-input/div/div[1]/label/input")


    //FOR INT C
    // @FindBy(how = How.XPATH, using = "/html/body/div/div[7]/div/div/div/main/div/div/div[1]/div/twa-input/div/material-input/div/div[1]/label/input")


    private WebElement tenant_id;

    public TextBox tenant;


    public PolicyTestTenantQA() {
        tenant = new TextBox(tenant_id);

    }



//clicks on the selected tenant

    //public static final By ChooseTenant = By.cssSelector(".customer-name");
    // public static final By ChooseTenant = By.xpath("/html/body/div[1]/div[4]/div/div/div/main/div/div/div[2]/twa-select/div/material-list/div/div/twa-select-item/dynamic-component/org-selector-renderer/div/div[2]/div[1]/div");
    public static final By ChooseTenant = By.xpath("/html/body/div[1]/div[5]/div/div/div/main/div/div/div[2]/twa-select/div/material-list/div/div/twa-select-item/dynamic-component/org-selector-renderer/div/div[2]/div[1]/div");

    ////div[@class='customer-name _ngcontent-stx-75']
    // @FindBy(how = How.CSS, using = ".customer-name")
    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div[5]/div/div/div/main/div/div/div[2]/twa-select/div/material-list/div/div/twa-select-item/dynamic-component/org-selector-renderer/div/div[2]/div[1]/div")

    private WebElement Tenant_Id;



    public void ChooseTenantId() {


        Button TenantId = new Button(Tenant_Id);


        TenantId.element.click();


    }




}




