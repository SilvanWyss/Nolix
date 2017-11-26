//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.Configurable;

//class
/**
 * @author Silvan Wyss
 * @month 2016-01
 * @lines 50
 */
public class StandardConfiguration extends Configuration<StandardConfiguration> {

	//type name
	public static final String TYPE_NAME = "StandardConfiguration";
	
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
						
			final ReadContainer<Configurable> elements = element.getRefConfigurables();
			elements.forEach(e -> configurations.forEach(c -> c.configure(e)));
		}
	}
}
