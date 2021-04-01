//package declaration
package ch.nolix.element.base;

//class
public final class ExtensionElement<E extends Element<E>> extends BaseExtensionElement<E> {
	
	//constructor
	public ExtensionElement(final E internalExtensionElement) {
		super(internalExtensionElement);
	}
	
	//method
	@Override
	public boolean isExchangable() {
		return true;
	}
}
