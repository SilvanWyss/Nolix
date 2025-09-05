package ch.nolix.coreapi.programcontrol.flowcontrol;

import ch.nolix.coreapi.programcontrol.future.IFuture;

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
