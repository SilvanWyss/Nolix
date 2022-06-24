//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.core.skilluniversalapi.Clearable;
import ch.nolix.coreapi.containerapi.IContainer;

//interface
public interface IMultiValue<
	IMPL,
	V
> extends Clearable, IBaseValue<IMPL, V> {
	
	//method declaration
	void addValue(V value);
	
	//method declaration
	IContainer<V> getRefValues();
}
