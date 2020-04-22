package Tests;

import Actions.CloudActions;
import Utils.PropertiesFile.PropertiesFile;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
// //span[contains(text(),'test123')]  to use for finding the report (assert that the generated report exist).

public class ReportsRunSet extends GenericTest {

    private static WebDriver driver;

    private CloudActions action;

    @Factory(dataProvider = "getData")
    public ReportsRunSet(Object dataToSet) {
        super(dataToSet);
        action = new CloudActions();


    }


    @Test

    //customer id xpath
    //
    //
    ////*[@id="dateRangeInput"]/select-dropdown-button/div/span[2]


    public void PolicyTest1() throws Exception {
//Policy test - login page
        OpenGenerateReportPage();

        clickOnFindingsAndIncidents();

        SetTheReportName();

        SetCustomerId();
        SearchCustomerID();

        CustomerDateRange();

        ClickOnRunNowButton();

        FileCabinet();

      //  FileCReports();

        // ReportExist();


//
////Sets the Tenant in the policy test page.
//            policyTestSetUser();
//
////Run's the policyTest test
//            policyTestRunTest();


    }


    public void OpenGenerateReportPage() throws Exception {


        action.LaunchApplication(data.get("Browser"));

        //action.SetApplicationUrl(data.get("URL"));

        //action.Login(data.get("UserName"), data.get("Password"));


        action.SetApplicationUrl(PropertiesFile.getCurrentClusterLink());   //Cluster Link


        action.Login(PropertiesFile.getUserName(), PropertiesFile.getPassword());  // User name + password from properties file.


        action.GotoReportsGeneratorPage(PropertiesFile.getCurrentClusterLink()); // Add the Login page Link

        //action.GotoSWGPolicyTestPage(data.get("URL"));  - //URL Link from excel .


    }


    public void clickOnFindingsAndIncidents() throws Exception {

        action.clickOnFindingsAndIncidents();


    }


    public void SetTheReportName() throws Exception {


        action.TypeNameIncidents(data.get("Report's Name"));


    }


    public void SetCustomerId() throws Exception {

        action.ReportsClickOnCustomerID();


    }

    public void SearchCustomerID() throws Exception {

        action.CustomerIDSearch(data.get("CustomerID"));
    }


    public void CustomerDateRange() throws Exception {

        action.CustomerDate();

    }


    public void ClickOnRunNowButton() throws Exception {

        action.RunNow();


    }

    public void FileCabinet() throws Exception {

        Thread.sleep(20000);

        action.GoToFileCabinet(PropertiesFile.getCurrentClusterLink());
    }



    public void FileCReports() throws  Exception{

        action.FileCabinetReports();

}





public void ReportExist() throws  Exception {

action.ReportExist();
}

















//
//
//        public void policyTestSetUser() throws IOException, InterruptedException {
//
////Configure Tenant
//            action.ClickOnPolicyTestCofigureUser();
//
//            //Search Tenant according to cluster
//
//            action.TypeInTrustWaveSelectCluster();
//
//
////Set tenant ID - from Excel file
//            //  action.TypeInTrustWave123(data.get("CustomerName"));
//
//
//            //   action.TypeInTrustWave123QA (PropertiesFile.getCustomerName() );
//
//
//        }
//
//
//
//        public void policyTestRunTest () throws Exception {
//
//
//            action.PolicyTestTypeURL(data.get("URL TO BLOCK"));
//
//            action.PolicyTestTypeIP(data.get("IP TO BLOCK"));
//            action.ClickOnGo();
//            action.PageIsBlocked();
//            //  action.PolicyTestBlock();   ////BLOCK MESSAGE policy test
//
//
//        }
//
//
//
//        public void OpenPoliciesPage () throws Exception {
//
//            action.LaunchApplication(data.get("Browser"));
//            action.SetApplicationUrl(PropertiesFile.getCurrentClusterLink());
//
//
//        }
//
//
//

    @BeforeClass
    public void beforeClass () {


    }
//
//        @AfterClass
//        public void afterClass() {
//
//            action.CloseApplication();

}





