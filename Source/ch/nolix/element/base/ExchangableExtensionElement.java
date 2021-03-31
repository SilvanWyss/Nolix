//package declaration
package ch.nolix.element.base;

//own import
import ch.nolix.element.elementapi.IMutableElement;

//class
public final class ExchangableExtensionElement<ME extends IMutableElement<ME>> extends BaseExtensionElement<ME> {
	
	//constructor
	public ExchangableExtensionElement(final String attributePrefix, final ME internalExtensionElement) {
		super(attributePrefix, internalExtensionElement);
	}
	
	//method
	@Override
	public boolean isExchangable() {
		return true;
	}
	
	//method
	public void setExtensionElement(final ME extensionElement) {
		internalSetExtensionElement(extensionElement);
	}
}
