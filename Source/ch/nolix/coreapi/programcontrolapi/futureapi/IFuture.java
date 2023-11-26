//package declaration
package ch.nolix.coreapi.programcontrolapi.futureapi;

//own imports
import ch.nolix.coreapi.methodapi.requestapi.FinishRequestable;

//interface
/**
 * A {@link IFuture} is supposed to be given back when a job is started in
 * background. A {@link IFuture} can always be asked if the background job has
 * finished or thrown an error.
 * 
 * @author Silvan Wyss
 * @date 2019-04-14
 */
public interface IFuture extends FinishRequestable {

  //method declaration
  /**
   * @return true if the current {@link IFuture} caught an error.
   */
  boolean caughtError();

  //method declaration
  /**
   * @return the error of the current {@link IFuture}.
   */
  Throwable getError();

  //method declaration
  /**
   * @return true if the current {@link IFuture} is finished successfully.
   */
  boolean isFinishedSuccessfully();

  //method declaration
  /**
   * @return true if the current {@link IFuture} is finished with an error.
   */
  boolean isFinishedWithError();

  //method declaration
  /**
   * Lets the current {@link IFuture} wait until it is finished.
   */
  void waitUntilIsFinished();

  //method declaration
  /**
   * Lets the current {@link IFuture} wait until it is finished within the given
   * timeoutInMilliseconds.
   * 
   * @param timeoutInMilliseconds
   */
  void waitUntilIsFinished(int timeoutInMilliseconds);

  //method declaration
  /**
   * Lets the current {@link IFuture} wait until it is finished successfully.
   * 
   * @throws RuntimeException if the current {@link IFuture} catches an error.
   */
  void waitUntilIsFinishedSuccessfully();

  //method declaration
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
