/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.manager.applicationmanager;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 * @param <A> is the type of the applications of a {@link IApplicationManager}.
 */
public interface IApplicationManager<A> {
  /**
   * @return the applications of the current {@link IApplicationManager}.
   */
  ExtendedIterable<? extends A> getStoredApplications();
}
