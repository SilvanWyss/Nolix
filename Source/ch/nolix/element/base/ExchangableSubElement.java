//package declaration
package ch.nolix.element.base;

//own imports
import ch.nolix.element.elementapi.IMutableElement;

//class
public final class ExchangableSubElement<ME extends IMutableElement<ME>> extends BaseSubElement<ME> {
	
	//constructor
	public ExchangableSubElement(final String attributePrefix, final ME internalSubElement) {
		super(attributePrefix, internalSubElement);
	}
	
	//method
	@Override
	public boolean isExchangable() {
		return true;
	}
	
	//method
	public void setSubElement(final ME extensionElement) {
		internalSetSubElement(extensionElement);
	}
}
