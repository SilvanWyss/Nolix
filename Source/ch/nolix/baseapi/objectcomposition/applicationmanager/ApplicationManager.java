/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.objectcomposition.applicationmanager;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 * @param <A> the type of the applications of a {@link ApplicationManager}
 */
public interface ApplicationManager<A> {
  /**
   * @return the applications of the current {@link ApplicationManager}
   */
  ExtendedIterable<? extends A> getStoredApplications();
}
