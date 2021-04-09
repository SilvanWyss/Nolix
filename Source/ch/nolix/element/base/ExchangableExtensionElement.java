//package declaraiton
package ch.nolix.element.base;

//own import
import ch.nolix.element.elementapi.IRespondingMutableElement;

//class
public final class ExchangableExtensionElement<E extends IRespondingMutableElement<E>>
extends BaseExtensionElement<E> {
	
	//constructor
	public ExchangableExtensionElement(final E internalExtensionElement) {
		super(internalExtensionElement);
	}
	
	//method
	@Override
	public boolean isExchangable() {
		return true;
	}
	
	//method
	public void setExtensionElement(final E internalExtensionElement) {
		internalSetExtensionElement(internalExtensionElement);
	}
}
