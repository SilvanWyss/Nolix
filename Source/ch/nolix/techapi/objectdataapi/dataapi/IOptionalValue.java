//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.skillapi.Clearable;

//interface
public interface IOptionalValue<P extends IProperty<P>, V> extends Clearable, IBaseValue<P, V> {
	
	//method declaration
	V getRefValue();
	
	//method declaration
	boolean hasValue();
}
