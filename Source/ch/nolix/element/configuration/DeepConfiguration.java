//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.Configurable;
import ch.nolix.element.basic.PositiveInteger;

//class
/**
 * @author Silvan Wyss
 * @month 2016-01
 * @lines 190
 */
public final class DeepConfiguration extends Configuration<DeepConfiguration> {

	//simple class name
	public static final String SIMPLE_CLASS_NAME = "DeepConfiguration";
	
	//attribute name
	private static final String MAX_SELECTOR_LEVEL = "MaxSelectorLevel";
	
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
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	public DeepConfiguration(final Iterable<StandardSpecification> attributes) {
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
	public void addOrChangeAttribute(final StandardSpecification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case MAX_SELECTOR_LEVEL:
				setMaxSelectorLevel(attribute.getOneAttributeToInteger());
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
	public void configure(Configurable element) {

		if (!hasMaxSelectorLevel()) {

			final List<Configurable> elements = element.getRefConfigurables();
			
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
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		//Handles the option that this deep configuration has a max selector level.
		if (hasMaxSelectorLevel()) {
			attributes.addAtEnd(maxSelectorLevel.getSpecificationAs(MAX_SELECTOR_LEVEL));
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
	 * @throws InvalidStateException if this deep configuration is frozen.
	 */
	public void reset() {
				
		//Calls the method of the base class.
		super.reset();
		
		removeMaxSelectorLevel();
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
	private void configure(Configurable element, int level) {
		if (level > 0) {
			
			final List<Configurable> elements = element.getRefConfigurables();
			
			if (selects(element)) {
				setAttachingAttributesTo(element);
				elements.forEach(e -> configurations.forEach(c -> c.configure(e)));			
			}
				
			elements.forEach(e -> configure(e, level - 1));
		}
	}
}
