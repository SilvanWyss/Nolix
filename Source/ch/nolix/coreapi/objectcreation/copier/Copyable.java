/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.objectcreation.copier;

/**
 * A {@link Copyable} can create a copy of itself.
 * 
 * @author Silvan Wyss
 * @param <C> is the type of a {@link Copyable}.
 */
public interface Copyable<C extends Copyable<C>> {
  /**
   * @return a new copy of the current {@link Copyable}.
   */
  C getCopy();
}
