//package declaration
package ch.nolix.system.database.entity;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.IElementTaker;

//class
public abstract class SingleValue<V> extends BaseValueProperty<V> {
	
	//optional attributes
	private V value;
	private final IElementTaker<V> preSetValueFunction;
	
	//constructor
	public SingleValue() {
		preSetValueFunction = null;
	}
	
	//constructor
	public SingleValue(final IElementTaker<V> preSetValueFunction) {
		
		Validator.assertThat(preSetValueFunction).thatIsNamed("pre-set value function").isNotNull();
		
		this.preSetValueFunction = preSetValueFunction;
	}
	
	//method
	@Override
	public final boolean canBeSeveral() {
		return false;
	}
	
	//method
	@SuppressWarnings("unchecked")
	public Class<V> getDataType() {
		return (Class<V>)getClass().getDeclaredFields()[0].getType();
	}
	
	//method
	public final V getValue() {
		
		assertHasValue();
		
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
		
		Validator.assertThat(value).thatIsNamed(LowerCaseCatalogue.VALUE).isNotNull();
		
		if (preSetValueFunction != null) {
			preSetValueFunction.run(value);
		}
		
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
		
		assertIsOptional();
		
		value = null;
		internalNoteUpdate();
	}
	
	//method
	@Override
	protected final LinkedList<Object> internalGetValues() {
		
		if (isEmpty()) {
			return new LinkedList<>();
		}
		
		return LinkedList.withElements(getValue());
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
	private void assertHasValue() {
		if (!hasValue()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.VALUE);
		}
	}
	
	//method
	private void assertIsOptional() {
		if (!isOptional()) {
			throw new InvalidArgumentException(this, "is not optional");
		}
	}
}
