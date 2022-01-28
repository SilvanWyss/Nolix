//package declaration
package ch.nolix.system.objectdata.data;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.system.objectdata.propertyhelper.MultiReferenceHelper;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.propertyhelperapi.IMultiReferenceHelper;

//class
public final class MultiReference<E extends IEntity<DataImplementation>> extends BaseReference<E>
implements IMultiReference<DataImplementation, E> {
	
	//static attribute
	private static final IMultiReferenceHelper multiReferenceHelper = new MultiReferenceHelper();
	
	//static method
	public static <E2 extends Entity> MultiReference<E2> forEntity(final Class<E2> type) {
		return new MultiReference<>(type.getSimpleName());
	}
	
	//static method
	public static MultiReference<GeneralEntity> forEntityWithTableName(final String tableName) {
		return new MultiReference<>(tableName);
	}
	
	//multi-attribute
	private final LinkedList<String> referencedEntityIds = new LinkedList<>();
	
	//constructor
	private MultiReference(final String referencedTableName) {
		super(referencedTableName);
	}
	
	//method
	@Override
	public void addEntity(final E entity) {
		
		multiReferenceHelper.assertCanAddGivenEntity(this, entity);
		
		updateStateForAddEntity(entity);
		
		internalSetParentEntityAsEdited();
		
		updateRecordForAddEntity(entity);
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
	public int getElementCount() {
		return referencedEntityIds.getElementCount();
	}
	
	//method
	@Override
	public E getRefAt(final int index) {
		return getReferencedTable().getRefEntityById(getIdOfEntityAt(index));
	}
	
	//method
	@Override
	public IContainer<String> getReferencedEntityIds() {
		return referencedEntityIds;
	}
	
	//method
	@Override
	public PropertyType getType() {
		return PropertyType.MULTI_REFERENCE;
	}
	
	//method
	@Override
	public boolean isMandatory() {
		return false;
	}
	
	//method
	@Override
	public Iterator<E> iterator() {
		
		final var referencedTable = getReferencedTable();
		
		return referencedEntityIds.iterator(referencedTable::getRefEntityById);
	}
	
	//method
	@Override
	public boolean references(final IEntity<?> entity) {
		
		if (entity == null) {
			return false;
		}
		
		return referencedEntityIds.containsAnyEqualing(entity.getId());
	}
	
	//method
	@Override
	public boolean referencesUninsertedEntity() {
		return containsOnly(IEntity::knowsParentTable);
	}
	
	//method
	@Override
	void internalSetOrClearDirectlyFromContent(final Object content) {
		//TODO: Implement.
	}
	
	//method
	private void clearWhenContainsAny() {
		
		multiReferenceHelper.assertCanClear(this);
		
		updateStateForClear();
		
		internalSetParentEntityAsEdited();
		
		updateRecordForClear();
	}
	
	//method
	private String getIdOfEntityAt(final int index) {
		return referencedEntityIds.getRefAt(index);
	}
	
	//method
	private void updateRecordForAddEntity(final E entity) {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAndSchemaAdapter().updateRecordOnTable(
				getParentEntity().getParentTableName(),
				multiReferenceHelper.createRecordupdateDTOForAddEntity(this, entity)
			);
		}
	}
	
	//method
	private void updateRecordForClear() {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAndSchemaAdapter().updateRecordOnTable(
				getParentEntity().getParentTableName(),
				multiReferenceHelper.createRecordUpdateDTOForClear(this)
			);
		}
	}
	
	//method
	private void updateStateForAddEntity(final E entity) {
		referencedEntityIds.addAtEnd(entity.getId());
	}
	
	//method
	private void updateStateForClear() {
		referencedEntityIds.clear();
	}
}
