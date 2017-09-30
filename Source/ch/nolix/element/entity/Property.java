//package declaration
package ch.nolix.element.entity;

//own imports
import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.functionInterfaces.IBooleanGetter;
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationInterfaces.Specified;
import ch.nolix.core.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 170
 * @param <V> The type of the value of a property.
 */
public final class Property<V extends Specified> extends NamedElement {

	//attributes
	private final V defaultValue;
	private final IBooleanGetter secondaryDefaultValueCondition;
	private final V secondaryDefaultValue;
	private final IElementTakerElementGetter<IContainer<Specification>, V> valueCreator;
	
	//optional attributes
	private V value;
	private Property<V> baseProperty;
	
	//constructor
	public Property(
		final String name,		
		final V defaultValue,
		final IElementTakerElementGetter<IContainer<Specification>, V> valueCreator
	) {
		//Calls constructor of the base class.
		super(name);
		
		Validator.suppose(name).thatIsNamed("name").isNotEmpty();
		Validator.suppose(defaultValue).thatIsNamed("default value").isNotNull();
		Validator.suppose(valueCreator).thatIsNamed("value creator").isNotNull();
		
		this.valueCreator = valueCreator;
		this.defaultValue = defaultValue;
		secondaryDefaultValueCondition = null;
		secondaryDefaultValue = null;
	}
	
	//constructor
	public Property(
		final String name,
		final V defaultValue,
		final IBooleanGetter secondaryDefaultValueCondition,
		final V secondaryDefaultValue,
		final IElementTakerElementGetter<IContainer<Specification>, V> valueCreator
	) {
		//Calls constructor of the base class.
		super(name);
		
		Validator
		.suppose(defaultValue)
		.thatIsNamed("default value")
		.isNotNull();
		
		Validator
		.suppose(secondaryDefaultValueCondition)
		.thatIsNamed("secondary default value condition")
		.isNotNull();
		
		Validator
		.suppose(secondaryDefaultValue)
		.thatIsNamed("secondary default value")
		.isNotNull();
		
		Validator.suppose(valueCreator).thatIsNamed("value creator").isNotNull();
		
		this.defaultValue = defaultValue;
		this.secondaryDefaultValueCondition = secondaryDefaultValueCondition;
		this.secondaryDefaultValue = secondaryDefaultValue;
		this.valueCreator = valueCreator;
	}
	
	//method
	/**
	 * Removes the value of this property.
	 */
	public void clear() {
		value = null;
	}
	
	//method
	public V getActiveValue() {
	
		if (hasValue()) {
			return value;
		}
		
		if (hasBaseProperty()) {
			return baseProperty.getActiveValue();
		}
		
		if (
			hasSecondaryDefaultValue()
			&& secondaryDefaultValueCondition.getOutput()
		) {
			return secondaryDefaultValue;
		}
		
		return defaultValue;
	}
	
	//method
	public V getValue() {
		
		if (!hasValue()) {
			throw new UnexistingAttributeException(this, "value");
		}
		
		return value;
	}
	
	//method
	public boolean hasBaseProperty() {
		return (baseProperty != null);
	}
	
	//method
	public boolean hasRecursiveValue() {
		
		if (hasValue()) {
			return true;
		}
		
		if (hasBaseProperty()) {
			return baseProperty.hasRecursiveValue();
		}
		
		return false;
	}
	
	//method
	public boolean hasSecondaryDefaultValue() {
		return (secondaryDefaultValue != null);
	}
	
	//method
	public boolean hasValue() {
		return (value != null);
	}
	
	//method
	public void setValue(final V value) {
		
		Validator.suppose(value).thatIsNamed("value").isNotNull();
		
		this.value = value;
	}
	
	//method
	@SuppressWarnings("unchecked")
	public <S extends Specification> void setValue(final IContainer<S> specifications) {
		final IContainer<Specification> inputs = (IContainer<Specification>)specifications;
		setValue(valueCreator.getOutput(inputs));
	}
	
	//package-visible method
	@SuppressWarnings("unchecked")
	void setBaseProperty(final Property<?> baseProperty) {
		
		Validator
		.suppose(baseProperty)
		.thatIsNamed("base property")
		.isNotNull();
		
		this.baseProperty = (Property<V>)baseProperty;
	}
}
