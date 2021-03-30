//package declaration
package ch.nolix.element.base;

//class
public final class ExtensionElement<E extends Element<E>> extends BaseExtensionElement<E> {
	
	//constructor
	public ExtensionElement(final String attributePrefix, final E internalExtensionElement) {
		super(attributePrefix, internalExtensionElement);
	}
	
	//method
	@Override
	public boolean isExchangable() {
		return false;
	}
}
