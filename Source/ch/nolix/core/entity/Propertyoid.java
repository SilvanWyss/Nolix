//package declaration
package ch.nolix.core.entity;

//own imports
import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.functionInterfaces.IElementTakerRunner;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationInterfaces.Specified;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 130
 */
public abstract class Propertyoid<V extends Specified> extends NamedElement {
	
	//attribute
	private final IElementTakerElementGetter<Specification, V> valueCreator;
	
	//optional attribute
	private final IElementTakerRunner<V> setterMethod;
	
	//package-visible constructor
	/**
	 * Creates new property with the given name and value creator.
	 * 
	 * @param name
	 * @param valueCreator
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given value creator is null.
	 */
	Propertyoid(
		final String name,
		final IElementTakerElementGetter<Specification, V> valueCreator
	) {
		
		//Calls constructor of the base class.
		super(name);
		
		//Checks if the given value creator is not null.
		Validator.suppose(valueCreator).thatIsNamed("value creator").isNotNull();
		
		//Clears the setter method of this property.
		setterMethod = null;
		
		//Sets the value creator of this property.
		this.valueCreator = valueCreator;
	}

	//package-visible constructor
	/**
	 * Creates new property with the given name and value creator.
	 * 
	 * @param name
	 * @param setterMethod
	 * @param valueCreator
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given setter method is null.
	 * @throws NullArgumentException if the given value creator is null.
	 */
	Propertyoid(
		final String name,
		final IElementTakerRunner<V> setterMethod,
		final IElementTakerElementGetter<Specification, V> valueCreator
	) {
		
		//Calls constructor of the base class.
		super(name);
		
		//Checks if the gvein setter method is not null.
		Validator.suppose(setterMethod).thatIsNamed("setter method").isNotNull();
		
		//Checks if the given value creator is not null.
		Validator.suppose(valueCreator).thatIsNamed("value creator").isNotNull();
		
		//Sets the setter method of this property.
		this.setterMethod = setterMethod;
		
		//Sets the value creator of this property.
		this.valueCreator = valueCreator;
	}
	
	//abstract method
	/**
	 * @return the value of this property.
	 */
	public abstract V getValue();

	//method
	/**
	 * Sets teh value of this property.
	 * This method uses the setter method of this property
	 * if this property has a setter method.
	 * 
	 * @param specification
	 */
	void setValueUsingPossibleSetterMethod(final Specification specification) {
		
		final V value = valueCreator.getOutput(specification);
		
		//Handles the case that this property has no setter method.
		if (!hasSetterMethod()) {
			setValue(value);
		}
		
		//Handles the case that this property has a setter method.
		else {
			setterMethod.run(value);
		}
	}
	
	//method
	/**
	 * @return true if this property has a setter method.
	 */
	public final boolean hasSetterMethod() {
		return (setterMethod != null);
	}
	
	//abstract method
	/**
	 * Sets the value of this property.
	 * 
	 * @param value
	 */
	public abstract void setValue(V value);
	
	//package-visible abstract method
	/**
	 * @return true if this property has no value.
	 */
	abstract boolean isEmpty();
}
