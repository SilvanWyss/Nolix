//package declaration
package ch.nolix.system.element.style;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.mutableelement.MutableOptionalValue;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class DeepStyle extends BaseStyle<DeepStyle> {
	
	//constant
	public static final String TYPE_NAME = "DeepConfiguration";
	
	//constant
	private static final String MAX_SELECTOR_LEVEL_HEADER = "MaxSelectorLevel";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link DeepStyle} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static DeepStyle fromSpecification(final INode<?> specification) {
		
		final var deepConfiguration = new DeepStyle();
		deepConfiguration.resetFromSpecification(specification);
		
		return deepConfiguration;
	}
	
	//attribute
	private final MutableOptionalValue<Integer> maxSelectorLevel =
	MutableOptionalValue.forInt(MAX_SELECTOR_LEVEL_HEADER, this::setMaxSelectorLevel);
	
	//constructor
	/**
	 * Creates a new {@link DeepStyle}.
	 */
	public DeepStyle() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void styleElement(IStylableElement<?> element) {
		if (!hasMaxSelectorLevel()) {

			final var elements = element.getRefChildStylableElements();
			
			if (selects(element)) {
				final var configurations = getRefConfigurations();
				setAttachingAttributesTo(element);
				elements.forEach(e -> configurations.forEach(c -> c.styleElement(e)));
			}
				
			elements.forEach(this::styleElement);
		} else {
			configure(element, getMaxSelectorLevel());
		}
	}
	
	//method
	/**
	 * @return the max selector level of the current {@link DeepStyle}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link DeepStyle} does not have a max selector level.
	 */
	public int getMaxSelectorLevel() {
		return maxSelectorLevel.getValue();
	}
	
	//method
	/**
	 * @return true if the current {@link DeepStyle} has a max selector level.
	 */
	public boolean hasMaxSelectorLevel() {
		return maxSelectorLevel.hasValue();
	}
	
	//method
	/**
	 * Removes the max selector level of the current {@link DeepStyle}.
	 */
	public void removeMaxSelectorLevel() {
		maxSelectorLevel.clear();
	}
	
	//method
	/**
	 * Sets the max selector level of the current {@link DeepStyle}.
	 * 
	 * @param maxSelectorLevel
	 * @throws NonPositiveArgumentException if the given max selector level is not positive.
	 */
	public void setMaxSelectorLevel(final int maxSelectorLevel) {
		
		//Asserts that the given maxSelectorLevel is positive.
		GlobalValidator.assertThat(maxSelectorLevel).thatIsNamed("max selector level").isPositive();
				
		this.maxSelectorLevel.setValue(maxSelectorLevel);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetBaseConfiguration() {
		removeMaxSelectorLevel();
	}
		
	//method
	/**
	 * Lets the current {@link DeepStyle} configure the given element recursively to the given level.
	 * 
	 * @param element
	 * @param level
	 */
	private void configure(IStylableElement<?> element, int level) {
		if (level > 0) {
			
			final var elements = element.getRefChildStylableElements();
			
			if (selects(element)) {
				
				setAttachingAttributesTo(element);
				
				final var configurations = getRefConfigurations();
				elements.forEach(e -> configurations.forEach(c -> c.styleElement(e)));
			}
				
			elements.forEach(e -> configure(e, level - 1));
		}
	}
}
