package glymed.pageobjects.manager;

import com.glymed.commonFunctions.CommonFunction;
import glymed.pageobjects.loginandregistration.CheckOutPage;
import glymed.pageobjects.loginandregistration.DashboardPage;
import glymed.pageobjects.loginandregistration.ProductsPage;
import org.openqa.selenium.WebDriver;
import glymed.pageobjects.loginandregistration.LoginAndRegistrationFlowPages;

public class PageObjectManager {

    private final WebDriver driver;

    CommonFunction comfun;
    private LoginAndRegistrationFlowPages loginAndRegistrationFlowPages;
    private DashboardPage dashboardPage;
    private ProductsPage productsPage;
    private CheckOutPage checkOutPage;


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
}