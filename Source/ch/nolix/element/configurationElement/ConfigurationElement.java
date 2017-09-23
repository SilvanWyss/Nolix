/*
 * file:	ConfigurableElement.java
 * author:	Silvan Wyss
 * month:	2016-04
 * lines:	90
 */

//package declaration
package ch.nolix.element.configurationElement;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.bases.ConfigurableElement;
import ch.nolix.element.configuration.StandardConfiguration;

//class
/**
 * A configurable elemenet is an element that can have a configuration.
 */
public abstract class ConfigurationElement<CE extends ConfigurationElement<CE>>
extends ConfigurableElement<CE> {

	//optional attribute
	private StandardConfiguration configuration;
	
	//method
	/**
	 * @return the attributes of this configurable element
	 */
	public List<StandardSpecification> getAttributes() {
		
		List<StandardSpecification> attributes = new List<StandardSpecification>();
		
		if (hasConfiguration()) {
			attributes.addAtEnd(configuration.getSpecificationAs(StandardConfiguration.TYPE_NAME));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return true if this configurable element has a configuration
	 */
	public final boolean hasConfiguration() {
		return (configuration != null);
	}
	
	//method
	/**
	 * Removes the configuration of this configurable element.
	 */
	@SuppressWarnings("unchecked")
	public CE removeConfiguration() {
		configuration = null;
		resetConfiguration();
		
		return (CE)this;
	}
	
	public void reset() {
		removeConfiguration();
	}
	
	//method
	/**
	 * Sets the given attribute to this configurable element.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public void addOrChangeAttribute(StandardSpecification attribute) {
		switch (attribute.getHeader()) {
			case StandardConfiguration.TYPE_NAME:
				setConfiguration(new StandardConfiguration(attribute.getRefAttributes()));
				break;
			default:
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Sets the configuration of this configurable element.
	 * 
	 * @param configuration
	 */
	@SuppressWarnings("unchecked")
	public CE setConfiguration(StandardConfiguration configuration) {
		this.configuration = configuration;
		updateFromConfiguration();
		return (CE)this;
	}
	
	//method
	/**
	 * Updates this element from its configuration.
	 */
	public final void updateFromConfiguration() {
		if (hasConfiguration()) {
			resetConfiguration();
			configuration.configure(this);
		}
	}
}
