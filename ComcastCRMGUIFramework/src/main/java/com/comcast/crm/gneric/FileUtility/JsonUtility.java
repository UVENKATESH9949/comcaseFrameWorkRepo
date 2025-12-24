package com.comcast.crm.gneric.FileUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonUtility {

	public String getDataFromJsonFile(String key) throws Exception {
		String path = "./ConfigAppData/commondata.json";
		FileReader fis = new FileReader(new File(path));
		
		JSONParser parser = new JSONParser();
		JSONObject map =(JSONObject) parser.parse(fis);
		
		return (String) map.get(key);
	}
}
