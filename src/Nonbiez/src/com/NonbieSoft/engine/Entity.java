package com.NonbieSoft.engine;

import java.util.LinkedList;

public class Entity {

	private EntityManager _em;
	
	public String name;
	public String tag;
	
	public Entity(String name, String tag, EntityManager _em){
		this.name = name;
		this.tag = tag;
		this._em = _em;
	}

	public IComponent addComponent(IComponent comp) {
		return _em.addComponentToEntity(this, comp);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends IComponent> T getComponent(Class<? extends IComponent> componentType) {
		return (T) _em.getComponent(this, componentType);
	}
	
	public LinkedList<IComponent> getComponents(Class<?> componentType) {
		return _em.getComponents(this, componentType);
	}
}

