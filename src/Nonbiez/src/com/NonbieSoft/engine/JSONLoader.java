package com.NonbieSoft.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
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
	
		JsonParserFactory factory=JsonParserFactory.getInstance();
		JSONParser parser=factory.newJsonParser();
		FileHandle file = Gdx.files.internal(fileName);
		return parser.parseJson(file.readString());
		
	}
	
}
