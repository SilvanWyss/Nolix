/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.element.multistateconfiguration;

import ch.nolix.systemapi.element.mutableelement.IRespondingMutableElement;

/**
 * @author Silvan Wyss
 * @param <C> is the type of a {@link IMultiStateConfiguration}.
 * @param <S> is the type of the states a {@link IMultiStateConfiguration} is
 *            for.
 */
public interface IMultiStateConfiguration<C extends IMultiStateConfiguration<C, S>, S extends Enum<S>>
extends IRespondingMutableElement<C> {
  S getBaseState();
}
