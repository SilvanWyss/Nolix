package ch.nolix.coreapi.manager.applicationmanager;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 * @param <A> is the type of the applications of a {@link IApplicationManager}.
 */
public interface IApplicationManager<A> {
  /**
   * @return the applications of the current {@link IApplicationManager}.
   */
  IContainer<? extends A> getStoredApplications();
}
