//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.validator.Validator;

//abstract class
/**
 * A configuration elemenet is a configuration element that can have a configuration.
 * 
 * @author Silvan Wyss
 * @month 2016-04
 * @lines 120
 */
public abstract class ConfigurationElement<CE extends ConfigurationElement<CE>> extends ConfigurableElement<CE>
implements IConfigurationElement<CE> {
	
	//optional attribute
	private StandardConfiguration configuration;
	
	//method
	/**
	 * Adds or changes the given attribute to this configuration element.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	@Override
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case VariableNameCatalogue.CONFIGURATION:
				setConfiguration(new StandardConfiguration(attribute.getRefAttributes()));
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return the attributes of this configuration element.
	 */
	@Override
	public List<DocumentNode> getAttributes() {
		
		//Calls method of the base class.
		List<DocumentNode> attributes = super.getAttributes();
		
		//Handles the case that this configuration element has a configuration.
		if (hasConfiguration()) {
			attributes.addAtEnd(configuration.getSpecificationAs(VariableNameCatalogue.CONFIGURATION));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return true if this configuration element has a configuration.
	 */
	public final boolean hasConfiguration() {
		return (configuration != null);
	}
	
	//method
	/**
	 * Removes the configuration of this configuration element.
	 * 
	 * @return this configuration element.
	 */
	public CE removeConfiguration() {
		
		configuration = null;
		resetConfiguration();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Resets this configuration element.
	 * 
	 * @return this configuration element.
	 */
	@Override
	public CE reset() {
		
		removeConfiguration();
		resetConfiguration();
		
		//Calls method of the base class.
		return super.reset();
	}
	
	//method
	/**
	 * Sets the configuration of this configuration element.
	 * 
	 * @param configuration
	 * @return this configuration element.
	 * @throws NullArgumentException if the given configuration is null.
	 */
	public CE setConfiguration(StandardConfiguration configuration) {
		
		//Checks if the given configuration is not null.
		Validator
		.suppose(configuration)
		.thatIsNamed(VariableNameCatalogue.CONFIGURATION)
		.isNotNull();
		
		this.configuration = configuration;
		updateFromConfiguration();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Updates this element from its configuration.
	 */
	public final void updateFromConfiguration() {
		
		//Handles the case that this configuraiton element has a configuration.
		if (hasConfiguration()) {
			resetConfiguration();
			configuration.configure(this);
		}
	}
}
