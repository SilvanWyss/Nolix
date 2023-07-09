//package declaration
package ch.nolix.system.element.style;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;

//class
/**
 * @author Silvan Wyss
 * @date 2016-02-01
 */
public final class Style extends BaseStyle<Style> {
	
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
	public static Style fromFile(final String filePath) {
		
		final var configuration = new Style();
		configuration.resetFromFileWithFilePath(filePath);
		
		return configuration;
	}
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link Style} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static Style fromSpecification(final INode<?> specification) {
		
		final var configuration = new Style();
		configuration.resetFromSpecification(specification);
		
		return configuration;
	}
	
	//constructor
	/**
	 * Creates a new configuration with default attributes.
	 */
	public Style() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean selectsChildElements() {
		return false;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void styleElement(final IStylableElement<?> element) {
		
		if (selectsElement(element)) {
			
			setAttachingAttributesTo(element);
			
			final var elements = element.getOriChildStylableElements();
			final var configurations = getOriConfigurations();
			elements.forEach(e -> configurations.forEach(c -> c.styleElement(e)));
		}
	}
}
