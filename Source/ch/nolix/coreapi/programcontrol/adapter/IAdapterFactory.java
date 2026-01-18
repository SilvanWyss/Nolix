/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.programcontrol.adapter;

/**
 * @author Silvan Wyss
 * @param <A> is the type of the adapters a {@link IAdapterFactory} creates.
 */
public interface IAdapterFactory<A> {
  /**
   * @return a new adapter.
   */
  A createAdapter();
}
