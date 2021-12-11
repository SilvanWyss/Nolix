//package declaration
package ch.nolix.system.objectdata.data;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.system.objectdata.propertyhelper.MultiValueHelper;
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.techapi.objectdataapi.propertyhelperapi.IMultiValueHelper;

//class
public final class MultiValue<V> extends BaseValue<V> implements IMultiValue<DataImplementation, V> {
	
	//static attribute
	private static final IMultiValueHelper multiValueHelper = new MultiValueHelper();
	
	//multi-attribute
	private final LinkedList<V> values = new LinkedList<>();
	
	//constructor
	public MultiValue(final String name) {
		super(name);
	}
	
	//method
	@Override
	public void addValue(V value) {
		
		values.addAtEnd(value);
		
		if (isLinkedWithRealDatabase()) {
			getRefDataAdapter().updateRecordOnTable(
				getParentEntity().getParentTable().getName(),
				multiValueHelper.createRecordUpdateDTOForAddedValue(this, value)
			);
		}
	}
	
	//method
	@Override
	public void clear() {
		values.clear();
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
	public Iterator<V> iterator() {
		return values.iterator();
	}
}
