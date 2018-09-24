//package declaration
package ch.nolix.element.configuration;

import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.specificationAPI.Configurable;
import ch.nolix.element.core.PositiveInteger;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;

//class
/**
 * @author Silvan Wyss
 * @month 2016-01
 * @lines 190
 */
public final class DeepConfiguration extends Configuration<DeepConfiguration> {

	//type name
	public static final String TYPE_NAME = "DeepConfiguration";
	
	//attribute name
	private static final String MAX_SELECTOR_LEVEL_HEADER = "MaxSelectorLevel";
	
	//optional attribute
	private PositiveInteger maxSelectorLevel;
	
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
	public DeepConfiguration(final Iterable<DocumentNodeoid> attributes) {
		addOrChangeAttributes(attributes);
	}
	
	//method
	/**
	 * Adds or changes the given attribute to this deep configuration.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 * @throws InvalidStateException if this deep configuration is frozen.
	 */
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
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
	public void configure(Configurable<?> element) {

		if (!hasMaxSelectorLevel()) {

			final ReadContainer<Configurable<?>> elements = element.getRefConfigurables();
			
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
	public List<DocumentNode> getAttributes() {
		
		//Calls method of the base class.
		final List<DocumentNode> attributes = super.getAttributes();
		
		//Handles the case that this deep configuration has a max selector level.
		if (hasMaxSelectorLevel()) {
			attributes.addAtEnd(maxSelectorLevel.getSpecificationAs(MAX_SELECTOR_LEVEL_HEADER));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the max selector level of this deep configuration.
	 * @throws UnexistingAttributeException if this deep configuration has no max selector level.
	 */
	public int getMaxSelectorLevel() {
		
		//Checks if this deep configuration has a max selector level.
		if (!hasMaxSelectorLevel()) {
			throw new UnexistingAttributeException(this, "max selector level");
		}
		
		return maxSelectorLevel.getValue();
	}
	
	//method
	/**
	 * @return true if this deep configuration has a max selector level.
	 */
	public boolean hasMaxSelectorLevel() {
		return (maxSelectorLevel != null);
	}
	
	//method
	/**
	 * Removes the max selector level of this deep configuration.
	 * 
	 * @return this deep configuration.
	 * @throws InvalidStateException if this deep configuration is frozen.
	 */
	public DeepConfiguration removeMaxSelectorLevel() {
		
		//Checks if this deep configuration is not frozen.
		supposeNotFrozen();
		
		maxSelectorLevel = null;
		
		return this;
	}
	
	//method
	/**
	 * Resets this deep configuration.
	 * 
	 * @return this deep configuration.
	 * @throws InvalidStateException if this deep configuration is frozen.
	 */
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
	 * @throws InvalidStateException if this deep configuration is frozen.
	 */
	public void setMaxSelectorLevel(int maxSelectorLevel) {
		
		//Checks if this deep configuration is not frozen.
		supposeNotFrozen();
		
		this.maxSelectorLevel = new PositiveInteger(maxSelectorLevel);
	}
	
	//method
	/**
	 * Lets this deep configuration configure the given element recursively to the given level.
	 * 
	 * @param element
	 * @param level
	 */
	private void configure(Configurable<?> element, int level) {
		if (level > 0) {
			
			final ReadContainer<Configurable<?>> elements = element.getRefConfigurables();
			
			if (selects(element)) {
				setAttachingAttributesTo(element);
				elements.forEach(e -> configurations.forEach(c -> c.configure(e)));			
			}
				
			elements.forEach(e -> configure(e, level - 1));
		}
	}
}
