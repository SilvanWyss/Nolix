//package declaration
package ch.nolix.system.entity;

import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.node.Node;
import ch.nolix.common.skillAPI.Clearable;

//class
public final class MultiValueProperty<V> extends BaseValueProperty<V> implements Clearable<MultiValueProperty<V>> {
	
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
	public MultiValueProperty<V> clear() {
		
		internalClear();
		
		return this;
	}
	
	//method
	@Override
	public Node getCellSpecification() {
		
		final var cellSpecification = new Node();
		for (final var v : getValues()) {
			cellSpecification.addAttribute(Node.fromString(v.toString()));
		}
		
		return cellSpecification;
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.MULTI_VALUE;
	}
	
	//method
	public ReadContainer<V> getValues() {
		return new ReadContainer<>(values);
	}

	//method
	@Override
	public boolean isEmpty() {
		return values.isEmpty();
	}
	
	//method
	@Override
	public void supposeCanBeSaved() {}
	
	//method
	@Override
	protected void internalClear() {
		values.clear();
	}
	
	//method
	@Override
	protected LinkedList<Object> internalGetValues() {
		return LinkedList.withElements(values);
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
