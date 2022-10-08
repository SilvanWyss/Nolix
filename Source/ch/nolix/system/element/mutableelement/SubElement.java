//package declaration
package ch.nolix.system.element.mutableelement;

//own imports
import ch.nolix.systemapi.elementapi.mainuniversalapi.IMutableElement;

//class
public final class SubElement<ME extends IMutableElement> extends BaseSubElement<ME> {
	
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
