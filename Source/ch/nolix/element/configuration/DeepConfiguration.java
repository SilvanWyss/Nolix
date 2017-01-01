/*
 * file:	DeepConfiguration.java
 * author:	Silvan Wyss
 * month:	2016-01
 * lines:	190
 */

//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.specification.Configurable;
import ch.nolix.common.specification.Specification;
import ch.nolix.element.basic.PositiveInteger;

//class
public final class DeepConfiguration extends Configuration<DeepConfiguration> {

	//constant
	public final static String SIMPLE_CLASS_NAME = "DeepConfiguration";
	
	//attribute header
	private final static String MAX_SELECTOR_LEVEL = "MaxSelectorLevel";
	
	//optional attribute
	private PositiveInteger maxSelectorLevel;
	
	//constructor
	/**
	 * Creates new deep configuration with default attributes.
	 */
	public DeepConfiguration() {}
	
	//constructor
	/**
	 * Creates new deep configuration with the given attributes.
	 * @param attributes
	 * @throws Exception if the given attributes are not valid
	 */
	public DeepConfiguration(List<Specification> attributes) {
		addOrChangeAttributes(attributes);
	}
	
	//method
	/**
	 * Configures the given element.
	 * @param element
	 */
	public final void configure(Configurable element) {

		if (hasMaxSelectorLevel()) {
			configure(element, getMaxSelectorLevel());
		}
		else {
			
			final List<Configurable> elements = element.getRefConfigurables();
			
			if (selects(element)) {
				
				setAttachingAttributesTo(element);
				
				elements.forEach(e -> configurations.forEach(c -> c.configure(e)));			
			}
				
			elements.forEach(e -> configure(e));
		}
	}
	
	//method
	/**
	 * @return the attributes of this deep configuration
	 */
	public final List<Specification> getAttributes() {
		
		//Calls method of the base class.
		final List<Specification> attributes = super.getAttributes();
		
		if (hasMaxSelectorLevel()) {
			attributes.addAtEnd(maxSelectorLevel.getSpecificationAs(MAX_SELECTOR_LEVEL));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the max selector level of this deep configuration
	 */
	public final int getMaxSelectorLevel() {
		return maxSelectorLevel.getValue();
	}
	
	//method
	/**
	 * @return true if this deep configuration has a max selector level
	 */
	public final boolean hasMaxSelectorLevel() {
		return (maxSelectorLevel != null);
	}
	
	//method
	/**
	 * Removes the max selector level of this deep configuration.
	 * 
	 * @return this deep configuration
	 * @throws Exception if this deep configuration is frozen
	 */
	public final DeepConfiguration removeMaxSelectorLevel() {
		
		throwExceptionIfFrozen();
		
		maxSelectorLevel = null;
		
		return this;
	}
	
	//method
	/**
	 * Resets this deep configuration.
	 * 
	 * @throws Exception if this deep configuration is frozen
	 */
	public final void reset() {
				
		//Calls the method of the base class.
		super.reset();
		
		removeMaxSelectorLevel();
	}
	
	//method
	/**
	 * Sets the given attribute to this deep configuration.
	 * 
	 * @param attribute
	 * @throws Exception if:
	 * -The given attribute is not valid.
	 * -This deep configuration is frozen.
	 */
	public final void addOrChangeAttribute(Specification attribute) {
		
		throwExceptionIfFrozen();
		
		switch (attribute.getHeader()) {
			case MAX_SELECTOR_LEVEL:
				setMaxSelectorLevel(attribute.getOneAttributeToInteger());
				break;
			default:
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Sets the max selector level of this deep configuration.
	 * @param maxSelectorLevel
	 * @throws Exception if:
	 * -The given max selector level is not positive.
	 * -This deep configuration is frozen.
	 */
	public final void setMaxSelectorLevel(int maxSelectorLevel) {
		
		throwExceptionIfFrozen();
		
		this.maxSelectorLevel = new PositiveInteger(maxSelectorLevel);
	}
	
	//method
	/**
	 * Lets this deep configuration configure the given element until the given max selector level.
	 * 
	 * @param element
	 * @param maxSelectorLevel
	 */
	private void configure(Configurable element, int maxSelectorLevel) {
		if (maxSelectorLevel > 0) {
			
			final List<Configurable> elements = element.getRefConfigurables();
			
			if (selects(element)) {
				
				setAttachingAttributesTo(element);
				
				elements.forEach(e -> configurations.forEach(c -> c.configure(e)));			
			}
				
			elements.forEach(e -> configure(e, maxSelectorLevel - 1));
		}
	}
}
