//package declaration
package ch.nolix.system.element.property;

//own imports
import ch.nolix.systemapi.elementapi.mutableelementapi.IMutableElement;

//class
public final class ExchangableSubElement<ME extends IMutableElement> extends BaseSubElement<ME> {

  //constructor
  public ExchangableSubElement(final String attributePrefix, final ME internalSubElement) {
    super(attributePrefix, internalSubElement);
  }

  //method
  @Override
  public boolean isExchangable() {
    return true;
  }

  //method
  public void setSubElement(final ME extensionElement) {
    internalSetSubElement(extensionElement);
  }
}
