package com.NonbieSoft.engine;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class EntityManager {
	
	public HashMap<String, List<Entity>> tagHashMap = new HashMap<String, 
			List<Entity>>();
	
	public HashMap<Class<?>, HashMap<Entity, List<IComponent>>> componentHashMap 
	= new HashMap<Class<?>, HashMap<Entity, List<IComponent>>>();

	
	public Entity createEntity(String name, String tag) {
		Entity newEntity = new Entity(name, tag);

		// Check if tag exists in hashmap
		if (!tagHashMap.containsKey(tag)) {
			LinkedList<Entity> entityList = new LinkedList<Entity>();
			tagHashMap.put(tag, entityList);
		}

		tagHashMap.get(tag).add(newEntity);

		return newEntity;
	}
	
	
	public void removeEntity(Entity en){
		
		tagHashMap.get(en.tag).remove(en);
		for(Class<?> t : componentHashMap.keySet()){
			if(componentHashMap.get(t).containsKey(en)){
				componentHashMap.get(t).remove(en);
			}
		}
		
		
	}

	
	
	public void addComponentToEntity(Entity newEntity, IComponent comp) {
		if (!componentHashMap.containsKey(comp.getClass()))
			componentHashMap.put(comp.getClass(),
					new HashMap<Entity, List<IComponent>>());

		HashMap<Entity, List<IComponent>> tempMap = componentHashMap.get(comp
				.getClass());

		if (!tempMap.containsKey(newEntity)) {
			tempMap.put(newEntity, new LinkedList<IComponent>());
		}
		tempMap.get(newEntity).add(comp);
	}

	
	
	public void removeComponentFromEntity(Entity newEntity, IComponent comp) {
		if (componentHashMap.containsKey(comp.getClass())){
			if(componentHashMap.get(comp.getClass()).containsKey(newEntity)){
				componentHashMap.get(comp.getClass()).get(newEntity).remove(comp);
			}
		}
		
	}
	
	
	public List<IComponent> getComponents(Entity newEntity, IComponent comp){
		if (componentHashMap.containsKey(comp.getClass())){
			if(componentHashMap.get(comp.getClass()).containsKey(newEntity)){
					return componentHashMap.get(comp.getClass()).get(newEntity);
			}
		}
		return null;
		
	}
	
	
	public Set<Entity> getEntitiesByComponentType(Class<?> type){
		
		if(componentHashMap.containsKey(type))
			return componentHashMap.get(type).keySet();
		
		return null;
		
	}
	
	

}
