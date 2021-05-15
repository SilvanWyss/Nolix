//package declaration
package ch.nolix.element.base;

//own imports
import ch.nolix.element.elementapi.IMutableElement;

//class
public final class SubElement<ME extends IMutableElement<ME>> extends BaseSubElement<ME> {
	
	//constructor
	public SubElement(final String attributePrefix, final ME internalExtensionElement) {
		super(attributePrefix, internalExtensionElement);
	}
	
	//method
	@Override
	public boolean isExchangable() {
		return false;
	}
}
