package Servicos;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class Funcionalidades
{
    public static WebDriver Driver;
    //public static string SeleniumBaseUrl = ConfigurationManager.AppSettings["seleniumBaseUrl"];
    public static String BrowserConfig = "CHROME";
    public static Capabilities capabilities;
    public static DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    public static Timestamp commandTimeout;
    public static String AuxAmbiente;
    public static DesiredCapabilities dc = new DesiredCapabilities();
    public static String appiumServerUrl = "http://127.0.0.1:4723/wd/hub";

    public static void Inicializar(String url) throws Exception {
        //Variavel auxilidar que recebe o tipo de ambiente que serão executados os testes
        AuxAmbiente = "Local";
        BrowserConfig = "CHROME";

        if (Driver == null)
        {
            switch (AuxAmbiente)
            {
                case "Remoto":
                    switch ("CHROME")
                    {
                        case ("CHROME"):
                            ChromeOptions chromeOptions = new ChromeOptions();
                            chromeOptions.addArguments("--ignore-certificate-errors");
                            chromeOptions.addArguments("--allow-running-insecure-content");
                            chromeOptions.addArguments("--disable-extensions");
                            chromeOptions.addArguments("--start-maximized");
                            chromeOptions.addArguments("test-type");
                            chromeOptions.addArguments("test-type=browser");
                            chromeOptions.addArguments("--enable-precise-memory-info");
                            break;
                        case "FIREFOX":
                            //desiredCapabilities.SetCapability(CapabilityType.BrowserName, "firefox");
                            FirefoxOptions firefoxOptions = new FirefoxOptions();
                            firefoxOptions.addArguments("--ignore-certificate-errors");
                            firefoxOptions.addArguments("--allow-running-insecure-content");
                            firefoxOptions.addArguments("--disable-extensions");
                            firefoxOptions.addArguments("--start-maximized");
                            firefoxOptions.addArguments("test-type");
                            firefoxOptions.addArguments("test-type=browser");
                            firefoxOptions.addArguments("--enable-precise-memory-info");
                            break;
                        case "IE":
                            //desiredCapabilities.SetCapability(CapabilityType.BrowserName, "internet explorer");
                            InternetExplorerOptions internetexplorerOptions = new InternetExplorerOptions();
                            break;
                        case "EDGE":
                            EdgeOptions edgeoptions = new EdgeOptions();
                            //edgeoptions.AcceptInsecureCertificates = true;
                            //edgeoptions.AddAdditionalCapability("browserName","MicrosoftEdge");
                            break;
                        default:
                            throw new Exception("{Driver} Não é suportado.");
                    }
                    break;
                case "Local":
                    //switch (browser.ToUpper())
                    switch (BrowserConfig)
                    {
                        case "CHROME":
                            //ChromeOptions chromeCapabilities = new ChromeOptions();
                            //chromeCapabilities.EnableMobileEmulation("Laptop with HiDPI screen");
                            if (Driver == null){
                                Driver = new ChromeDriver();
                            }
                            break;
                        case "FIREFOX":
                            Driver = new FirefoxDriver();
                            break;
                        case "IE":
                            Driver = new InternetExplorerDriver();
                            break;
                        case "EDGE":
                            Driver = new EdgeDriver();
                            break;
                    }
                    break;
                case "Android":

                    dc.setCapability("platformName", "Android");
                    dc.setCapability("platform", "Android");
                    dc.setCapability("deviceName", "Galaxy_S7");
                    dc.setCapability("automationName", "uiautomator2");
                    dc.setCapability("appPackage", "br.gov.sp.saude.horamarcada");
                    dc.setCapability("appActivity", "br.gov.sp.saude.horamarcada.ui.activity.SplashActivity");
                    dc.setCapability("avdLaunchTimeout", "20000");
                    dc.setCapability("appWaitDuration", "30000");

                    // Skip the installation of io.appium.settings app and the UIAutomator 2 server.
                    dc.setCapability("newCommandTimeout", 9000);
                    //dc.setCapability("skipDeviceInitialization", true);
                    //dc.setCapability("skipServerInstallation", true);

                    // Skip Keyboard
                    dc.setCapability("unicodeKeyboard", true);
                    dc.setCapability("resetKeyboard", true);

                    Driver = new AppiumDriver(new URL(appiumServerUrl), dc) {
                    };
                    //Driver.navigate().to("http://dev.k8s.prodesp.sp.gov.br/reembolso#/home");
                    return;
            }

            Driver.manage().window().maximize();
            Driver.navigate().to(url);
        }
    }

    public static void FecharBrowser()
    {
        if (Driver != null)
        {
            Driver.quit();
            Driver.close();
            Driver = null;
        }
    }

    public static void Clicar(By Locator)
    {
        Driver.findElement(Locator).click();
    }

    public static void ClicarPorCoordenadas(int x, int y)
    {
        TouchAction touchAction = new TouchAction((PerformsTouchActions) Funcionalidades.Driver);
        touchAction.tap(PointOption.point(x, y)).perform();
    }

    //Enviar texto para um objeto
    public static void EnviarTexto(String Text, By Locator)
    {
        /*if (!Locator.toString().contains("body"))
        {
            Driver.findElement(Locator).clear();
        }*/
        Driver.findElement(Locator).clear();
        Driver.findElement(Locator).sendKeys(Text);
    }

    //Capturar o texto de um objeto
    public static String CapturarTexto(By Locator)
    {
        String ObjectText = Driver.findElement(Locator).getText();
        return ObjectText;
    }

    //Capturar o texto de um objeto
    public static String SelecionarOpcaoPorTexto(By Locator)
    {
        String ObjectText = Driver.findElement(Locator).getText();
        return ObjectText;
    }

    //Comparar textos
    public static void CompararTexto(String TextoExperado, String TextoAtual)
    {
        Assert.assertTrue(TextoExperado.equals(TextoAtual));
    }

    //Comparar textos
    public static void ObjetoContemTexto(String TextoExperado, String TextoAtual)
    {
        //Assert.assertTrue(TextoAtual.contains(TextoExperado));
    }

    //Selecionar uma opção dentro de um option
    public static void SelecionarOpcao(String OptionText, By Locator)
    {
        WebElement mySelectElement = Driver.findElement(Locator);
        Select dropdown= new Select(mySelectElement);
        dropdown.selectByVisibleText(OptionText);
    }

    //Tirar print da tela
    public static void PrintScreen()
    {
        String path;

        //Condição que verifica o ambiente de execução, dependendo do ambiente os prints são direcionados para pastas especificas
        if (AuxAmbiente.contains("Remoto"))
        {
            //Save the screenshot
            try {
                WebDriver augmentedDriver = new Augmenter().augment(Driver);
                File source = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
                path = "./target/screenshots/" + source.getName();
                FileUtils.copyFile(source, new File(path));
            }
            catch(IOException e) {
                path = "Failed to capture screenshot: " + e.getMessage();
            }
        }
        else
        {
            //Save the screenshot
            try {
                WebDriver augmentedDriver = new Augmenter().augment(Driver);
                File source = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
                path = "./target/screenshots/" + source.getName();
                FileUtils.copyFile(source, new File(path));
            }
            catch(IOException e) {
                path = "Failed to capture screenshot: " + e.getMessage();
            }
        }
    }

    //Apagar informações em campos
    public static void LimparCampo(By Locator)
    {
        Driver.findElement(Locator).clear();
    }

    //Verifica se o objeto está visivel
    public static boolean ObjetoEstaVisivel(By Locator)
    {
        try {
            Driver.findElement(Locator);
            return true;
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    //verifica se o objeto está habilitado
    public static boolean ObjetoEstaHabilitado(By Locator)
    {
        boolean status = Driver.findElement(Locator).isEnabled();
        return status;
    }

    //Espera o objeto carregar
    public static void EsperarObjetoCarregar(By Locator)
    {
        //Driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
        int secondsToWait = 10;
        WebDriverWait wait = new WebDriverWait(Driver, secondsToWait);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
    }

    public static void EsperarTextoDesaparecer(String texto, By locator)
    {
        int secondsToWait = 30;
        WebDriverWait wait = new WebDriverWait(Driver, secondsToWait);

        //var teste = Driver.FindElement(By.XPath("//*/table/tbody/tr/td")).Text;

        Object teste = Funcionalidades.CapturarTexto(locator);

        wait.until((ExpectedConditions.invisibilityOfElementWithText(locator, texto)));
    }



    //Verifica se o objeto desapareceu da tela
    public static void VerificarQdoObjetoDesaparecer(By Locator)
    {
        int secondsToWait = 60;
        WebDriverWait wait = new WebDriverWait(Driver,10);

        //wait.until((d -> d.findElement(Locator).isEnabled() & d.findElement(Locator).isDisplayed()));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(Locator));
    }

    //Esperar
    public static void Esperar() throws InterruptedException {
        Thread.sleep(300);
        Driver.manage().timeouts().pageLoadTimeout(900, TimeUnit.SECONDS);
        Driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
    }

    //Encerrar processo.
    public static void EncerrarProcesso() throws IOException, InterruptedException {

        Process proc = Runtime.getRuntime().exec ("tasklist.exe");
        InputStream procOutput = proc.getInputStream ();
        if (0 == proc.waitFor ()) {
            // TODO scan the procOutput for your data
        }
    }


    public static void CriarNovaPasta(String path)
    {
        File directory = new File(path);
        if (! directory.exists()){
            directory.mkdir();
        }
    }

    public static void Scroll()
    {
        new TouchAction((PerformsTouchActions) Driver).press(PointOption.point(550, 640)).waitAction().moveTo(PointOption.point(550, 60)).release().perform();
    }

}
