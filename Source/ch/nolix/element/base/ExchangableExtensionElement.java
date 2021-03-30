//package declaration
package ch.nolix.element.base;

//class
public final class ExchangableExtensionElement<E extends Element<E>> extends BaseExtensionElement<E> {
	
	//constructor
	public ExchangableExtensionElement(final String attributePrefix, final E internalExtensionElement) {
		super(attributePrefix, internalExtensionElement);
	}
	
	//method
	@Override
	public boolean isExchangable() {
		return true;
	}
	
	//method
	public void setExtensionElement(final E extensionElement) {
		internalSetExtensionElement(extensionElement);
	}
}
