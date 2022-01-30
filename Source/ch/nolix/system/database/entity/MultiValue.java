//package declaration
package ch.nolix.system.database.entity;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.skillapi.Clearable;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class MultiValue<V> extends BaseValueProperty<V> implements Clearable {
	
	//multi-attribute
	private final LinkedList<V> values = new LinkedList<>();
	
	//method
	public void addUntypedValue(final V value) {
		addValue(value);
	}
	
	//method
	public void addValue(final V value) {
		
		values.addAtEnd(value);
		
		internalNoteUpdate();
	}
	
	//method
	@SuppressWarnings("unchecked")
	public void addValue(final V... values) {
		
		this.values.addAtEnd(values);
		
		internalNoteUpdate();
	}
	
	//method
	public void addValues(final Iterable<V> values) {
		
		this.values.addAtEnd(values);
		
		internalNoteUpdate();
	}
	
	//method
	@Override
	public boolean canBeSeveral() {
		return true;
	}
	
	//method
	@Override
	public void clear() {
		internalClear();
	}
	
	//method
	@Override
	public PropertyType getPropertyKind() {
		return PropertyType.MULTI_VALUE;
	}
	
	//method
	public ReadContainer<V> getValues() {
		return ReadContainer.forIterable(values);
	}

	//method
	@Override
	public boolean isEmpty() {
		return values.isEmpty();
	}
	
	//method
	@Override
	public void assertCanBeSaved() {}
	
	//method
	@Override
	protected void internalClear() {
		values.clear();
	}
	
	//method
	@Override
	protected LinkedList<Object> internalGetValues() {
		return values.getCopy().asContainerWithElementsOfEvaluatedType();
	}
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	protected void internalSetValue(final Object value) {
		clear();
		values.addAtEnd((V)value);
	}
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	protected void internalSetValues(final IContainer<Object> values) {
		clear();
		values.forEach(v -> addValue((V)v));
	}
}
