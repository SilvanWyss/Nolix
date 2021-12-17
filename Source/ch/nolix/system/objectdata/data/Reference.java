//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.system.objectdata.propertyhelper.ReferenceHelper;
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.IReference;
import ch.nolix.techapi.objectdataapi.propertyhelperapi.IReferenceHelper;

//class
public final class Reference<E extends IEntity<DataImplementation>> extends BaseReference<E>
implements IReference<DataImplementation, E> {
	
	//static attribute
	private static final IReferenceHelper referenceHelper = new ReferenceHelper();
	
	//optional attributes
	private String referencedEntityId;
	
	//constructor
	public Reference(final String name) {
		//TODO
		super(name, null);
	}
	
	//method
	@Override
	public boolean canReference(final IEntity<DataImplementation> entity) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//method
	@Override
	public PropertyType getType() {
		return PropertyType.REFERENCE;
	}
	
	//method
	@Override
	public boolean references(final IEntity<DataImplementation> entity) {
		//TODO: Implement.
		return false;
	}
	
	//method
	@Override
	public E getRefEntity() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public boolean referencesEntity() {
		return (referencedEntityId != null);
	}
	
	//method
	@Override
	public IReference<DataImplementation, E> setEntity(final E entity) {
		//TODO: Implement.
		return this;
	}
	
	//method
	private String getReferencedEntityId() {
		
		referenceHelper.assertReferencesEntity(this);
		
		return referencedEntityId;
	}
}
