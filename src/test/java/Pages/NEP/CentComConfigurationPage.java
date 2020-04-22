package Pages.NEP;

import Pages.GenericPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CentComConfigurationPage extends GenericPage {

    @FindBy(how= How.XPATH,using="//twa-button[@aria-label='Publish']")
    public WebElement publishButton_element;

    @FindBy(how= How.XPATH,using="//div[@class='pane modal visible']//twa-button[@aria-label='Continue']")
    public WebElement continueButton_element;


    private static final String percent100Xpath="//label[contains(text(),'100%')]";
    public static final By percent100By = By.xpath(percent100Xpath);
    @FindBy(how= How.XPATH,using=percent100Xpath)
    public WebElement percent_100_element;




}
