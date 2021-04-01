//package declaraiton
package ch.nolix.element.base;

//class
public final class ExchangableExtensionElement<E extends Element<E>> extends BaseExtensionElement<E> {
	
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
