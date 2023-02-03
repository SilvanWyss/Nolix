//package declaration
package ch.nolix.coreapi.attributeapi.mutableoptionalattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeuniversalapi.OptionalValued;

//interface
public interface OptionalValuable<V> extends OptionalValued<V> {
	
	//method declaration
	void removeValue();
	
	//method declaration
	void setValue(V value);
}
