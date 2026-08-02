/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.generalstate.staterequest;

/**
 * A {@link StateRequestable} has a certain state.
 * 
 * @author Silvan Wyss
 * @param <S> the type of the state of a {@link StateRequestable}.
 */
public interface StateRequestable<S extends Enum<S>> {
  /**
   * @return the state of the current {@link StateRequestable}.
   */
  S getState();
}
