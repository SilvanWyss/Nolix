//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//own imports
import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.skilluniversalapi.Clearable;

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
