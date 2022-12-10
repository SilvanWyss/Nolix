//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.system.objectdatabase.propertyhelper.OptionalReferenceHelper;
import ch.nolix.system.sqlrawdata.databasedto.ContentFieldDTO;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IOptionalReferenceHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDTO;

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
	public static OptionalReference<BaseEntity> forEntityWithTableName(final String tableName) {
		return new OptionalReference<>(tableName);
	}
	
	//optional attribute
	private String referencedEntityId;
	
	//constructor
	private OptionalReference(final String referencedTableName) {
		super(referencedTableName);
	}
	
	//method
	@Override
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
	public boolean isMandatory() {
		return false;
	}
	
	//method
	@Override
	public boolean references(IEntity<?> entity) {
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
		&& !getRefEntity().belongsToTable();
	}
	
	//method
	@Override
	public void setEntity(final E entity) {
		
		assertCanSetEntity(entity);
		
		updateStateForSetEntity(entity);
		
		setAsEditedAndRunProbableUpdateAction();
	}
	
	//method
	@Override
	public void setEntityWithId(final String id) {
		
		final var entity = getReferencedTable().getRefEntityById(id);
		
		setEntity(entity);
	}
	
	//method
	@Override
	public IContentFieldDTO technicalToContentField() {
		
		if (isEmpty()) {
			return new ContentFieldDTO(getName());
		}
		
		return new ContentFieldDTO(getName(), getEntityId());
	}
	
	//method
	@Override
	void internalSetOrClearDirectlyFromContent(final Object content) {
		if (content == null) {
			referencedEntityId = null;
		} else {
			referencedEntityId = (String)content;
		}
	}
	
	//method
	@Override
	void internalUpdateWhenIsNewMultiProperty() {
		//Does nothing.
	}
	
	//method
	@Override
	void internalUpdateProbableBackReferencesWhenIsNew() {
		if (containsAny()) {
			updateProbableBackReferenceForSetOrAddedEntity(getRefEntity());
		}
	}
	
	//method
	private void assertCanClear() {
		optionalReferenceHelper.assertCanClear(this);
	}
	
	//method
	private void assertCanSetEntity(final E entity) {
		optionalReferenceHelper.assertCanSetGivenEntity(this, entity);
	}
	
	//method
	private void clearWhenContainsAny() {
		
		assertCanClear();
		
		updateProbableBackReferencingPropertyForClear();
		
		updateStateForClear();
		
		updateRealDatabaseForClear();
		
		setAsEditedAndRunProbableUpdateAction();
	}
	
	//method
	private void updateBackReferencingPropertyForClear(final IProperty<DataImplementation> backReferencingProperty) {
		switch (backReferencingProperty.getType()) {
			case BACK_REFERENCE:
				final var backReference = (BackReference<?>)backReferencingProperty;
				backReference.internalClear();
				break;
			case OPTIONAL_BACK_REFERENCE:
				final var optionalBackReference = (OptionalBackReference<?>)backReferencingProperty;
				optionalBackReference.internalClear();
				break;
			case MULTI_BACK_REFERENCE:
				//TODO: Implement.
				break;
			default:
				//Does nothing.
		}
	}
	
	//method
	private void updateProbableBackReferencingPropertyForClear() {
		if (containsAny()) {
			updateProbableBackReferencingPropertyForClearWhenIsNotEmpty();
		}
	}
	
	//method
	private void updateProbableBackReferencingPropertyForClearWhenIsNotEmpty() {
		
		final var backReferencingProperty = optionalReferenceHelper.getRefBackReferencingPropertyOrNull(this);
		
		if (backReferencingProperty != null) {
			updateBackReferencingPropertyForClear(backReferencingProperty);
		}
	}
	
	//method
	private void updateRealDatabaseForClear() {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAndSchemaAdapter().updateRecordOnTable(
				getRefParentEntity().getParentTableName(),
				optionalReferenceHelper.createRecordUpdateDTOForClear(this)
			);
		}
	}
	
	//method
	private void updateStateForSetEntity(final E entity) {
		referencedEntityId = entity.getId();
	}
	
	//method
	private void updateStateForClear() {
		referencedEntityId = null;
	}
}
