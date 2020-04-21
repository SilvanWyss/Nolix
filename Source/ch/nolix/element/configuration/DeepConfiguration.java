//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.elementAPI.IConfigurableElement;

//class
/**
 * @author Silvan Wyss
 * @month 2016-01
 * @lines 200
 */
public final class DeepConfiguration extends Configuration<DeepConfiguration> {

	//type name
	public static final String TYPE_NAME = "DeepConfiguration";
	
	//attribute name
	private static final String MAX_SELECTOR_LEVEL_HEADER = "MaxSelectorLevel";
	
	//optional attribute
	private int maxSelectorLevel = -1;
	
	//constructor
	/**
	 * Creates a new deep configuration with default attributes.
	 */
	public DeepConfiguration() {}
	
	//constructor
	/**
	 * Creates a new deep configuration with the given attributes.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	public DeepConfiguration(final Iterable<BaseNode> attributes) {
		addOrChangeAttributes(attributes);
	}
	
	//method
	/**
	 * Adds or changes the given attribute to this deep configuration.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 * @throws InvalidArgumentException if this deep configuration is frozen.
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
	 * Lets this configuration configure the given element.
	 * 
	 * @param element
	 */
	@Override
	public void configure(IConfigurableElement<?> element) {

		if (!hasMaxSelectorLevel()) {

			final var elements = element.getRefConfigurables();
			
			if (selects(element)) {
				setAttachingAttributesTo(element);
				elements.forEach(e -> configurations.forEach(c -> c.configure(e)));
			}
				
			elements.forEach(e -> configure(e));
		}
		else {
			configure(element, getMaxSelectorLevel());
		}
	}
	
	//method
	/**
	 * @return the attributes of this deep configuration.
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		
		//Calls method of the base class.
		final LinkedList<Node> attributes = super.getAttributes();
		
		//Handles the case that this deep configuration has a max selector level.
		if (hasMaxSelectorLevel()) {
			attributes.addAtEnd(new Node(MAX_SELECTOR_LEVEL_HEADER, maxSelectorLevel));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the max selector level of this deep configuration.
	 * @throws ArgumentDoesNotHaveAttributeException if this deep configuration does not have a max selector level.
	 */
	public int getMaxSelectorLevel() {
		
		//Asserts that this deep configuration has a max selector level.
		if (!hasMaxSelectorLevel()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "max selector level");
		}
		
		return maxSelectorLevel;
	}
	
	//method
	/**
	 * @return true if this deep configuration has a max selector level.
	 */
	public boolean hasMaxSelectorLevel() {
		return (maxSelectorLevel != -1);
	}
	
	//method
	/**
	 * Removes the max selector level of this deep configuration.
	 * 
	 * @return this deep configuration.
	 * @throws InvalidArgumentException if this deep configuration is frozen.
	 */
	public DeepConfiguration removeMaxSelectorLevel() {
		
		//Asserts that this deep configuration is not frozen.
		supposeNotFrozen();
		
		maxSelectorLevel = -1;
		
		return this;
	}
	
	//method
	/**
	 * Resets this deep configuration.
	 * 
	 * @return this deep configuration.
	 * @throws InvalidArgumentException if this deep configuration is frozen.
	 */
	@Override
	public DeepConfiguration reset() {
				
		removeMaxSelectorLevel();
		
		//Calls the method of the base class.
		return super.reset();
	}
		
	//method
	/**
	 * Sets the max selector level of this deep configuration.
	 * 
	 * @param maxSelectorLevel
	 * @throws NonPositiveArgumentException if the given max selector level is not positive.
	 * @throws InvalidArgumentException if this deep configuration is frozen.
	 */
	public void setMaxSelectorLevel(int maxSelectorLevel) {
		
		//Asserts that the given maxSelectorLevel is positive.
		Validator.assertThat(maxSelectorLevel).thatIsNamed("max selector level").isPositive();
		
		//Asserts that this deep configuration is not frozen.
		supposeNotFrozen();
		
		this.maxSelectorLevel = maxSelectorLevel;
	}
	
	//method
	/**
	 * Lets this deep configuration configure the given element recursively to the given level.
	 * 
	 * @param element
	 * @param level
	 */
	private void configure(IConfigurableElement<?> element, int level) {
		if (level > 0) {
			
			final var elements = element.getRefConfigurables();
			
			if (selects(element)) {
				setAttachingAttributesTo(element);
				elements.forEach(e -> configurations.forEach(c -> c.configure(e)));
			}
				
			elements.forEach(e -> configure(e, level - 1));
		}
	}
}
