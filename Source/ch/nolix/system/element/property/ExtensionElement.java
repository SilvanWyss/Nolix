package ch.nolix.system.element.property;

import ch.nolix.systemapi.elementapi.mutableelementapi.IRespondingMutableElement;

public final class ExtensionElement<E extends IRespondingMutableElement<E>> extends BaseExtensionElement<E> {

  public ExtensionElement(final E internalExtensionElement) {
    super(internalExtensionElement);
  }

  @Override
  public boolean isExchangable() {
    return true;
  }
}
