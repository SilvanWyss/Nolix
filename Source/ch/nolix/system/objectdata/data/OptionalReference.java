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
	
	//static method
	public static <E2 extends Entity> OptionalReference<E2> forEntity(final Class<E2> type) {
		return new OptionalReference<>(type.getSimpleName());
	}
	
	//static method
	public static OptionalReference<GeneralEntity> forEntityWithTableName(final String tableName) {
		return new OptionalReference<>(tableName);
	}
	
	//optional attribute
	private String referencedEntityId;
	
	//constructor
	private OptionalReference(final String referencedTableName) {
		super(referencedTableName);
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
	@Override
	public IOptionalReference<DataImplementation, E> setEntity(final E entity) {
		//TODO: Implement.
		return this;
	}
	
	//method
	private void clearWhenContainsAny() {
		//TODO: Implement.
	}
}
