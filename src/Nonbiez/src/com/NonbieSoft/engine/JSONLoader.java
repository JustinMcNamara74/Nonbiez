package com.NonbieSoft.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.json.parsers.JSONParser;
import com.json.parsers.JsonParserFactory;

public class JSONLoader {
	
	// little helpers
	
	public static int getInt(Map map, String key){
		
		return Integer.parseInt((String)map.get(key));
	}
	

	public static String getString(Map map, String key){
		
		return (String)map.get(key);
	}
	

	public static List getList(Map map, String key){
		
		return (List)map.get(key);
	}
	
	@SuppressWarnings("rawtypes")
	public static Map loadFile(String fileName) {
		try{
		JsonParserFactory factory=JsonParserFactory.getInstance();
		JSONParser parser=factory.newJsonParser();
		return parser.parseJson(readFileAsString(fileName));
		}
		catch(IOException e){
			e.printStackTrace();
			
		}
		return null;
	}
	
	// reads in a file path as a string to send to the parseJson
	public static String readFileAsString(String filePath) throws IOException {
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }
	
	
	
	
	
	
	
	
	
}
