//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationInterfaces.Configurable;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;

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
	 * if the file with the given file path represents no standard configuration.
	 */
	public static StandardConfiguration createConfigurationFromFile(final String filePath) {
		StandardConfiguration standardConfiguration = new StandardConfiguration();
		standardConfiguration.loadFrom(filePath);
		return standardConfiguration;
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
	public <S extends Specification> StandardConfiguration(final Iterable<? extends Specification> attributes) {
		addOrChangeAttributes(attributes);
	}
	
	//method
	/**
	 * Lets this configuration configure the given element.
	 * 
	 * @param element
	 */
	public final void configure(final Configurable<?> element) {	
		if (selects(element)) {
			
			setAttachingAttributesTo(element);
						
			final ReadContainer<Configurable<?>> elements = element.getRefConfigurables();
			elements.forEach(e -> configurations.forEach(c -> c.configure(e)));
		}
	}
}
