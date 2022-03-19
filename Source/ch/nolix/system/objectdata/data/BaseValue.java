//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseValue;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;

//class
public abstract class BaseValue<V> extends Property implements IBaseValue<DataImplementation, V> {
	
	//method
	@Override
	public final boolean references(final IEntity<?> entity) {
		return false;
	}
	
	//method
	@Override
	public final boolean referencesBack(final IEntity<?> entity) {
		return false;
	}
	
	//method
	@Override
	public final boolean referencesUninsertedEntity() {
		return false;
	}
}
