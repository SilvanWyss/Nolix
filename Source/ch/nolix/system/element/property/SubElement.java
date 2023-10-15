//package declaration
package ch.nolix.system.element.property;

import ch.nolix.systemapi.elementapi.mainapi.IMutableElement;

//class
public final class SubElement<ME extends IMutableElement> extends BaseSubElement<ME> {

  // constructor
  public SubElement(final String attributePrefix, final ME internalExtensionElement) {
    super(attributePrefix, internalExtensionElement);
  }

  // method
  @Override
  public boolean isExchangable() {
    return false;
  }
}
