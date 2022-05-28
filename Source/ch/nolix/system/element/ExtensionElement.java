//package declaration
package ch.nolix.system.element;

import ch.nolix.systemapi.elementuniversalapi.IRespondingMutableElement;

//class
public final class ExtensionElement<E extends IRespondingMutableElement<E>> extends BaseExtensionElement<E> {
	
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
