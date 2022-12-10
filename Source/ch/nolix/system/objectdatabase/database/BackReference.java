//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.system.objectdatabase.propertyhelper.BackReferenceHelper;
import ch.nolix.system.sqlrawdata.databasedto.ContentFieldDTO;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBackReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IBackReferenceHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDTO;

//class
public final class BackReference<E extends IEntity<DataImplementation>> extends BaseBackReference<E>
implements IBackReference<DataImplementation, E>{
	
	//static attribute
	private static final IBackReferenceHelper backReferenceHelper = new BackReferenceHelper();
	
	//static method
	public static <E2 extends Entity> BackReference<E2> forEntityAndBackReferencedPropertyName(
		final Class<E2> type,
		final String backReferencedPropertyName
	) {
		return new BackReference<>(type.getSimpleName(), backReferencedPropertyName);
	}
	
	//static method
	public static BackReference<BaseEntity> forEntityWithTableNameAndBackReferencedPropertyName(
		final String tableName,
		final String backReferencedPropertyName
	) {
		return new BackReference<>(tableName, backReferencedPropertyName);
	}
	
	//optional attributes
	private String backReferencedEntityId;
	
	//constructor
	private BackReference(final String backReferencedTableName, final String backReferencedPropertyName) {
		super(backReferencedTableName, backReferencedPropertyName);
	}
	
	//method
	@Override
	public PropertyType getType() {
		return PropertyType.BACK_REFERENCE;
	}
	
	//method
	@Override
	public String getEntityId() {
		
		backReferenceHelper.assertIsNotEmpty(this);
		
		return backReferencedEntityId;
	}
	
	//method
	@Override
	public E getRefEntity() {
		return getRefBackReferencedTable().getRefEntityById(getEntityId());
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return (backReferencedEntityId == null);
	}
	
	//method
	@Override
	public boolean isMandatory() {
		return true;
	}
	
	//method
	@Override
	public boolean referencesBackEntity(final IEntity<?> entity) {
		return
		containsAny()
		&& entity != null
		&& getEntityId().equals(entity.getId());
	}
	
	//method
	@Override
	public boolean referencesBackEntity() {
		return containsAny();
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
	void internalClear() {
		backReferencedEntityId = null;
		setAsEditedAndRunProbableUpdateAction();
	}
	
	//method
	void internalSetDirectlyBackReferencedEntityId(final String backReferencedEntityId) {
		this.backReferencedEntityId = backReferencedEntityId;
	}
	
	//method
	@Override
	void internalSetOrClearDirectlyFromContent(final Object content) {
		backReferencedEntityId = (String)content;
	}
	
	//method
	@Override
	void internalUpdateWhenIsNewMultiProperty() {
		//Does nothing.
	}
}
