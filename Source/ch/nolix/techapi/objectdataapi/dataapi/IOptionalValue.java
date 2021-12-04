//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.skillapi.Clearable;

//interface
public interface IOptionalValue<IMPL, V> extends Clearable, IBaseValue<IMPL, V> {
	
	//method declaration
	V getRefValue();
}
