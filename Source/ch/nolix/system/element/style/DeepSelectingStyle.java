//package declaration
package ch.nolix.system.element.style;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.styleapi.ISelectingStyle;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class DeepSelectingStyle extends BaseStyle<DeepSelectingStyle> implements ISelectingStyle {
	
	//constant
	public static final String TYPE_NAME = "DeepConfiguration";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link DeepSelectingStyle} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static DeepSelectingStyle fromSpecification(final INode<?> specification) {
		
		final var deepConfiguration = new DeepSelectingStyle();
		deepConfiguration.resetFromSpecification(specification);
		
		return deepConfiguration;
	}
	
	//constructor
	/**
	 * Creates a new {@link DeepSelectingStyle}.
	 */
	public DeepSelectingStyle() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean selectsChildElements() {
		return true;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void styleElement(IStylableElement<?> element) {
		
		final var elements = element.getOriChildStylableElements();
		
		if (selectsElement(element)) {
			final var configurations = getOriConfigurations();
			setAttachingAttributesTo(element);
			elements.forEach(e -> configurations.forEach(c -> c.styleElement(e)));
		}
			
		elements.forEach(this::styleElement);
	}
}
