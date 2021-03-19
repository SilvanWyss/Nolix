//package declaration
package ch.nolix.element.base;

//own import
import ch.nolix.element.elementapi.IMutableElement;

//class
public final class ExchangableExtensionElement<EE extends IMutableElement<EE>> extends BaseExtensionElement<EE> {
	
	//constructor
	public ExchangableExtensionElement(final String attributePrefix, final EE internalExtensionElement) {
		super(attributePrefix, internalExtensionElement);
	}
	
	//method
	@Override
	public boolean isExchangable() {
		return true;
	}
	
	//method
	public void setExtensionElement(final EE extensionElement) {
		internalSetExtensionElement(extensionElement);
	}
}
