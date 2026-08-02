/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.programcontrol.property;

import ch.nolix.baseapi.generalstate.staterequest.UpToDateRequestable;

/**
 * A {@link ILazyCalculatedProperty} provides a value that is calculated lazy.
 * 
 * @author Silvan Wyss
 * @param <V> the type of the value of a {@link ILazyCalculatedProperty}
 */
public interface ILazyCalculatedProperty<V> extends UpToDateRequestable {
  /**
   * @return the lazy calculated value of the current
   *         {@link ILazyCalculatedProperty}
   */
  V getStoredValue();
}
