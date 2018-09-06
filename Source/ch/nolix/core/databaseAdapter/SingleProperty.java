//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
public abstract class SingleProperty<V>
extends DataPropertyoid<V> {

	//optional attribute
	private V value;
	
	//method
	@SuppressWarnings("unchecked")
	public Class<V> getDataType() {
		return (Class<V>)getClass().getDeclaredFields()[0].getType();
	}
	
	//method
	public final List<StandardSpecification> getAttributes0() {
		
		final var attributes = new List<StandardSpecification>();
		
		if (hasValue()) {
			attributes.addAtEnd(
				new StandardSpecification(
					getValue().toString()
				)
			);
		}
		
		return attributes;
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
	@SuppressWarnings("unchecked")
	public final void setUntypedValue(final Object value) {
		setValue((V)value);
	}
	
	//method
	public final void setValue(final V value) {
		
		Validator
		.suppose(value)
		.thatIsNamed(VariableNameCatalogue.VALUE)
		.isInstance();
		
		this.value = value;
		
		internal_noteUpdate();
	}
	
	//method
	protected final void internal_clear() {
		
		supposeIsOptional();
		
		value = null;
		
		internal_noteUpdate();
	}
	
	//method
	protected final List<Object> internal_getValues() {
		
		final var values = new List<Object>();
		
		if (hasValue()) {
			values.addAtEnd(getValue());
		}
		
		return values;
	}
	
	//package-visible method
	@SuppressWarnings("unchecked")
	protected final void internal_setValues(final Iterable<Object> values) {
		setValue((V)(new ReadContainer<>(values).getRefOne()));
	}
	
	//method
	private void supposeHasValue() {
		if (!hasValue()) {
			throw new UnexistingAttributeException(
				this,
				VariableNameCatalogue.VALUE
			);
		}
	}
	
	//method
	private void supposeIsOptional() {
		if (!isOptional()) {
			throw new InvalidStateException(this, "is not optional");
		}
	}
}
