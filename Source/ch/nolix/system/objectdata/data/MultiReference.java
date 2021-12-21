//package declaration
package ch.nolix.system.objectdata.data;

//Java imports
import java.util.Iterator;

import ch.nolix.common.container.IContainer;
//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.system.objectdata.propertyhelper.MultiReferenceHelper;
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.objectdataapi.dataapi.IEntity;
import ch.nolix.techapi.objectdataapi.dataapi.IMultiReference;
import ch.nolix.techapi.objectdataapi.propertyhelperapi.IMultiReferenceHelper;

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
	
	@Override
	public void addEntity(final E entity) {
		//TODO: Implement
	}
	
	//method
	@Override
	public void clear() {
		
		referencedEntityIds.clear();
		
		noteParentEntityForChange();
		
		updateParentEntityForClear();
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
	public Iterator<E> iterator() {
		
		final var referencedTable = getReferencedTable();
		
		return referencedEntityIds.iterator(referencedTable::getRefEntityById);
	}
	
	//method
	@Override
	public boolean references(final IEntity<DataImplementation> entity) {
		
		if (entity == null) {
			return false;
		}
		
		return referencedEntityIds.containsAnyEqualing(entity.getId());
	}
	
	//method
	private String getIdOfEntityAt(final int index) {
		return referencedEntityIds.getRefAt(index);
	}
	
	//method
	private void noteParentEntityForChange() {
		if (belongsToEntity()) {
			internalGetParentEntity().internalSetEdited();
		}
	}
	
	//method
	private void updateParentEntityForClear() {
		if (belongsToEntity()) {
			internalGetRefDataAdapter().updateRecordOnTable(
				getParentEntity().getId(),
				multiReferenceHelper.createRecordUpdateDTOForClear(this)
			);
		}
	}
}
