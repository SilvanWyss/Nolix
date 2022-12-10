//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseValue;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;

//class
public abstract class BaseValue<V> extends Property implements IBaseValue<DataImplementation, V> {
	
	//method
	@Override
	public final boolean canReferencesBackProperty(final IProperty<?> property) {
		return false;
	}
	
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
	public boolean referencesBackProperty(final IProperty<?> property) {
		return false;
	}
	
	//method
	@Override
	public final boolean referencesUninsertedEntity() {
		return false;
	}
	
	//method
	@Override
	final void internalUpdateProbableBackReferencesWhenIsNew() {
		//Does nothing.
	}
}
