//package declaration
package ch.nolix.element.base;

//own imports
import ch.nolix.common.attributeAPI.Named;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;

//class
/**
* @author Silvan Wyss
* @month 2017-10
* @lines 100
* @param <V> The type of the values of a {@link BaseProperty}.
*/
public abstract class BaseProperty<V> implements Named {
	
	//attributes
	private final String name;
	private final IElementTakerElementGetter<BaseNode, V> valueCreator;
	final IElementTakerElementGetter<V, Node> specificationCreator;
	
	//constructor
	/**
	 * Creates a new {@link BaseProperty} with the given name, valueCreator and specificationCreator.
	 * 
	 * @param name
	 * @param valueCreator
	 * @param specificationCreator
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given valueCreator is null.
	 * @throws ArgumentIsNullException if the given specificationCreator is null.
	 */
	BaseProperty(
		final String name,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator
	) {
		
		//Asserts that the given name is not null or blank.
		Validator.assertThat(name).thatIsNamed(VariableNameCatalogue.NAME).isNotBlank();
				
		//Asserts that the given value creator is not null.
		Validator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
		
		//Asserts that the given specification creator is not null.
		Validator.assertThat(specificationCreator).thatIsNamed("specificaiton creator").isNotNull();
		
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
	
	//method declaration
	/**
	 * @return true if the current {@link BaseProperty} does not contain a value.
	 */
	public abstract boolean isEmpty();
	
	//method declaration
	/**
	 * @return true if the current {@link BaseProperty} is mutable.
	 */
	public abstract boolean isMutable();
	
	//method declaration
	/**
	 * Adds or change the given value to the current {@link BaseProperty}.
	 * 
	 * @param value
	 */
	abstract void addOrChangeValue(V value);
	
	//method
	/**
	 * Adds or changes the value from the given specification to the current {@link BaseProperty}.
	 * 
	 * @param specification
	 */
	final void addOrChangeValueFromSpecification(final BaseNode specification) {
		addOrChangeValue(valueCreator.getOutput(specification));
	}
	
	//method declaration
	/**
	 * Fills up the specifications of the values of the current {@link BaseProperty} into the given list.
	 * 
	 * @param list
	 */
	abstract void fillUpSpecificationsOfValues(LinkedList<Node> list);
}
