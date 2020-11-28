//package declaration
package ch.nolix.element.configuration;

import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.elementapi.IConfigurableElement;

//class
/**
 * @author Silvan Wyss
 * @month 2016-01
 * @lines 60
 */
public class Configuration extends BaseConfiguration<Configuration> {

	//constant
	public static final String TYPE_NAME = "StandardConfiguration";
	
	//static method
	/**
	 * @param filePath
	 * @return a new standard specification from the file with the given file path.
	 * @throws InvalidArgumentException if the given file path is not valid.
	 * @throws InvalidArgumentException
	 * if the file with the given file path does not represent a standard configuration.
	 */
	public static Configuration createConfigurationFromFile(final String filePath) {
		return new Configuration().resetFrom(filePath);
	}
	
	//constructor
	/**
	 * Creates a new configuration with default attributes.
	 */
	public Configuration() {}
	
	//constructor
	/**
	 * Creates a new configuration with the given attributes.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	public <S extends BaseNode> Configuration(final Iterable<? extends BaseNode> attributes) {
		addOrChangeAttributes(attributes);
	}
	
	//method
	/**
	 * Lets this configuration configure the given element.
	 * 
	 * @param element
	 */
	@Override
	public final void configure(final IConfigurableElement<?> element) {
		if (selects(element)) {
			
			setAttachingAttributesTo(element);
						
			final var elements = element.getSubConfigurables();
			elements.forEach(e -> configurations.forEach(c -> c.configure(e)));
		}
	}
}
