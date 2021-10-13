//package declaration
package ch.nolix.techapi.objectdataapi.structuraldataapi;

//interface
public interface ISingleValue<SV extends ISingleValue<SV, V>, V> extends IProperty<SV> {
	
	//method declaration
	V getRefValue();
	
	//method declaration
	boolean hasValue();
}
