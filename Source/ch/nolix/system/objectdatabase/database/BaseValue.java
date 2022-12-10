//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseValue;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;

//class
public abstract class BaseValue<V> extends Property implements IBaseValue<DataImplementation, V> {
	
	//method
	@Override
	public final IContainer<IProperty<DataImplementation>> getRefReferencingProperties() {
		return new ImmutableList<>();
	}
	
	//method
	@Override
	public final boolean referencesEntity(final IEntity<?> entity) {
		return false;
	}
	
	//method
	@Override
	public final boolean referencesBackEntity(final IEntity<?> entity) {
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
