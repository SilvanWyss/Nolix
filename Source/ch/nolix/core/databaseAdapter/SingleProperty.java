//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
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
	public final V getValue() {
		
		supposeHasValue();
		
		return value;
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
		
		noteChange();
	}
	
	//package-visible method
	void internal_clear() {
		
		supposeIsOptional();
		
		value = null;
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
