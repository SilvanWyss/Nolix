package ch.nolix.systemapi.applicationapi.mainapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.netapi.targetapi.IServerTarget;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;

/**
 * A {@link IServer} can contain {@link IApplication}s.
 * 
 * @author Silvan Wyss
 * @version 2023-11-19
 */
public interface IServer extends Clearable, GroupCloseable {

  /**
   * @return the current {@link IServer} as {@link IServerTarget}.
   */
  IServerTarget asTarget();

  /**
   * @return the {@link SecurityMode} of the current {@link IServer}.
   */
  SecurityMode getSecurityMode();

  /**
   * @return the {@link IApplication}s of the current {@link IServer}.
   */
  IContainer<? extends IApplication<?>> getStoredApplications();

  /**
   * Removes the {@link IApplication} with the given instanceName from the current
   * {@link IServer}.
   * 
   * @param instanceName
   * @throws RuntimeException if the current {@link IServer} does not contain a
   *                          {@link IApplication} with the given instanceName.
   */
  void removeApplicationByInstanceName(final String instanceName);
}
