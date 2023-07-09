//package declaration
package ch.nolix.system.element.style;

//own imports
import ch.nolix.systemapi.elementapi.styleapi.ISelectingStyle;
import ch.nolix.systemapi.elementapi.styleapi.IStylableElement;

//class
/**
 * @author Silvan Wyss
 * @month 2023-07-09
 */
public final class SelectingStyle extends BaseStyle<SelectingStyle> implements ISelectingStyle {
	
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
