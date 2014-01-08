package com.NonbieSoft.nonbiez.components;

import java.util.Map;

import com.NonbieSoft.engine.IComponent;
import com.NonbieSoft.engine.JSONLoader;


// parse Json

public class TileMapComponent implements IComponent{
	@SuppressWarnings("rawtypes")
	private Map jsonData;
	public int width;
	public int height;

	
	public TileMapComponent(String fileName){
		
		jsonData = JSONLoader.loadFile(fileName);
		@SuppressWarnings("rawtypes")
		Map root = (Map)jsonData.get("root");
		
		height = JSONLoader.getInt(root, "height");
		width = JSONLoader.getInt(root, "width");
		
	}
	

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
