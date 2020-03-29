//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.baseAPI.IConfigurableElement;

//class
/**
 * @author Silvan Wyss
 * @month 2016-01
 * @lines 60
 */
public class StandardConfiguration extends Configuration<StandardConfiguration> {

	//type name
	public static final String TYPE_NAME = "StandardConfiguration";
	
	//static method
	/**
	 * @param filePath
	 * @return a new standard specification from the file with the given file path.
	 * @throws InvalidArgumentException if the given file path is not valid.
	 * @throws InvalidArgumentException
	 * if the file with the given file path does not represent a standard configuration.
	 */
	public static StandardConfiguration createConfigurationFromFile(final String filePath) {
		return new StandardConfiguration().resetFrom(filePath);
	}
	
	//constructor
	/**
	 * Creates a new configuration with default attributes.
	 */
	public StandardConfiguration() {}
	
	//constructor
	/**
	 * Creates a new configuration with the given attributes.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	public <S extends BaseNode> StandardConfiguration(final Iterable<? extends BaseNode> attributes) {
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
						
			final var elements = element.getRefConfigurables();
			elements.forEach(e -> configurations.forEach(c -> c.configure(e)));
		}
	}
}
