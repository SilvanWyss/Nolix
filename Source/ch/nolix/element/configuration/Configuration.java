//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.elementapi.IConfigurableElement;

//class
/**
 * @author Silvan Wyss
 * @date 2016-02-01
 * @lines 70
 */
public final class Configuration extends BaseConfiguration<Configuration> {

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
	public static Configuration fromFile(final String filePath) {
		
		final var configuration = new Configuration();
		configuration.resetFromFile(filePath);
		
		return configuration;
	}
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link Configuration} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static Configuration fromSpecification(final BaseNode specification) {
		
		final var configuration = new Configuration();
		configuration.resetFrom(specification);
		
		return configuration;
	}
	
	//constructor
	/**
	 * Creates a new configuration with default attributes.
	 */
	public Configuration() {}
	
	//method
	/**
	 * Lets this configuration configure the given element.
	 * 
	 * @param element
	 */
	@Override
	public void configure(final IConfigurableElement<?> element) {
		if (selects(element)) {
			
			setAttachingAttributesTo(element);
						
			final var elements = element.getSubConfigurables();
			elements.forEach(e -> configurations.forEach(c -> c.configure(e)));
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetBaseConfiguration() {}
}
