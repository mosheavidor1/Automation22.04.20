package Actions;

import Pages.NEP.*;
import Pages.Portal.UpperMenu;
import Utils.Logs.JLog;
import Utils.PropertiesFile.PropertiesFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class NepActions extends CloudActions{
    private static final String PortalManagmentURL = "/#/operations?menuKey=cua-search&stackKey=search-home";
    private static final String FileCabinetURL = "/#/support?menuKey=file-cabinet&stackKey=file-cabinet-files";
    private static final String centComSearchURL = "/#/operations?menuKey=centcom-devices&stackKey=entity-search";

    private static final String destinationFolder = "C:\\PROGRA~1\\Trustwave\\NEPAgent\\certs";
    private static final String clientKeyPem = "\\client_key.pem";
    private static final String clientPem = "\\client.pem";

    private static final String dbJsonPath = "C:\\ProgramData\\Trustwave\\NEPAgent\\db.json";


    public void GotoCentComSearch(String UrlPrefix) {

        this.SetApplicationUrl(UrlPrefix + centComSearchURL);
    }



    public void GotoPortalManagmentPage(String UrlPrefix) {

        this.SetApplicationUrl(UrlPrefix + PortalManagmentURL);
    }

    public void GotoPortalFileCabinetPage(String UrlPrefix) {

        this.SetApplicationUrl(UrlPrefix + FileCabinetURL);
    }

    public void PublishNewDownloads(String customerName, int waitForPublishInSec) throws IOException {
        CentComSearchPage centSearch = new CentComSearchPage();
        centSearch.customersText_element.sendKeys(customerName);
        centSearch.searchButton_element.click();
        //centSearch.row_element.click();
        centSearch.GetCustomerRow(customerName).click();
        centSearch.detailsButton_element.click();

        CentComSearchDetailsPage detailsPage = new CentComSearchDetailsPage();
        detailsPage.resetInstaller_element.click();
        detailsPage.continueButton_element.click();

        this.GotoCentComSearch(PropertiesFile.getCurrentClusterLink());

        centSearch.openConfigurationButton_element.click();

        CentComConfigurationPage conf = new CentComConfigurationPage();
        conf.publishButton_element.click();
        conf.continueButton_element.click();
        conf.WaitUntilObjectClickable(conf.percent100By,waitForPublishInSec);

    }

    public void CheckEndPointOkInCentCom(String customerName) throws IOException, InterruptedException {
        CentComSearchPage centSearch = new CentComSearchPage();
        centSearch.customersText_element.sendKeys(customerName);
        centSearch.searchButton_element.click();
        centSearch.GetCustomerRow(customerName).click();
        centSearch.detailsButton_element.click();

        CentComSearchDetailsPage detailsPage = new CentComSearchDetailsPage();
        detailsPage.endPointTab_element.click();

        String host = InetAddress.getLocalHost().getHostName();

        detailsPage.endPointSearchBox_element.sendKeys(host+"\n");
        detailsPage.refreshButton_element.click();

        detailsPage.WaitUntilPageLoad();
        detailsPage.WaitUntilObjectDisappear(detailsPage.spinnerBy);
        detailsPage.WaitUntilObjectClickable(detailsPage.rowBy);

        Thread.sleep(3000); //after all 3 wait above needs some more - to be investigated

        if(!detailsPage.IsElementExist(detailsPage.GetHostNameRowBy(host))) {
            org.testng.Assert.fail("Could not find hostname: " + host);
        }

        if(!detailsPage.IsElementExist(detailsPage.OkBy)) {
            org.testng.Assert.fail("Host: " + host + " Status is not Okay. See screenshot/video.");
        }

    }


    public void SetCustomerMTD(String customerName){
        PortalManagementPage mngPage = new PortalManagementPage();
        mngPage.searchTextBox_element.sendKeys(customerName + "\n");
        mngPage.searchTextBox_element.click(); //help to avoid issue
        //mngPage.searchButton_element.click(); //search button click is not necessary
        mngPage.WaitUntilObjectClickable(mngPage.customerRowBy);
        mngPage.customerRow_element.click();
        mngPage.customerMagnifyingGlass_element.click();

        CustomerDetailPage detailPage = new CustomerDetailPage();
        detailPage.WaitUntilObjectClickable(detailPage.editServicesLinkBy);
        detailPage.WaitUntilObjectDisappear(detailPage.spinnerBy);
        detailPage.WaitUntilObjectClickable(detailPage.editServicesLinkBy);

        detailPage.editServicesLink_element.click();
        ServicesPage srv = new ServicesPage();
        srv.MTD_element.click();
        srv.OK_element.click();
        detailPage.WaitUntilObjectClickable(detailPage.editServicesLinkBy);
        detailPage.editServicesLink_element.click();
        srv.MTD_element.click();
        srv.OK_element.click();

    }

    public void SelectCustomer(String customerName){

        UpperMenu up = new UpperMenu();
        up.WaitUntilObjectClickable(up.customerSelectorBy);
        up.customerSelector_element.click();

        up.WaitUntilObjectClickable(up.searchTextBy);
        up.searchText_element.clear();
        up.searchText_element.sendKeys(customerName);

        up.WaitUntilObjectClickable(up.customerNameBy);
        up.customerName.click();

    }

    public void DeleteAllDownloads(){


        FileCabinet fc = new FileCabinet();
        fc.TrustwaveEndpointFolder_element.click();

        fc.WaitUntilObjectClickable(fc.refreshButtonBy);
        fc.WaitUntilPageLoad();
        fc.WaitUntilObjectDisappear(fc.spinnerBy);


        while (fc.GetFirstThreeDotsIcon().size()>0 ) {
            fc.WaitUntilObjectClickable(fc.threeDotsIconBy);
            fc.threeDotsIcon_element.click();
            fc.WaitUntilObjectClickable(fc.removeMenuItemBy);
            fc.WaitUntilPageLoad();
            fc.WaitUntilObjectClickable(fc.removeMenuItemBy);
            fc.removeMenuItem_element.click();
            fc.WaitUntilObjectClickable(fc.removeButtonConfirmBy);
            fc.removeButtonConfirm_element.click();
        }

    }

    public void DownloadFilesFromTrustWaveEndPointFolder(String fileToAppearTimeoutString, String fileStoredAndVirusScanTimeoutString) throws InterruptedException {
        int fileToAppearTimeout = Integer.parseInt(fileToAppearTimeoutString);
        int fileStoredAndVirusScanTimeout = Integer.parseInt(fileStoredAndVirusScanTimeoutString);

        FileCabinet fc = new FileCabinet();

        fc.TrustwaveEndpointFolder_element.click();

        boolean found = false;
        for (int count=0; count <fileToAppearTimeout; count+=5) {
            fc.refreshButton_element.click();

            Thread.sleep(5000);

            fc.WaitUntilObjectClickable(fc.refreshButtonBy);
            fc.WaitUntilObjectDisappear(fc.spinnerBy);
            fc.WaitUntilObjectClickable(fc.refreshButtonBy);


            if ( fc.IsElementExist(FileCabinet.endPointExeBy)) {
                found = true;
                break;
            }


        }

        if(!found)
            org.testng.Assert.fail("Download failed. Installation file did not appeared at File Cabinet after timeout: " + fileToAppearTimeoutString+ " seconds. See screenshot or video links below" );



        boolean errorMessageAppear = true;
        for (int count=0; count <fileStoredAndVirusScanTimeout; count+=5) {

            fc.WaitUntilObjectClickable(fc.endPointExeBy);
            fc.TrustwaveEndpointExe_element.click();
            if (! fc.IsElementExist(fc.fileUnableToBeDownloadedBy) ) {
                errorMessageAppear=false;
                break;
            }

            fc.errorMessageOKButton_element.click();

            fc.refreshButton_element.click();

            Thread.sleep(5000);

            fc.WaitUntilObjectClickable(fc.refreshButtonBy);
            fc.WaitUntilObjectDisappear(fc.spinnerBy);
            fc.WaitUntilObjectClickable(fc.refreshButtonBy);


        }

        if(errorMessageAppear)
            org.testng.Assert.fail("Message appears: File is still being processed (virus scanned and stored). after timeout: " + fileStoredAndVirusScanTimeoutString+ " seconds. See screenshot or video links below" );



    }


    public void CreateAndCleanDownloadFolder() throws IOException {
        File nepFolder = new File(PropertiesFile.readProperty("DownloadFolder"));
        //Creating the directory
        if (! nepFolder.exists() || ! nepFolder.isDirectory() ) {
            boolean bool = nepFolder.mkdir();
            if ( ! bool)
                org.testng.Assert.fail("Could not create download directory: " + nepFolder );

        }

        try {
            FileUtils.cleanDirectory(nepFolder);
        } catch (Exception e) {
            org.testng.Assert.fail("Could delete all old files from the following directory: " + nepFolder + "\n" + e.toString() );
        }

    }

    public void VerifyFilesExist (int timeoutSeconds) throws IOException, InterruptedException {
        File nepFolder = new File(PropertiesFile.readProperty("DownloadFolder"));
        //String [] expected = {"client.pem" , "client_key.pem" , "TrustwaveEndpoint.exe"};
        String [] expected = {"TrustwaveEndpoint.exe"};

        boolean foundFiles =false;
        String [] filesArr = nepFolder.list();
        for(int count = 0; count<timeoutSeconds; count+=5) {
            Thread.sleep(5000);
            filesArr = nepFolder.list();
            if (Arrays.equals(nepFolder.list(), expected)) {
                foundFiles = true;
                break;
            }
        }
        if (foundFiles == false)
                org.testng.Assert.fail("Could not find expected installation files at folder: " + nepFolder + "\n"+ "Found files:   " + Arrays.toString(filesArr)
                    + "\n" + "Expected files:" + Arrays.toString(expected));

    }

    public void VerifyInstallerSignature() throws IOException {
        final String expectedVerified1 = "Verified:\tA certificate chain processed, but terminated in a root certificate which is not trusted by the trust provider";
        final String expectedVerified2 = "Verified:\tSigned ";

        String sigcheckPath = "c:\\Selenium\\Utils\\sigcheck.exe";

        File file = new File(sigcheckPath);
        if( ! file.exists())
            org.testng.Assert.fail("Signature check failed. Could not find signature check utility at: " + sigcheckPath);

        String command = sigcheckPath + " -nobanner -a ";
        String installerLocation = PropertiesFile.readProperty("DownloadFolder");
        installerLocation += "\\TrustwaveEndpoint.exe";
        command += installerLocation;
        String result = execCmd(command , false);
        if (  ! (result.contains(expectedVerified1)  || result.contains(expectedVerified2) ) )
            org.testng.Assert.fail("Failed to verify siganture of file: " + installerLocation + "\nCheck Signature output:\n" + result
                    + "\nExpected check signature result could be one of the following:\n" + expectedVerified1 + "\nOr:\n" + expectedVerified2);

        int startLine = result.indexOf("Binary Version");
        int endLine = result.indexOf("\n", startLine);
        String version = result.substring(startLine,endLine);
        JLog.logger.info("End Point Agent " + version);

    }

    public void InstallEndPoint(int timeout) throws IOException, InterruptedException {

        String installerLocation = PropertiesFile.readProperty("DownloadFolder");
        installerLocation += "\\TrustwaveEndpoint.exe";
        //String host = "DS_HOST_NAME=" + PropertiesFile.getCurrentClusterNepHost() ;
        String command =  installerLocation + " /q " ;//+ host;
        String result = execCmd(command, false);
        boolean found = false;
        for(int count = 0; count < timeout ; count+=5) {
            Thread.sleep(5000);
            if (EndPointServiceExist()) {
                found=true;
                break;
            }
        }

        if(!found)
            org.testng.Assert.fail("Trustwave Endpoint installation failed. Trustwave Endpoint Agent Service was not found on services list");

    }

    public void UnInstallEndPoint(int timeout) throws IOException, InterruptedException {


        String installerLocation = PropertiesFile.readProperty("DownloadFolder");
        installerLocation += "\\TrustwaveEndpoint.exe";
        String command =  installerLocation + " /q /uninstall" ;
        execCmd(command, true);

        //execCmd("wmic product where \"description='Trustwave Endpoint Agent (64bit)' \" uninstall",true);

        boolean found = true;
        int count =0;
        for(count = 0; count <= timeout ; count+=5) {
            Thread.sleep(5000);
            if (!EndPointServiceExist()) {
                found=false;
                break;
            }
        }

        if(found)
            org.testng.Assert.fail("Uninstall failed. Trustwave Endpoint Agent Service still found after timeout(sec): " + Integer.toString(timeout));


        final String installationFolder = "C:\\Program Files\\Trustwave\\NEPAgent";
        File file = new File(installationFolder);

        found = true;
        for(; count <= timeout ; count+=5) {
            if (!file.exists()) {
                found=false;
                break;
            }
            Thread.sleep(5000);
            //JLog.logger.debug("Found Folder!!!");

        }

        if(found)
            org.testng.Assert.fail("Uninstall failed. Trustwave Endpoint installation folder not deleted  after timeout(sec): " + Integer.toString(timeout) + "   Installation folder: " + installationFolder);


        found = true;
        final String installationProcess ="TrustwaveEndpoint.exe";
        for(; count <= timeout ; count+=5) {

            String result = execCmd("tasklist", false);
            if (! result.contains(installationProcess)) {
                found= false;
                break;
            }
            //JLog.logger.debug("Found Process!!!");
            Thread.sleep(5000);
        }

        if(found)
            org.testng.Assert.fail("Uninstall failed. Trustwave installation process is still active after timeout (sec): " +  Integer.toString(timeout) + "   Installation process: " + installationProcess);


    }

    public void ReplaceEndPointFilesAndRestartService(int timeout) throws IOException, InterruptedException {

        execCmd("Net stop NepaService", true);

        boolean active = true;
        String result ="";
        for(int count = 0; count <= timeout ; count+=5) {
            Thread.sleep(5000);
            result = execCmd("sc query \"NepaService\"", false);
            if (result.contains("STOPPED")) {
                active = false;
                break;
            }
        }

        if(active)
            org.testng.Assert.fail("Failed to stop End Point service");

        String downloadLocation = PropertiesFile.readProperty("DownloadFolder");

        //Shortcut to program files is used to avoid space failure
        String clientKeyPemPath = downloadLocation + clientKeyPem;
        String command = "xcopy /Y " + clientKeyPemPath + " " + destinationFolder;
        execCmd(command, true);

        String clientPemPath = downloadLocation + clientPem;
        command = "xcopy /Y " + clientPemPath + " " + destinationFolder;
        execCmd(command, true);

        File file1 = new File(clientKeyPemPath);
        File file1Copied = new File(destinationFolder + clientKeyPem );
        File file2 = new File(clientPemPath);
        File file2Copied = new File(destinationFolder + clientPem );

        boolean fileCopied = false;
        for(int count = 0; count <= 30 ; count+=2) {
            Thread.sleep(2000);
            if (FileUtils.contentEquals(file1, file1Copied)  && FileUtils.contentEquals(file2, file2Copied)) {
                fileCopied = true;
                break;
            }
        }

        if (! fileCopied)
            org.testng.Assert.fail("Failed copy downloaded client_key.pem and client.pem to folder: " + destinationFolder );

        execCmd("Net start NepaService", true);

        active = false;
        for(int count = 0; count <= timeout ; count+=5) {
            Thread.sleep(5000);
            result = execCmd("sc query \"NepaService\"", false);
            if (result.contains("RUNNING")) {
                active = true;
                break;
            }
        }

        if(!active)
            org.testng.Assert.fail("Failed to start End Point service");

    }


    public void CheckEndPointActiveByDbJson(int timeout) throws IOException, InterruptedException {
        String text = "";

        File file = new File(dbJsonPath);

        boolean active = false;
        for(int count = 0; count < timeout ; count+=5) {
            if( file.exists()) {
                FileInputStream inputStream = new FileInputStream(dbJsonPath);
                text = IOUtils.toString(inputStream, Charset.defaultCharset());
                if (text.contains("\"EndpointId\": \"")  && text.contains("\"DsInitialHost\": ")) {
                    active = true;
                    break;
                }
            }

            Thread.sleep(5000);

        }

        if(! file.exists())
            org.testng.Assert.fail("Service is not connected - db.json file was not found at: " + dbJsonPath);

        if(! active)
            org.testng.Assert.fail("Service is not connected according to db.json file after timeout(sec): " + Integer.toString(timeout) + ". Failed to find in db.json: End Point ID  Or Host.\n"+ "db.json file content:\n"+text);

    }



    public boolean EndPointServiceExist() throws IOException {
        //String result = execCmd("net start | find \"Trustwave Endpoint Agent Service\"");
        String result = execCmd("net start", false);
        if (result.contains("Trustwave Endpoint Agent Service"))
            return true;
        else
            return false;

    }

}
