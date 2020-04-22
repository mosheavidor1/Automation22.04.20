package Pages.Portal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import Pages.GenericPage;

public class BlockPage extends GenericPage {

	private static final String  trustwaveImageID1 = "backGrdImg_Id";
	public static final By trustwaveImageIID = By.id(trustwaveImageID1);

	private static final String  pageBlockedID = "backGrdText_Id";
	public static final By pageBlockedTextByID = By.id(pageBlockedID);

	//Trustwave image
	@FindBy(how=How.ID,using=trustwaveImageID1)
	public WebElement trustwaveImage_element;

	//Trustwave image
	@FindBy(how=How.ID,using=pageBlockedID)
	public WebElement pageBlocked_element;




	private static final String  pageBlockedID4 = "/html/body/center/table/tbody/tr[2]/td/table/tbody/tr[1]/td";
	public static final By pageBlockedTextByID5 = By.xpath(pageBlockedID4);




	@FindBy(how=How.XPATH,using=pageBlockedID4)
	public WebElement pageBlocked_element2;





}
