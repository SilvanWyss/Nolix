/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.programcontrol.future;

/**
 * A {@link IResultFuture} is a {@link IFuture} that will deliver a result when
 * it is finished.
 * 
 * @author Silvan Wyss
 * @param <R> is the type of the result of a {@link IResultFuture}.
 */
public interface IResultFuture<R> extends IFuture {
  /**
   * @return the result of the current {@link IResultFuture}.
   */
  R getResult();

  /**
   * Waits until the current {@link IResultFuture} is finished and returns its
   * result.
   * 
   * @return the result of the current {@link IResultFuture} after waiting until
   *         it is finished.
   */
  R waitUntilIsFinishedAndGetResult();
}
