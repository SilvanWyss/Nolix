//package declaration
package ch.nolix.element.base;

//own import
import ch.nolix.element.elementapi.IMutableElement;

//class
public final class ExtensionElement<ME extends IMutableElement<ME>> extends BaseExtensionElement<ME> {
	
	//constructor
	public ExtensionElement(final String attributePrefix, final ME internalExtensionElement) {
		super(attributePrefix, internalExtensionElement);
	}
	
	//method
	@Override
	public boolean isExchangable() {
		return false;
	}
}
