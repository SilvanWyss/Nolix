//package declaration
package ch.nolix.system.objectdatabase.database;

import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseValue;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;

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
