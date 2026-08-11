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
  void addChild(MultiStateConfiguration<?, S> multiStateConfiguration);

  S getBaseState();

  <T extends MultiStateConfiguration<T, S>> void removeChild(T multiStateConfiguration);
}
