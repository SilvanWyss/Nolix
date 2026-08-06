/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.style.styleholder;

import ch.nolix.systemapi.style.model.IStyle;
import ch.nolix.systemapi.style.stylable.IStylableElement;

/**
 * A {@link StyleHolder} can have a {@link IStyle} to apply to itself and to
 * its child elements.
 * 
 * @author Silvan Wyss
 * @param <E> the type of a {@link StyleHolder}.
 */
public interface StyleHolder<E extends StyleHolder<E>> extends IStylableElement<E> {
  /**
   * Applies the {@link IStyle} of the current {@link StyleHolder} to the
   * current {@link StyleHolder} and its child elements if the current
   * {@link StyleHolder} has a {{@link IStyle}
   */
  void applyStyleIfHasStyle();

  /**
   * @return true if the current {@link StyleHolder} has a {@link IStyle}, false
   *         otherwise
   */
  boolean hasStyle();

  /**
   * Removes the {@link IStyle} of the current {@link StyleHolder}.
   */
  void removeStyle();

  /**
   * Sets the given configuration to the current {@link StyleHolder}.
   * 
   * @param style
   * @return the current {@link StyleHolder}
   * @throws RuntimeException if the given configuration is null
   */
  E setStyle(IStyle style);
}
