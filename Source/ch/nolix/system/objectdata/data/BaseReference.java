//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.techapi.objectdataapi.dataapi.IBaseReference;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;

//class
public abstract class BaseReference<E extends IEntity<DataImplementation>> extends Property
implements IBaseReference<DataImplementation, E> {
	
	//constructor
	public BaseReference(final String name) {
		super(name);
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
	public boolean referencesBack(final IEntity<DataImplementation> entity) {
		return false;
	}
}
