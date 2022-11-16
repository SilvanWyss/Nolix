//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBackReference;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IContentFieldDTO;

//class
public final class BackReference<E extends IEntity<DataImplementation>> extends BaseBackReference<E>
implements IBackReference<DataImplementation, E>{
	
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
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public E getRefEntity() {
		return getRefBackReferencedTable().getRefEntityById(getEntityId());
	}
	
	//method
	@Override
	public boolean isEmpty() {
		//TODO: Implement.
		return false;
	}
	
	//method
	@Override
	public boolean isMandatory() {
		return true;
	}
	
	//method
	@Override
	public boolean referencesBack(final IEntity<?> entity) {
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
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	void internalSetOrClearDirectlyFromContent(final Object content) {
		//TODO: Implement.
	}
}
