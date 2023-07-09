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
 * @month 2023-07-09
 */
public final class SelectingStyle extends BaseStyle<SelectingStyle> implements ISelectingStyle {
	
	//constant
	public static final String TYPE_NAME = "SelectingStyle";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link SelectingStyle} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static SelectingStyle fromSpecification(final INode<?> specification) {
		
		//TODO: Implement.
		return null;
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
		}
	}
}
