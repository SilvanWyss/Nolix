//package declaration
package ch.nolix.systemapi.applicationapi.mainapi;

import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.netapi.targetapi.IServerTarget;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;

//interface
/**
 * A {@link IServer} can contain {@link IApplication}s.
 * 
 * @author Silvan Wyss
 * @date 2023-11-19
 */
public interface IServer extends Clearable, GroupCloseable {

  //method declaration
  /**
   * @return the current {@link IServer} as {@link IServerTarget}.
   */
  IServerTarget asTarget();

  //method declaration
  /**
   * @return the {@link SecurityMode} of the current {@link IServer}.
   */
  SecurityMode getSecurityLevel();

  //method
  /**
   * Removes the {@link IApplication} with the given instanceName from the current
   * {@link IServer}.
   * 
   * @param instanceName
   * @throws RuntimeException if the current {@link IServer} does not contain a
   *                          {@link IApplication} with the given instance name.
   */
  void removeApplicationByInstanceName(final String instanceName);
}
