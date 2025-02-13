package ch.nolix.coreapi.programcontrolapi.flowcontrolapi;

import ch.nolix.coreapi.programcontrolapi.futureapi.IFuture;

/**
 * @author Silvan Wyss
 * @version 2025-02-13
 */
public interface IAsSoonAsMediator {

  /**
   * Lets the current {@link IAsSoonAsMediator} run the given job asynchronously
   * in background.
   * 
   * @param job
   * @return a new {@link IFuture}.
   * @throws RuntimeException if the given job is null.
   */
  IFuture runInBackground(Runnable job);
}
