package ch.nolix.systemapi.application.main;

import ch.nolix.coreapi.manager.applicationmanager.IApplicationManager;
import ch.nolix.coreapi.net.netattribute.ISecuriyModeHolder;
import ch.nolix.coreapi.net.target.IServerTarget;
import ch.nolix.coreapi.resourcecontrol.resourceclosing.GroupCloseable;
import ch.nolix.coreapi.state.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @version 2023-11-19
 * @param <S> is the type of a {@link IServer}.
 */
public interface IServer<S extends IServer<S>>
extends Clearable, GroupCloseable, IApplicationManager<IApplication<?, ?>>, ISecuriyModeHolder {

  /**
   * Adds the given application to the current {@link IServer}.
   * 
   * @param application
   * @return the current {@link IServer}.
   * @throws RuntimeException if the given application is null.
   * @throws RuntimeException if the current {@link IServer} contains already a
   *                          {@link IApplication} with the same instanceName as
   *                          the given application.
   */
  S addApplication(final IApplication<?, ?> application);

  /**
   * @return a target representation of the current {@link IServer}.
   */
  IServerTarget asTarget();

  /**
   * Removes the {@link IApplication} with the given instanceName from the current
   * {@link IServer}.
   * 
   * @param instanceName
   * @throws RuntimeException if the current {@link IServer} does not contain a
   *                          {@link IApplication} with the given instanceName.
   */
  void removeApplicationWithInstanceName(final String instanceName);
}
