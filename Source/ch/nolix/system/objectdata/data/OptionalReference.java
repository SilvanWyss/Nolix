//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.system.objectdata.propertyhelper.OptionalReferenceHelper;
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.IOptionalReference;
import ch.nolix.techapi.objectdataapi.propertyhelperapi.IOptionalReferenceHelper;

//class
public final class OptionalReference<E extends IEntity<DataImplementation>> extends BaseReference<E>
implements IOptionalReference<DataImplementation, E> {
	
	//static attribute
	private static final IOptionalReferenceHelper optionalReferenceHelper = new OptionalReferenceHelper();
	
	//optional attribute
	private String referencedEntityId;
	
	//constructor
	public OptionalReference(final String name) {
		//TODO
		super(name, null);
	}
	
	//method
	@Override
	public boolean canReference(final IEntity<DataImplementation> entity) {
		return
		entity != null
		&& getReferencedTable().getEntityClass() == entity.getClass();
	}
	
	//method
	public void clear() {
		if (containsAny()) {
			clearWhenContainsAny();
		}
	}
	
	//method
	@Override
	public String getEntityId() {
		
		optionalReferenceHelper.assertIsNotEmpty(this);
		
		return referencedEntityId;
	}
	
	//method
	@Override
	public E getRefEntity() {
		return getReferencedTable().getRefEntityById(getEntityId());
	}
	
	//method
	@Override
	public PropertyType getType() {
		return PropertyType.OPTIONAL_REFERENCE;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return (referencedEntityId == null);
	}
	
	//method
	@Override
	public boolean references(IEntity<DataImplementation> entity) {
		return
		containsAny()
		&& entity != null
		&& getEntityId().equals(entity.getId());
	}
	
	//method
	private void clearWhenContainsAny() {
		//TODO: Implement.
	}
}
