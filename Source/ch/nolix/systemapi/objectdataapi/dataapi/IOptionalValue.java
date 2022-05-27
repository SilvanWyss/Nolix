//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.core.skilluniversalapi.Clearable;

//interface
public interface IOptionalValue<IMPL, V> extends Clearable, IBaseValue<IMPL, V> {
	
	//method declaration
	V getRefValue();
	
	//method declaration
	void setValue(V value);
	
	//method declaration
	void setValueFromStringRepresentation(String string);
}
