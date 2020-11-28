//package declaration
package ch.nolix.system.entity;

import ch.nolix.common.attributeapi.Named;

//class
public final class ValueClassBox<V> implements Named {
	
	//attributes
	private final Class<V> valueClass;
	
	//constructor
	ValueClassBox(final Class<V> valueClass) {		
		this.valueClass = valueClass;
	}
	
	//method
	@Override
	public String getName() {
		return valueClass.getSimpleName();
	}
	
	//method
	public Class<V> getValueClass() {
		return valueClass;
	}
}
