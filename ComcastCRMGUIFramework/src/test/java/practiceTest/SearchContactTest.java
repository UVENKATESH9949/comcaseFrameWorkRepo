package practiceTest;
/**
 * test class for Contact module
 * @author Venkatesh
 */

import org.testng.annotations.Test;

import com.comcast.crm.objectRepositoryUtility.LoginPage;
import com.crm.comcast.baseTest.BaseTest;

public class SearchContactTest extends BaseTest{

	@Test
	public void searchContactTest() {
		/* Step 1 : Login to App  */
		LoginPage loginpage = new LoginPage(driver);
		loginpage.loginTOApp("admin", "admin");
	}
}
