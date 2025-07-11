package ch.nolix.coreapi.managerapi.applicationmanagerapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

/**
 * @author Silvan Wyss
 * @version 2025-07-11
 * @param <A> is the type of the applications of a {@link IApplicationManager}.
 */
public interface IApplicationManager<A> {

  /**
   * @return the applications of the current {@link IApplicationManager}.
   */
  IContainer<? extends A> getStoredApplications();
}
