//package declaration
package ch.nolix.system.entity;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;

//class
public abstract class SingleProperty<V> extends BaseValueProperty<V> {

	//optional attribute
	private V value;
	
	//method
	@Override
	public final Node getCellSpecification() {
		
		if (value == null) {
			return new Node();
		}
		
		//return new Node(value.toString());
		return Node.fromString(value.toString());
	}
	
	//method
	@SuppressWarnings("unchecked")
	public Class<V> getDataType() {
		return (Class<V>)getClass().getDeclaredFields()[0].getType();
	}
	
	//method
	public final V getValue() {
		
		supposeHasValue();
		
		return value;
	}
	
	//method
	public final boolean hasEqualValue(final V value) {
		
		if (!hasValue()) {
			return false;
		}
		
		return getValue().equals(value);
	}
	
	//method
	public final boolean hasValue() {
		return (value != null);
	}
	
	//method
	public final boolean isEmpty() {
		return (value == null);
	}
	
	//method declaration
	public abstract boolean isOptional();
	
	//method
	public final void setValue(final V value) {
		
		Validator
		.assertThat(value)
		.thatIsNamed(VariableNameCatalogue.VALUE)
		.isNotNull();
		
		this.value = value;
		
		internalNoteUpdate();
	}
	
	//method
	public final void setValueFromSpecification(final Node specification) {
		setValue(createValueFromSpecification(specification));
	}
	
	//method
	public final void setValueFromString(final String string) {
		setValueFromSpecification(Node.fromString(string));
	}
	
	//method
	@Override
	protected final void internalClear() {
		
		supposeIsOptional();
		
		value = null;
		
		internalNoteUpdate();
	}
	
	//method
	@Override
	protected final LinkedList<Object> internalGetValues() {
		
		final var values = new LinkedList<Object>();
		
		if (hasValue()) {
			values.addAtEnd(getValue());
		}
		
		return values;
	}
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	protected final void internalSetValue(final Object value) {
		setValue((V)value);
	}
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	protected final void internalSetValues(final IContainer<Object> values) {
		setValue((V)(ReadContainer.forIterable(values).getRefOne()));
	}
	
	//method
	private void supposeHasValue() {
		if (!hasValue()) {
			throw new ArgumentDoesNotHaveAttributeException(
				this,
				VariableNameCatalogue.VALUE
			);
		}
	}
	
	//method
	private void supposeIsOptional() {
		if (!isOptional()) {
			throw new InvalidArgumentException(this, "is not optional");
		}
	}
}
