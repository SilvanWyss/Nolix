package ch.nolix.coreapi.datastructure.property;

import ch.nolix.coreapi.state.staterequest.UpToDateRequestable;

/**
 * A {@link ILazyCalculatedProperty} provides a value that is calculated lazy.
 * 
 * @author Silvan Wyss
 * @param <V> is the type of the value of a {@link ILazyCalculatedProperty}.
 */
public interface ILazyCalculatedProperty<V> extends UpToDateRequestable {
  /**
   * @return the lazy calculated value of the current
   *         {@link ILazyCalculatedProperty}.
   */
  V getStoredValue();
}
