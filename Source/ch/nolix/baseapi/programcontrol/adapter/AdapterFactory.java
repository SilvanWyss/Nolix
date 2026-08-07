/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.programcontrol.adapter;

/**
 * @author Silvan Wyss
 * @param <A> the type of the adapters a {@link AdapterFactory} creates.
 */
public interface AdapterFactory<A> {
  /**
   * @return a new adapter.
   */
  A createAdapter();
}
