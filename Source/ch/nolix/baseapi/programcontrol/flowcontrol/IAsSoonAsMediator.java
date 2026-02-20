/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.programcontrol.flowcontrol;

import ch.nolix.baseapi.programcontrol.future.IFuture;

/**
 * @author Silvan Wyss
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
