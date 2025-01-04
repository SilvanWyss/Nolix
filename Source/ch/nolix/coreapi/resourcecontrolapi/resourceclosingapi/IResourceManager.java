package ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi;

/**
 * A {@link IResourceManager} manages one resource that is a
 * {@link AutoCloseable}. A {@link IResourceManager} knows if it must or must
 * not close its resource when the {@link IResourceManager} is closed itself.
 * 
 * @author Silvan Wyss
 * @version 2025-01-04
 * @param <R> is the type of the resource of a {@link IResourceManager}.
 */
public interface IResourceManager<R extends AutoCloseable> extends GroupCloseable {

  /**
   * @return the resource of the current {@link IResourceManager}.
   */
  R get();
}
