//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.core.container.AccessorContainer;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.Configurable;

//class
/**
 * @author Silvan Wyss
 * @month 2016-01
 * @lines 50
 */
public class StandardConfiguration extends Configuration<StandardConfiguration> {

	//simple class name
	public static final String SIMPLE_CLASS_NAME = "StandardConfiguration";
	
	//constructor
	/**
	 * Creates new configuration with default attributes.
	 */
	public StandardConfiguration() {}
	
	//constructor
	/**
	 * Creates new configuration with the given attributes.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	public StandardConfiguration(final Iterable<StandardSpecification> attributes) {
		addOrChangeAttributes(attributes);
	}
	
	//method
	/**
	 * Lets this configuration configure the given element.
	 * 
	 * @param element
	 */
	public final void configure(final Configurable element) {	
		if (selects(element)) {
			
			setAttachingAttributesTo(element);
						
			final AccessorContainer<Configurable> elements = element.getRefConfigurables();
			elements.forEach(e -> configurations.forEach(c -> c.configure(e)));
		}
	}
}
