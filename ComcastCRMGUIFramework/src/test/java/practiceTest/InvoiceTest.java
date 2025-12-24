package practiceTest;

import org.testng.annotations.Test;

import com.crm.comcast.baseTest.BaseTest;

public class InvoiceTest extends BaseTest{

	@Test
	public void createInvoiceTest() {
		System.out.println("Execute createInvoiceTest");
		System.out.println(driver.getTitle());
	}
	
	@Test
	public void createInvoiceWithContactTest() {
		System.out.println("Execute createInvoiceWithContactTest");
	}
}
