//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//interface
public interface IValue<P extends IProperty<P>, V> extends IBaseValue<P, V> {
	
	//method declaration
	V getRefValue();
	
	//method declaration
	boolean hasValue();
}
