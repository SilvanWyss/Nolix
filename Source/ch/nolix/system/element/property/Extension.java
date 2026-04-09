/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.property;

import ch.nolix.systemapi.element.mutableelement.IRespondingMutableElement;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the actual extension element of a
 *            {@link Extension}.
 */
public final class Extension<E extends IRespondingMutableElement<E>> extends AbstractExtensionElement<E> {
  private Extension(final E internalExtensionElement) {
    super(internalExtensionElement);
  }

  public static <T extends IRespondingMutableElement<T>> Extension<T> withElement(final T element) {
    return new Extension<>(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isExchangable() {
    return true;
  }
}
