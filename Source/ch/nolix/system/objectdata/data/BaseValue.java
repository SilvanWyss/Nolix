//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.techapi.objectdataapi.dataapi.IBaseValue;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;

//class
public abstract class BaseValue<V> extends Property implements IBaseValue<DataImplementation, V> {
	
	//constructor
	public BaseValue(final String name) {
		super(name);
	}
	
	//method
	@Override
	public boolean canReference(final IEntity<DataImplementation> entity) {
		return false;
	}
	
	//method
	@Override
	public boolean canReferenceBack(final IEntity<DataImplementation> entity) {
		return false;
	}
	
	//method
	@Override
	public boolean canReferenceBackEntityOfType(final Class<IEntity<DataImplementation>> type) {
		return false;
	}
	
	//method
	@Override
	public boolean canReferenceEntityOfType(final Class<IEntity<DataImplementation>> type) {
		return false;
	}
	
	//method
	@Override
	public boolean references(final IEntity<DataImplementation> entity) {
		return false;
	}
	
	//method
	@Override
	public boolean referencesBack(final IEntity<DataImplementation> entity) {
		return false;
	}
}
