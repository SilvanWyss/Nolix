//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//interface
public interface IValue<IMPL, V> extends IBaseValue<IMPL, V> {
	
	//method declaration
	V getRefValue();
	
	//method declaration
	boolean hasValue();
}
