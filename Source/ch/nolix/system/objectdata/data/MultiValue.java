//package declaration
package ch.nolix.system.objectdata.data;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.system.objectdata.propertyhelper.MultiValueHelper;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.propertyhelperapi.IMultiValueHelper;

//class
public final class MultiValue<V> extends BaseValue<V> implements IMultiValue<DataImplementation, V> {
	
	//static attribute
	private static final IMultiValueHelper multiValueHelper = new MultiValueHelper();
	
	//multi-attribute
	private final LinkedList<V> values = new LinkedList<>();
		
	//method
	@Override
	public void addValue(final V value) {
		
		multiValueHelper.assertCanAddGivenValue(this, value);
		
		updateStateForAddValue(value);
		
		internalSetParentEntityAsEdited();
		
		updateRecordForAddValue(value);
	}
	
	//method
	@Override
	public void clear() {
		
		multiValueHelper.assertCanClear(this);
		
		updateStateForClear();
		
		internalSetParentEntityAsEdited();
		
		updateRecordForClear();
	}
	
	//method
	@Override
	public int getElementCount() {
		return values.getElementCount();
	}
	
	//method
	@Override
	public V getRefAt(final int index) {
		return values.getRefAt(index);
	}
	
	//method
	@Override
	public PropertyType getType() {
		return PropertyType.MULTI_VALUE;
	}
	
	//method
	@Override
	public boolean isMandatory() {
		return false;
	}
	
	//method
	@Override
	public Iterator<V> iterator() {
		return values.iterator();
	}
	
	//method
	@Override
	void internalSetOrClearDirectlyFromContent(final Object content) {
		//TODO: Implement.
	}
	
	//method
	private void updateRecordForAddValue(final V value) {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAndSchemaAdapter().updateRecordOnTable(
				getParentEntity().getParentTableName(),
				multiValueHelper.createRecordUpdateDTOForAddedValue(this, value)
			);
		}
	}
	
	//method
	private void updateRecordForClear() {
		if (isLinkedWithRealDatabase()) {
			internalGetRefDataAndSchemaAdapter().updateRecordOnTable(
				getParentEntity().getParentTableName(),
				multiValueHelper.createRecordUpdateDTOForClear(this)
			);
		}
	}
	
	//method
	private void updateStateForAddValue(final V value) {
		values.addAtEnd(value);
	}
	
	//method
	private void updateStateForClear() {
		values.clear();
	}
}
