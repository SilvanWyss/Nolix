//package declaration
package ch.nolix.element.base;

//own imports
import ch.nolix.common.attributeapi.Named;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.IElementTakerElementGetter;

//class
/**
* @author Silvan Wyss
* @date 2017-10-29
* @lines 100
* @param <V> is the type of the values of a {@link Property}.
*/
public abstract class Property<V> implements Named {
	
	//attributes
	private final String name;
	private final IElementTakerElementGetter<BaseNode, V> valueCreator;
	protected final IElementTakerElementGetter<V, Node> specificationCreator;
	
	//constructor
	/**
	 * Creates a new {@link Property} with the given name, valueCreator and specificationCreator.
	 * 
	 * @param name
	 * @param valueCreator
	 * @param specificationCreator
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given valueCreator is null.
	 * @throws ArgumentIsNullException if the given specificationCreator is null.
	 */
	Property(
		final String name,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator
	) {
		
		//Asserts that the given name is not null or blank.
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
				
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
	 * @return true if the current {@link Property} does not contain a value.
	 */
	public abstract boolean isEmpty();
	
	//method declaration
	/**
	 * @return true if the current {@link Property} is mutable.
	 */
	public abstract boolean isMutable();
		
	//method declaration
	/**
	 * Adds or change the given value to the current {@link Property}.
	 * 
	 * @param value
	 */
	abstract void addOrChangeValue(V value);
	
	//method
	/**
	 * Adds or changes the value from the given specification to the current {@link Property}.
	 * 
	 * @param specification
	 */
	final void addOrChangeValueFromSpecification(final BaseNode specification) {
		addOrChangeValue(valueCreator.getOutput(specification));
	}
	
	//method declaration
	/**
	 * Fills up the specifications of the values of the current {@link Property} into the given list.
	 * 
	 * @param list
	 */
	abstract void fillUpSpecificationsOfValues(LinkedList<Node> list);
}
