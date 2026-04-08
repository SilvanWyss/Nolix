/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.property;

import ch.nolix.systemapi.element.mutableelement.IRespondingMutableElement;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the actual extension element of a
 *            {@link ExtensionElement}.
 */
public final class ExtensionElement<E extends IRespondingMutableElement<E>> extends AbstractExtensionElement<E> {
  private ExtensionElement(final E internalExtensionElement) {
    super(internalExtensionElement);
  }

  public static <T extends IRespondingMutableElement<T>> ExtensionElement<T> withElement(final T element) {
    return new ExtensionElement<>(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isExchangable() {
    return true;
  }
}
