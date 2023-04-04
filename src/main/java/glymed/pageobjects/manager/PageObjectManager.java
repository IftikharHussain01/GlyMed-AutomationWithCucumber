package glymed.pageobjects.manager;

import com.glymed.commonFunctions.CommonFunction;
import glymed.pageobjects.loginandregistration.*;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {

    private final WebDriver driver;

    CommonFunction comfun;
    private LoginAndRegistrationFlowPages loginAndRegistrationFlowPages;
    private DashboardPage dashboardPage;
    private ProductsPage productsPage;
    private CheckOutPage checkOutPage;
    private ProductSearchPage productSearchPage;
    private ExfoliatorProductPurchase exfoliatorProductPurchase;
    private SerumProductPage serumProductPage;
    private MoisturizerProductPage moisturizerProductPage;


    public PageObjectManager(WebDriver driver, CommonFunction comfun) {
        this.driver = driver;
        this.comfun = comfun;
    }

    public LoginAndRegistrationFlowPages getLoginAndRegistrationFlowPages() {
        return (loginAndRegistrationFlowPages == null) ? loginAndRegistrationFlowPages = new LoginAndRegistrationFlowPages(driver, comfun) : loginAndRegistrationFlowPages;
    }

    public DashboardPage getDashboardPage() {
        return (dashboardPage == null) ? dashboardPage = new DashboardPage(driver, comfun) : dashboardPage;
    }

    public ProductsPage getProductsPage() {
        return (productsPage == null) ? productsPage = new ProductsPage(driver, comfun) : productsPage;
    }

    public CheckOutPage getCheckOutPage() {
        return (checkOutPage == null) ? checkOutPage = new CheckOutPage(driver, comfun) : checkOutPage;
    }

    public ProductSearchPage getProductSearchPage(){
        return (productSearchPage == null) ? productSearchPage = new ProductSearchPage(driver,comfun) : productSearchPage;
    }

    public ExfoliatorProductPurchase getExfoliatorProductPurchase(){
        return (exfoliatorProductPurchase == null) ? exfoliatorProductPurchase = new ExfoliatorProductPurchase(driver,comfun) : exfoliatorProductPurchase;
    }

    public SerumProductPage getSerumProductPage(){
        return (serumProductPage == null) ? serumProductPage = new SerumProductPage(driver,comfun) : serumProductPage;
    }

    public MoisturizerProductPage getMoisturizerProductPage(){
        return (moisturizerProductPage == null) ? moisturizerProductPage = new MoisturizerProductPage(driver,comfun) : moisturizerProductPage;
    }
}