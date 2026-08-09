/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.element.multistateconfiguration;

import ch.nolix.systemapi.element.mutableelement.RespondingMutableElement;

/**
 * @author Silvan Wyss
 * @param <C> the type of a {@link IMultiStateConfiguration}.
 * @param <S> the type of the states a {@link IMultiStateConfiguration} is for.
 */
public interface IMultiStateConfiguration<C extends IMultiStateConfiguration<C, S>, S extends Enum<S>>
extends RespondingMutableElement<C> {
  void addChild(IMultiStateConfiguration<?, S> multiStateConfiguration);

  S getBaseState();

  <T extends IMultiStateConfiguration<T, S>> void removeChild(T multiStateConfiguration);
}
