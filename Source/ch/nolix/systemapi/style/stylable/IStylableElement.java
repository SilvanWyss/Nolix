/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.style.stylable;

import ch.nolix.baseapi.attribute.fluentmutablemultiattribute.FluentMutableMultiTokenHolder;
import ch.nolix.baseapi.attribute.fluentmutableoptionalattribute.FluentMutableOptionalIdHolder;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.programcontrol.type.TypeRequestable;
import ch.nolix.systemapi.element.mutableelement.IMutableElement;

/**
 * A {@link IStylableElement} is configurable and can contain other
 * {@link IStylableElement}s.
 * 
 * @author Silvan Wyss
 * @param <E> the type of a {@link IStylableElement}.
 */
public interface IStylableElement<E extends IStylableElement<E>>
extends
FluentMutableMultiTokenHolder<E>,
FluentMutableOptionalIdHolder<E>,
IMutableElement,
TypeRequestable {
  /**
   * @return the child {@link IStylableElement}s of the current
   *         {@link IStylableElement}.
   */
  ExtendedIterable<? extends IStylableElement<?>> getStoredChildStylableElements();

  /**
   * @param role
   * @return true if the current {@link IStylableElement} has the given role,
   *         false otherwise
   */
  boolean hasRole(String role);

  /**
   * Resets the style of the current {@link IStylableElement} and the style of the
   * child {@link IStylableElement}s of the current {@link IStylableElement}.
   */
  void resetStyleRecursively();
}
