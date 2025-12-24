package practiceTest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.gneric.FileUtility.XL_Utility;

public class GetProductInfoTest {

	@Test(dataProvider = "data1")
	public void getProductInfo(String brand, String Model) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://www.amazon.in/");
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brand,Keys.ENTER);
		String price = driver.findElement(By.xpath("//span[text()='"+Model+"']/ancestor::div[@class='a-section a-spacing-small a-spacing-top-small']//span[@class='a-price-whole']")).getText();
		
		System.out.println(price);
	}
	

	@DataProvider(name = "data1")
	public Object[][] data() throws Exception, Exception {
		XL_Utility xu = new XL_Utility();
		int rows = xu.getRowCount("Sheet1");
		
		Object[][] data= new Object[rows-1][2];
		
		for(int i=1; i<rows-1; i++) {
			data[i-1][0] = xu.getDataFromExcel("Sheet1", i, 0);
			data[i-1][1] = xu.getDataFromExcel("Sheet1", i, 1);
		}
//		data[0][0] = "Iphone";
//		data[0][1] = "iPhone 15 (128 GB) - Black";
//		
//		data[1][0] = "Iphone";
//		data[1][1] = "iPhone 16 Pro Max 512 GB: 5G Mobile Phone with Camera Control, 4K 120 fps Dolby Vision and a Huge Leap in Battery Life. Works with AirPods; Natural Titanium";
//		
//		data[2][0] = "Iphone";
//		data[2][1] = "iPhone 17 Pro 256 GB: 15.93 cm (6.3″) Display with Promotion up to 120Hz, A19 Pro Chip, Breakthrough Battery Life, Pro Fusion Camera System with Center Stage Front Camera; Deep Blue";
		
		return data;
	}
}
