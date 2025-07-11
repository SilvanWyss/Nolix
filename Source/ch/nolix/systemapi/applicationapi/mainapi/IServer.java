package ch.nolix.systemapi.applicationapi.mainapi;

import ch.nolix.coreapi.managerapi.applicationmanagerapi.IApplicationManager;
import ch.nolix.coreapi.netapi.netattributeapi.ISecuriyModeHolder;
import ch.nolix.coreapi.netapi.targetapi.IServerTarget;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;

/**
 * @author Silvan Wyss
 * @version 2023-11-19
 */
public interface IServer extends Clearable, GroupCloseable, IApplicationManager<IApplication<?>>, ISecuriyModeHolder {

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
