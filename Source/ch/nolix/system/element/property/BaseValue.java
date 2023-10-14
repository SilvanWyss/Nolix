//package declaration
package ch.nolix.system.element.property;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;
import ch.nolix.coreapi.functionapi.requestapi.MutabilityRequestable;
import ch.nolix.systemapi.elementapi.propertyapi.IBaseValue;

//class
/**
 * @author Silvan Wyss
 * @date 2017-10-29
 * @param <V> is the type of the values of a {@link BaseValue}.
 */
public abstract class BaseValue<V> implements IBaseValue, MutabilityRequestable, Named {
	
	//attribute
	private final String name;
	
	//attribute
	private final IElementTakerElementGetter<INode<?>, V> valueCreator;
	
	//attribute
	protected final IElementTakerElementGetter<V, INode<?>> specificationCreator;
	
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
		final IElementTakerElementGetter<INode<?>, V> valueCreator,
		final IElementTakerElementGetter<V, INode<?>> specificationCreator
	) {
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		GlobalValidator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
		GlobalValidator.assertThat(specificationCreator).thatIsNamed("specificaiton creator").isNotNull();
		
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
	public final boolean addedOrChangedAttribute(final INode<?> attribute) {
		
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
