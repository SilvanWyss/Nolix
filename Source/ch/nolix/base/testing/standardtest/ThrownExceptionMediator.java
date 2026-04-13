/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.testing.standardtest;

/**
 * A {@link ThrownExceptionMediator} is not mutable.
 * 
 * A {@link ThrownExceptionMediator} does not need to have an exception. In the
 * case an exception was expected, but not thrown, a
 * {@link ThrownExceptionMediator} must be created, but an exception cannot be
 * given to it.
 * 
 * @author Silvan Wyss
 */
public final class ThrownExceptionMediator extends AbstractThrownExceptionMediator {
  /**
   * Creates a new {@link ThrownExceptionMediator} without exception.
   */
  private ThrownExceptionMediator() {
    super();
  }

  /**
   * Creates a new {@link ThrownExceptionMediator} for the given exception.
   * 
   * @param exception
   * @throws RuntimeException if the given exception is null.
   */
  private ThrownExceptionMediator(final Throwable exception) {
    //Calls constructor of the base class.
    super(exception);
  }

  /**
   * @param exception
   * @return a new {@link ThrownExceptionMediator} for the given exception.
   * @throws RuntimeException if the given exception is null.
   */
  public static ThrownExceptionMediator forExcetpion(final Throwable exception) {
    return new ThrownExceptionMediator(exception);
  }

  /**
   * @return a new {@link ThrownExceptionMediator} without exception.
   */
  public static ThrownExceptionMediator withoutException() {
    return new ThrownExceptionMediator();
  }
}
