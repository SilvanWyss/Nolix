//package declaraiton
package ch.nolix.system.element.mutableelement;

//own imports
import ch.nolix.systemapi.elementapi.mainuniversalapi.IRespondingMutableElement;

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
