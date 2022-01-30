//package declaration
package ch.nolix.element.base;

import ch.nolix.core.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionapi.IElementTakerElementGetter;
import ch.nolix.core.requestapi.MutabilityRequestable;

//class
/**
* @author Silvan Wyss
* @date 2017-10-29
* @lines 90
* @param <V> is the type of the values of a {@link BaseValue}.
*/
public abstract class BaseValue<V> extends Property implements MutabilityRequestable, Named {
	
	//attributes
	private final String name;
	private final IElementTakerElementGetter<BaseNode, V> valueCreator;
	protected final IElementTakerElementGetter<V, Node> specificationCreator;
	
	//constructor
	/**
	 * Creates a new {@link BaseValue} with the given name, valueCreator and specificationCreator.
	 * 
	 * @param name
	 * @param valueCreator
	 * @param specificationCreator
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given valueCreator is null.
	 * @throws ArgumentIsNullException if the given specificationCreator is null.
	 */
	BaseValue(
		final String name,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator
	) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		Validator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
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
	
	//method
	/**
	 * Adds or changes the value from the given attribute to the current {@link BaseValue}.
	 * 
	 * @param attribute
	 */
	@Override
	protected final boolean addedOrChangedAttribute(final BaseNode attribute) {
		
		if (attribute.hasHeader(getName())) {
			addOrChangeValue(valueCreator.getOutput(attribute));
			return true;
		}
		
		return false;
	}
	
	//method declaration
	/**
	 * Adds or change the given value to the current {@link BaseValue}.
	 * 
	 * @param value
	 */
	protected abstract void addOrChangeValue(V value);
}
