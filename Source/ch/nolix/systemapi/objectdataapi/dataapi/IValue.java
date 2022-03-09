//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//interface
public interface IValue<IMPL, V> extends IBaseValue<IMPL, V> {
	
	//method declaration
	V getRefValue();
	
	//method declaration
	void setValue(V value);
	
	//method declaration
	void setValueFromStringRepresentation(String string);
}
