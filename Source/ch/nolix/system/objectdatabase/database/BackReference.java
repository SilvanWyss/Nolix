//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdatabase.propertyvalidator.PropertyValidator;
import ch.nolix.system.sqldatabaserawdata.databasedto.ContentFieldDto;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBackReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi.IPropertyValidator;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IContentFieldDto;

//class
public final class BackReference<E extends IEntity> extends BaseBackReference<E>
implements IBackReference<E>{
	
	//constant
	private static final IPropertyValidator PROPERTY_VALIDATOR = new PropertyValidator();
	
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
	public IContainer<IProperty> getOriReferencingProperties() {
		
		if (isEmpty()) {
			return new ImmutableList<>();
		}
		
		return
		ImmutableList.withElement(
			getBackReferencedEntity().technicalGetRefProperties().getOriFirst(p -> p.hasName(getBackReferencedPropertyName()))
		);
	}
	
	//method
	@Override
	public PropertyType getType() {
		return PropertyType.BACK_REFERENCE;
	}
	
	//method
	@Override
	public E getBackReferencedEntity() {
		return getBackReferencedTable().getOriEntityById(getBackReferencedEntityId());
	}
	
	//method
	@Override
	public String getBackReferencedEntityId() {
		
		PROPERTY_VALIDATOR.assertIsNotEmpty(this);
		
		return backReferencedEntityId;
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
	public boolean referencesBackEntity(final IEntity entity) {
		return
		containsAny()
		&& entity != null
		&& getBackReferencedEntityId().equals(entity.getId());
	}
	
	//method
	@Override
	public boolean referencesBackEntity() {
		return containsAny();
	}
	
	//method
	@Override
	public IContentFieldDto technicalToContentField() {
		
		if (isEmpty()) {
			return new ContentFieldDto(getName());
		}
		
		return new ContentFieldDto(getName(), getBackReferencedEntityId());
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
