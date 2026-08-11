/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.objectcomposition.applicationcomponent;

/**
 * A {@link ApplicationComponent} can belong to an application.
 * 
 * @author Silvan Wyss
 * @param <A> the type of the application a {@link ApplicationComponent} can
 *            belong to.
 */
public interface ApplicationComponent<A> {
  /**
   * @return true if the current {@link ApplicationComponent} belongs to an
   *         application, false otherwise
   */
  boolean belongsToApplication();

  /**
   * @return the application of the current {@link ApplicationComponent}
   * @throws RuntimeException if the current {@link ApplicationComponent} does not
   *                          belong to an application.
   */
  A getStoredParentApplication();
}
