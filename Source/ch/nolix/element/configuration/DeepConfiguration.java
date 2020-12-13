//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.elementapi.IConfigurableElement;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 180
 */
public class DeepConfiguration extends BaseConfiguration<DeepConfiguration> {
	
	//constant
	public static final String TYPE_NAME = "DeepConfiguration";
	
	//constant
	private static final String MAX_SELECTOR_LEVEL_HEADER = "MaxSelectorLevel";
	
	//optional attribute
	private int maxSelectorLevel = -1;
	
	//constructor
	/**
	 * Creates a new {@link DeepConfiguration}.
	 */
	public DeepConfiguration() {}
		
	//method
	/**
	 * Adds or changes the given attribute to the current {@link DeepConfiguration}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case MAX_SELECTOR_LEVEL_HEADER:
				setMaxSelectorLevel(attribute.getOneAttributeAsInt());
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	/**
	 * Lets the current {@link DeepConfiguration} configure the given element.
	 * 
	 * @param element
	 */
	@Override
	public void configure(IConfigurableElement<?> element) {
		if (!hasMaxSelectorLevel()) {

			final var elements = element.getSubConfigurables();
			
			if (selects(element)) {
				setAttachingAttributesTo(element);
				elements.forEach(e -> configurations.forEach(c -> c.configure(e)));
			}
				
			elements.forEach(this::configure);
		}
		else {
			configure(element, getMaxSelectorLevel());
		}
	}
	
	//method
	/**
	 * @return the attributes of the current {@link DeepConfiguration}.
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		
		//Calls method of the base class.
		final LinkedList<Node> attributes = super.getAttributes();
		
		//Handles the case that the current DeepConfiguration has a max selector level.
		if (hasMaxSelectorLevel()) {
			attributes.addAtEnd(Node.withHeaderAndAttribute(MAX_SELECTOR_LEVEL_HEADER, maxSelectorLevel));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the max selector level of the current {@link DeepConfiguration}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link DeepConfiguration} does not have a max selector level.
	 */
	public int getMaxSelectorLevel() {
		
		//Asserts that the current DeepConfiguration has a max selector level.
		if (!hasMaxSelectorLevel()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "max selector level");
		}
		
		return maxSelectorLevel;
	}
	
	//method
	/**
	 * @return true if the current {@link DeepConfiguration} has a max selector level.
	 */
	public boolean hasMaxSelectorLevel() {
		return (maxSelectorLevel != -1);
	}
	
	//method
	/**
	 * Removes the max selector level of the current {@link DeepConfiguration}.
	 * 
	 * @return the current {@link DeepConfiguration}.
	 */
	public DeepConfiguration removeMaxSelectorLevel() {
				
		maxSelectorLevel = -1;
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reset() {
		
		//Calls the method of the base class.
		super.reset();
		
		removeMaxSelectorLevel();
	}
		
	//method
	/**
	 * Sets the max selector level of the current {@link DeepConfiguration}.
	 * 
	 * @param maxSelectorLevel
	 * @throws NonPositiveArgumentException if the given max selector level is not positive.
	 */
	public void setMaxSelectorLevel(int maxSelectorLevel) {
		
		//Asserts that the given maxSelectorLevel is positive.
		Validator.assertThat(maxSelectorLevel).thatIsNamed("max selector level").isPositive();
				
		this.maxSelectorLevel = maxSelectorLevel;
	}
	
	//method
	/**
	 * Lets the current {@link DeepConfiguration} configure the given element recursively to the given level.
	 * 
	 * @param element
	 * @param level
	 */
	private void configure(IConfigurableElement<?> element, int level) {
		if (level > 0) {
			
			final var elements = element.getSubConfigurables();
			
			if (selects(element)) {
				setAttachingAttributesTo(element);
				elements.forEach(e -> configurations.forEach(c -> c.configure(e)));
			}
				
			elements.forEach(e -> configure(e, level - 1));
		}
	}
}
