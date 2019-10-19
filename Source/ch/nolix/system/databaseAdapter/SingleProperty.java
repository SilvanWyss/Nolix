//package declaration
package ch.nolix.system.databaseAdapter;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.List;
import ch.nolix.common.containers.ReadContainer;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;

//abstract class
public abstract class SingleProperty<V> extends DataPropertyoid<V> {

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
	
	//abstract method
	public abstract boolean isOptional();
	
	//method
	public final void setValue(final V value) {
		
		Validator
		.suppose(value)
		.thatIsNamed(VariableNameCatalogue.VALUE)
		.isNotNull();
		
		this.value = value;
		
		internal_noteUpdate();
	}
	
	//TODO
	//method
	@SuppressWarnings("unchecked")
	public final void setValueUnsafe(final Object value) {
		this.value = (V)value;
	}
	
	//method
	public final void setValueFromSpecification(final Node specification) {
		setValue(createValueFromSpecification(specification));
	}
	
	//method
	public final void setValueFromString(final String string) {
		setValue(createValueFromString(string));
	}
	
	//method
	@Override
	protected final void internal_clear() {
		
		supposeIsOptional();
		
		value = null;
		
		internal_noteUpdate();
	}
	
	//method
	@Override
	protected final List<Object> internal_getValues() {
		
		final var values = new List<Object>();
		
		if (hasValue()) {
			values.addAtEnd(getValue());
		}
		
		return values;
	}
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	protected final void internal_setValue(final Object value) {
		setValue((V)value);
	}
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	protected final void internal_setValues(final Iterable<Object> values) {
		setValue((V)(new ReadContainer<>(values).getRefOne()));
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
