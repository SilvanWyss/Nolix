//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.objectdatabase.propertyhelper.OptionalBackReferenceHelper;
import ch.nolix.system.sqlrawdata.databasedto.ContentFieldDTO;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalBackReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IOptionalBackReferenceHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDTO;

//class
public final class OptionalBackReference<E extends IEntity<DataImplementation>> extends BaseBackReference<E>
implements IOptionalBackReference<DataImplementation, E>{
	
	//static attribute
	private static final IOptionalBackReferenceHelper optionalBackReferenceHelper = new OptionalBackReferenceHelper();
	
	//static method
	public static <E2 extends Entity> OptionalBackReference<E2> forEntityAndBackReferencedPropertyName(
		final Class<E2> type,
		final String backReferencedPropertyName
	) {
		return new OptionalBackReference<>(type.getSimpleName(), backReferencedPropertyName);
	}
	
	//static method
	public static OptionalBackReference<BaseEntity> forEntityWithTableNameAndBackReferencedPropertyName(
		final String tableName,
		final String backReferencedPropertyName
	) {
		return new OptionalBackReference<>(tableName, backReferencedPropertyName);
	}
	
	//optional attributes
	private String backReferencedEntityId;
	
	//constructor
	private OptionalBackReference(final String backReferencedTableName, final String backReferencedPropertyName) {
		super(backReferencedTableName, backReferencedPropertyName);
	}
	
	//method
	@Override
	public String getBackReferencedEntityId() {
		
		optionalBackReferenceHelper.assertIsNotEmpty(this);
		
		return backReferencedEntityId;
	}
	
	//method
	@Override
	public E getBackReferencedEntity() {
		return getBackReferencedTable().getRefEntityById(getBackReferencedEntityId());
	}
	
	//method
	@Override
	public IContainer<IProperty<DataImplementation>> getRefReferencingProperties() {
		
		if (isEmpty()) {
			return new ImmutableList<>();
		}
		
		return
		ImmutableList.withElement(
			getBackReferencedEntity().technicalGetRefProperties().getRefFirst(p -> p.hasName(getBackReferencedPropertyName()))
		);
	}
	
	//method
	@Override
	public PropertyType getType() {
		return PropertyType.OPTIONAL_BACK_REFERENCE;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return (backReferencedEntityId == null);
	}
	
	//method
	@Override
	public boolean isMandatory() {
		return false;
	}
	
	//method
	@Override
	public boolean referencesBackEntity(IEntity<?> entity) {
		return
		containsAny()
		&& entity != null
		&& getBackReferencedEntityId().equals(entity.getId());
	}
	
	//method
	@Override
	public IContentFieldDTO technicalToContentField() {

		if (isEmpty()) {
			return new ContentFieldDTO(getName());
		}
		
		return new ContentFieldDTO(getName(), getBackReferencedEntityId());
	}
	
	//method
	@Override
	protected boolean referencesBackEntityWithId(final String id) {
		return (containsAny() && getBackReferencedEntityId().equals(id));
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
}
