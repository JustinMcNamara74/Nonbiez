package com.NonbieSoft.nonbiez.components;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.NonbieSoft.engine.IComponent;
import com.NonbieSoft.engine.JSONLoader;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


// parse Json

public class TileMapComponent implements IComponent{
	@SuppressWarnings("rawtypes")
	private Map jsonData;
	private SpriteBatch spriteBatch = new SpriteBatch();
	public int width;
	public int height;
	public int tileWidth;
	public int tileHeight;
	
	public HashMap<String, Map> tileSets; 
	public TextureRegion[] tiles;
	public List<Tile> layerData = new LinkedList<Tile>(); 
	
	public TileMapComponent(String fileName){
		
		tileSets = new HashMap<String, Map>();
		jsonData = JSONLoader.loadFile(fileName);
		
		height = JSONLoader.getInt(jsonData, "height");
		width = JSONLoader.getInt(jsonData, "width");
		tileHeight = JSONLoader.getInt(jsonData, "tileheight");
		tileWidth = JSONLoader.getInt(jsonData, "tilewidth");
		
		List tempList = JSONLoader.getList(jsonData, "tilesets");
		int numOfTiles = 0;
		
		
		for(Object t: tempList){
			Map m = (Map)t;
			tileSets.put(JSONLoader.getString(m, "image"), m);
			int imageHeight = JSONLoader.getInt(m, "imageheight");
			int imageWidth= JSONLoader.getInt(m, "imagewidth");
			int tileHeight = JSONLoader.getInt(m, "tileheight");
			int tileWidth= JSONLoader.getInt(m, "tilewidth");
							// # of tiles in rows       cols
			numOfTiles += (imageHeight/tileHeight)*(imageWidth/tileWidth);
				
		}
		tiles = new TextureRegion[numOfTiles];
		
		tempList = JSONLoader.getList(jsonData, "layers");
		for(Object l : tempList){
			Map m = (Map) l;
			int height = JSONLoader.getInt(m, "height");
			int width= JSONLoader.getInt(m, "width");
			//  this is where it gets tricky!
			
			List data = JSONLoader.getList(m, "data");
			int i = 0;
			
			for(Object d : data){
			
				int tile = Integer.parseInt((String)d);
				if(tile != 0){
					int tileX = (i % width);
					int tileY = (i / width);
					layerData.add(new Tile(tile -1, tileX, tileY));
					
				}
			}
			
		}
		
		
	}
	
	
	public void addTileSetImage(String tileSetKey, String imageFile){
		Map m = tileSets.get(tileSetKey);
		
		AnimatedSpriteComponent asc = new AnimatedSpriteComponent(imageFile, 
				JSONLoader.getInt(m, "tileheight"), JSONLoader.getInt(m, "tilewidth")); 
		
		int index = JSONLoader.getInt(m, "firstgid")-1;
		
		// copied contents of index array into tiles array
		for(int i = 0; i< asc.frames.length; i++){
			tiles[index +i] = asc.frames[i];
			
		}
		
	}
	// how to draw with libGDX
	public void render(OrthographicCamera cam){
		// start rendering
		spriteBatch.setProjectionMatrix(cam.combined);
		spriteBatch.begin();
		
		for(Tile t:  layerData){
			int oddRow = t.tileY % 2;
			
			
			//calc pixel coords based on tile
			float x = t.tileX * tileWidth;
			float y = t.tileY * (tileHeight/2);
			
			spriteBatch.draw(tiles[t.tile], x, y);
			
		}
		
		
		spriteBatch.end();
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	class Tile{
		public int tile, tileX, tileY;
		
		public Tile(int tile, int tileX, int tileY){
			this.tile  = tile;
			this.tileX = tileX;
			this.tileY = tileY;
		}
	}
	
}

