//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link ConfigurationElement} is a {@link ConfigurationElement} that can have a {@link Configuration}.
 * 
 * @author Silvan Wyss
 * @date 2016-05-01
 * @lines 120
 */
public abstract class ConfigurationElement<CE extends ConfigurationElement<CE>> extends ConfigurableElement<CE> {
	
	//optional attribute
	private Configuration configuration;
	
	//method
	/**
	 * Adds or changes the given attribute to the current {@link ConfigurationElement}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case VariableNameCatalogue.CONFIGURATION:
				setConfiguration(new Configuration(attribute.getRefAttributes()));
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		
		//Calls method of the base class.
		final var attributes = super.getAttributes();
		
		//Handles the case that the current ConfigurationElement has a configuration.
		if (hasConfiguration()) {
			attributes.addAtEnd(configuration.getSpecificationAs(VariableNameCatalogue.CONFIGURATION));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return true if the current {@link ConfigurationElement} has a {@link Configuration}.
	 */
	public final boolean hasConfiguration() {
		return (configuration != null);
	}
	
	//method
	/**
	 * Removes the {@link Configuration} of the current {@link ConfigurationElement}.
	 * 
	 * @return the current {@link ConfigurationElement}.
	 */
	public CE removeConfiguration() {
		
		configuration = null;
		resetConfiguration();
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the {@link Configuration} of the current {@link ConfigurationElement}.
	 * 
	 * @param configuration
	 * @return the current {@link ConfigurationElement}.
	 * @throws ArgumentIsNullException if the given configuration is null.
	 */
	public CE setConfiguration(Configuration configuration) {
		
		//Asserts that the given configuration is not null.
		Validator.assertThat(configuration).thatIsNamed(VariableNameCatalogue.CONFIGURATION).isNotNull();
		
		this.configuration = configuration;
		updateFromConfiguration();
		
		return asConcrete();
	}
	
	//method
	/**
	 * Updates the current {@link Configuration} from its {@link Configuration}.
	 */
	public void updateFromConfiguration() {
		
		//Handles the case that the current ConfigurationElement has a Configuration.
		if (hasConfiguration()) {
			resetConfiguration();
			configuration.configure(this);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void resetStage2() {
		
		removeConfiguration();
		resetConfiguration();
		
		resetStage3();
	}
	
	//method
	/**
	 * Resets the current {@link ConfigurationElement}.
	 */
	protected abstract void resetStage3();
}
