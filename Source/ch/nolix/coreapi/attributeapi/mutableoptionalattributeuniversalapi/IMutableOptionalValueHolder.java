//package declaration
package ch.nolix.coreapi.attributeapi.mutableoptionalattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeuniversalapi.IOptionalValueHolder;

//interface
public interface IMutableOptionalValueHolder<V> extends IOptionalValueHolder<V> {
	
	//method declaration
	void removeValue();
	
	//method declaration
	void setValue(V value);
}
