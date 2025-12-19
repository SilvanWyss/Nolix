package ch.nolix.coreapi.programcontrol.future;

import ch.nolix.coreapi.programcontrol.process.FinishRequestable;

/**
 * A {@link IFuture} is supposed to be given back when a job is started in
 * background. A {@link IFuture} can always be asked if the background job has
 * finished or thrown an error.
 * 
 * @author Silvan Wyss
 */
public interface IFuture extends FinishRequestable {
  /**
   * @return true if the current {@link IFuture} caught an error, false otherwise.
   */
  boolean caughtError();

  /**
   * @return the error of the current {@link IFuture}.
   */
  Throwable getError();

  /**
   * @return true if the current {@link IFuture} is finished successfully, false
   *         otherwise.
   */
  boolean isFinishedSuccessfully();

  /**
   * @return true if the current {@link IFuture} is finished with an error, false
   *         otherwise.
   */
  boolean isFinishedWithError();

  /**
   * Lets the current {@link IFuture} wait until it is finished.
   */
  void waitUntilIsFinished();

  /**
   * Lets the current {@link IFuture} wait until it is finished within the given
   * timeoutInMilliseconds.
   * 
   * @param timeoutInMilliseconds
   */
  void waitUntilIsFinished(int timeoutInMilliseconds);

  /**
   * Lets the current {@link IFuture} wait until it is finished successfully.
   * 
   * @throws RuntimeException if the current {@link IFuture} catches an error.
   */
  void waitUntilIsFinishedSuccessfully();

  /**
   * Lets the current {@link IFuture} wait until it is finished successfully
   * within the given timeoutInMilliseconds.
   * 
   * @param timeoutInMilliseconds
   * @throws RuntimeException if the current {@link IFuture} catches an error.
   * @throws RuntimeException if the current {@link IFuture} reached the given
   *                          timeoutInMilliseconds before having finished.
   */
  void waitUntilIsFinishedSuccessfully(int timeoutInMilliseconds);
}
