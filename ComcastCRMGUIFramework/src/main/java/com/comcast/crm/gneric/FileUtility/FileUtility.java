package com.comcast.crm.gneric.FileUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class FileUtility {

	public String getDataFromPropertyFile(String key) throws Exception {
		String path = "./ConfigAppData/common.properties";
		FileInputStream fis =new FileInputStream(new File(path));
		
		Properties p = new Properties();
		p.load(fis);
		
		return p.getProperty(key);
	}
}
