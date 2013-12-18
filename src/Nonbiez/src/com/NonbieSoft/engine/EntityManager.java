package com.NonbieSoft.engine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EntityManager {
	
	public HashMap<String, LinkedList<Entity>> tagHashMap = new HashMap<String, 
			LinkedList<Entity>>();
	
	public HashMap<Class<?>, HashMap<Entity, LinkedList<IComponent>>> componentHashMap =
			new HashMap<Class<?>, HashMap<Entity, LinkedList<IComponent>>>();

	
	public Entity createEntity(String name, String tag) {
		Entity newEntity = new Entity(name, tag, this);

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

	
	
	public IComponent addComponentToEntity(Entity newEntity, IComponent comp) {
		if (!componentHashMap.containsKey(comp.getClass()))
			componentHashMap.put(comp.getClass(),
					new HashMap<Entity, LinkedList<IComponent>>());

		HashMap<Entity, LinkedList<IComponent>> tempMap = componentHashMap.get(comp
				.getClass());

		if (!tempMap.containsKey(newEntity)) {
			tempMap.put(newEntity, new LinkedList<IComponent>());
		}
		tempMap.get(newEntity).add(comp);
		
		return comp;
	}

	
	
	public void removeComponentFromEntity(Entity newEntity, IComponent comp) {
		if (componentHashMap.containsKey(comp.getClass())){
			if(componentHashMap.get(comp.getClass()).containsKey(newEntity)){
				componentHashMap.get(comp.getClass()).get(newEntity).remove(comp);
			}
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public <T extends IComponent> T getComponent(Entity entity, Class<? extends IComponent> type) {
		if (componentHashMap.containsKey(type)){
			if(componentHashMap.get(type).containsKey(entity)){
					return (T)componentHashMap.get(type).get(entity).getFirst();
			}
		}
		return null;
	}
	
	public LinkedList<IComponent> getComponents(Entity newEntity, Class<?> type){
		if (componentHashMap.containsKey(type)){
			if(componentHashMap.get(type).containsKey(newEntity)){
					return componentHashMap.get(type).get(newEntity);
			}
		}
		return null;
	}
	
	
	public Set<Entity> getEntitiesByComponentType(Class<?> type){
		
		if(componentHashMap.containsKey(type))
			return componentHashMap.get(type).keySet();
		
		return null;
		
	}
	
	
	// Calls dispose() on all components
	@SuppressWarnings("rawtypes")
	public void dispose() {
		Iterator it = componentHashMap.values().iterator();
		
		while (it.hasNext()) {
			Iterator entIt = ((Map)it.next()).values().iterator();
			
			while(entIt.hasNext()) {
				Iterator compIt = ((List)entIt.next()).iterator();
				
				while(compIt.hasNext()) {
					((IComponent)compIt.next()).dispose();
				}
			}
		}
	}
}
