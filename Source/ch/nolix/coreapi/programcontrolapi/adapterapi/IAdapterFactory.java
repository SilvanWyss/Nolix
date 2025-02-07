package ch.nolix.coreapi.programcontrolapi.adapterapi;

/**
 * @author Silvan Wyss
 * @version 2025-02-07
 * @param <A> is the type of the adapters a {@link IAdapterFactory} creates.
 */
public interface IAdapterFactory<A> {

  /**
   * @return a new adapter.
   */
  A createAdapter();
}
