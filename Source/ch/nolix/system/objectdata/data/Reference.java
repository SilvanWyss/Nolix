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
	
	//static method
	public static <E2 extends Entity> Reference<E2> forEntity(final Class<E2> type) {
		return new Reference<>(type.getSimpleName());
	}
	
	//static method
	public static Reference<GeneralEntity> forEntityWithTableName(final String tableName) {
		return new Reference<>(tableName);
	}
	
	//optional attributes
	private String referencedEntityId;
	
	//constructor
	private Reference(final String referencedTableName) {
		super(referencedTableName);
	}
	
	//method
	@Override
	public String getEntityId() {
		
		referenceHelper.assertIsNotEmpty(this);
		
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
		return PropertyType.REFERENCE;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return (referencedEntityId == null);
	}
	
	//method
	@Override
	public boolean isMandatory() {
		return true;
	}
	
	//method
	@Override
	public boolean references(final IEntity<DataImplementation> entity) {
		return
		containsAny()
		&& entity != null
		&& getEntityId().equals(entity.getId());
	}
	
	//method
	@Override
	public boolean referencesUninsertedEntity() {
		return
		containsAny()
		&& !getRefEntity().knowsParentTable();
	}
	
	//method
	@Override
	public void setEntity(final E entity) {
		
		referenceHelper.assertCanSetGivenEntity(this, entity);
		
		updateStateForSetEntity(entity);
		
		internalSetParentEntityAsEdited();
		
		updateRecordForSetEntity(entity);
	}
	
	//method
	@Override
	void internalSetOrClearDirectlyFromContent(final Object content) {
		referencedEntityId = (String)content;		
	}
	
	//method
	private void updateRecordForSetEntity(final E entity) {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAndSchemaAdapter().updateRecordOnTable(
				getParentEntity().getParentTableName(),
				referenceHelper.createRecordUpdateDTOForSetEntity(this, entity)
			);
		}
	}
	
	//method
	private void updateStateForSetEntity(final E entity) {
		referencedEntityId = entity.getId();
	}
}
