//package declaration
package ch.nolix.element.base;

//own imports
import ch.nolix.core.attributeAPI.Named;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.containers.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.validator.Validator;

//abstract class
/**
* @author Silvan Wyss
* @month 2017-10
* @lines 100
* @param <V> The type of the values of a {@link Propertyoid}.
*/
public abstract class Propertyoid<V> implements Named {
	
	//attributes
	private final String name;
	private final IElementTakerElementGetter<DocumentNodeoid, V> valueCreator;
	final IElementTakerElementGetter<V, DocumentNode> specificationCreator;
	
	//package-visible constructor
	/**
	 * Creates a new {@link Propertyoid} with the given name, valueCreator and specificationCreator.
	 * 
	 * @param name
	 * @param valueCreator
	 * @param specificationCreator
	 * @throws NullArgumentException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws NullArgumentException if the given valueCreator is null.
	 * @throws NullArgumentException if the given specificationCreator is null.
	 */
	Propertyoid(
		final String name,
		final IElementTakerElementGetter<DocumentNodeoid, V> valueCreator,
		final IElementTakerElementGetter<V, DocumentNode> specificationCreator
	) {
		
		//Checks if the given name is not null or blank.
		Validator.suppose(name).thatIsNamed(VariableNameCatalogue.NAME).isNotBlank();
				
		//Checks if the given value creator is not null.
		Validator.suppose(valueCreator).thatIsNamed("value creator").isNotNull();
		
		//Checks if the given specification creator is not null.
		Validator.suppose(specificationCreator).thatIsNamed("specificaiton creator").isNotNull();
		
		this.name = name;
		this.valueCreator = valueCreator;
		this.specificationCreator = specificationCreator;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getName() {
		return name;
	}
	
	//abstract method
	/**
	 * @return true if the current {@link Propertyoid} does not contain a value.
	 */
	public abstract boolean isEmpty();
	
	//abstract method
	/**
	 * @return true if the current {@link Propertyoid} is mutable.
	 */
	public abstract boolean isMutable();
	
	//package-visible abstract method
	/**
	 * Adds or change the given value to the current {@link Propertyoid}.
	 * 
	 * @param value
	 */
	abstract void addOrChangeValue(V value);
	
	//package-visible method
	/**
	 * Adds or changes the value from the given specification to the current {@link Propertyoid}.
	 * 
	 * @param specification
	 */
	final void addOrChangeValueFromSpecification(final DocumentNodeoid specification) {
		addOrChangeValue(valueCreator.getOutput(specification));
	}
	
	//package-visible abstract method
	/**
	 * Fills up the specifications of the values of the current {@link Propertyoid} into the given list.
	 * 
	 * @param list
	 */
	abstract void fillUpSpecificationsOfValues(List<DocumentNode> list);
}
