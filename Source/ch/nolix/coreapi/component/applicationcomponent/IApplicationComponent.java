/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.component.applicationcomponent;

/**
 * A {@link IApplicationComponent} can belong to an application.
 * 
 * @author Silvan Wyss
 * @param <A> is the type of the application a {@link IApplicationComponent} can
 *            belong to.
 */
public interface IApplicationComponent<A> {
  /**
   * @return true if the current {@link IApplicationComponent} belongs to an
   *         application, false otherwise.
   */
  boolean belongsToApplication();

  /**
   * @return the application of the current {@link IApplicationComponent}.
   * @throws RuntimeException if the current {@link IApplicationComponent} does
   *                          not belong to an application.
   */
  A getStoredParentApplication();
}
