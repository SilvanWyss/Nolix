//package declaration
package ch.nolix.element.base;

//own import
import ch.nolix.element.elementapi.IMutableElement;

//class
public final class ExtensionElement<EE extends IMutableElement<EE>> extends BaseExtensionElement<EE> {
	
	//constructor
	public ExtensionElement(final String attributePrefix, final EE internalExtensionElement) {
		super(attributePrefix, internalExtensionElement);
	}
	
	//method
	@Override
	public boolean isExchangable() {
		return false;
	}
}
