/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.element.multistateconfiguration;

import ch.nolix.systemapi.element.mutableelement.RespondingMutableElement;

/**
 * @author Silvan Wyss
 * @param <C> the type of a {@link MultiStateConfiguration}.
 * @param <S> the type of the states a {@link MultiStateConfiguration} is for.
 */
public interface MultiStateConfiguration<C extends MultiStateConfiguration<C, S>, S extends Enum<S>>
extends RespondingMutableElement<C> {
  /**
   * Adds the given multiStateConfiguration as child to the current
   * {@link MultiStateConfiguration}.
   * 
   * @param multiStateConfiguration
   */
  void addChild(MultiStateConfiguration<?, S> multiStateConfiguration);

  /**
   * @return the base state of the current {@link MultiStateConfiguration}
   */
  S getBaseState();

  /**
   * Removes the given multiStateConfiguration from the children of the current
   * {@link MultiStateConfiguration}.
   * 
   * @param multiStateConfiguration
   * @param <T>                     the type of the given multiStateConfiguration
   */
  <T extends MultiStateConfiguration<T, S>> void removeChild(T multiStateConfiguration);
}
