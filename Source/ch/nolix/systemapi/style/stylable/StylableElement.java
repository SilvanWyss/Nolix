/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.style.stylable;

import ch.nolix.baseapi.attribute.fluentmutablemultiattribute.FluentMutableMultiTokenHolder;
import ch.nolix.baseapi.attribute.fluentmutableoptionalattribute.FluentMutableOptionalIdHolder;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.programcontrol.type.TypeRequestable;
import ch.nolix.systemapi.element.mutableelement.MutableElement;

/**
 * A {@link StylableElement} is configurable and can contain other
 * {@link StylableElement}s.
 * 
 * @author Silvan Wyss
 * @param <E> the type of a {@link StylableElement}.
 */
public interface StylableElement<E extends StylableElement<E>>
extends
FluentMutableMultiTokenHolder<E>,
FluentMutableOptionalIdHolder<E>,
MutableElement,
TypeRequestable {
  /**
   * @return the child {@link StylableElement}s of the current
   *         {@link StylableElement}.
   */
  ExtendedIterable<? extends StylableElement<?>> getStoredChildStylableElements();

  /**
   * @param role
   * @return true if the current {@link StylableElement} has the given role, false
   *         otherwise
   */
  boolean hasRole(String role);

  /**
   * Resets the style of the current {@link StylableElement} and the style of the
   * child {@link StylableElement}s of the current {@link StylableElement}.
   */
  void resetStyleRecursively();
}
