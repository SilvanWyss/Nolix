//package declaration
package ch.nolix.core.entity;

//own imports
import ch.nolix.core.bases.NamedElement;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.functionInterfaces.IElementTakerElementGetter;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationInterfaces.Specified;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 60
 */
public abstract class Propertyoid<V extends Specified> extends NamedElement {
	
	//attribute
	private final IElementTakerElementGetter<IContainer<Specification>, V> valueCreator;
	
	//package-visible constructor
	/**
	 * Creates new property with the given name.
	 * 
	 * @param name
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 * @throws NullArgumentException if the given value creator is null.
	 */
	Propertyoid(final String name, final IElementTakerElementGetter<IContainer<Specification>, V> valueCreator) {
		
		//Calls constructor of the base class.
		super(name);
		
		//Checks if the given value creator is not null.
		Validator.suppose(valueCreator).thatIsNamed("value creator").isNotNull();
		
		this.valueCreator = valueCreator;
	}

	//abstract method
	/**
	 * @return the value of this property.
	 */
	public abstract V getValue();

	//method
	@SuppressWarnings("unchecked")
	public <S extends Specification> void setValue(final IContainer<S> specifications) {
		final IContainer<Specification> inputs = (IContainer<Specification>)specifications;
		setValue(valueCreator.getOutput(inputs));
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
