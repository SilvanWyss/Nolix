//package declaration
package ch.nolix.system.element;

import ch.nolix.systemapi.elementapi.mainuniversalapi.IMutableElement;

//class
public final class SubElement<ME extends IMutableElement<ME>> extends BaseSubElement<ME> {
	
	//constructor
	public SubElement(final String attributePrefix, final ME internalExtensionElement) {
		super(attributePrefix, internalExtensionElement);
	}
	
	//method
	@Override
	public boolean isExchangable() {
		return false;
	}
}
