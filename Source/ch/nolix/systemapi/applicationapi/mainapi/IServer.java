//package declaration
package ch.nolix.systemapi.applicationapi.mainapi;

//own imports
import ch.nolix.coreapi.netapi.securityapi.SecurityLevel;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;
import ch.nolix.coreapi.programcontrolapi.targetapi.IServerTarget;

//interface
/**
 * A {@link IServer} can contain {@link IApplication}s.
 * 
 * @author Silvan Wyss
 * @date 2023-11-19
 */
public interface IServer extends GroupCloseable {

  //method declaration
  /**
   * @return the current {@link IServer} as {@link IServerTarget}.
   */
  IServerTarget asTarget();

  //method declaration
  /**
   * @return the {@link SecurityLevel} of the current {@link IServer}.
   */
  SecurityLevel getSecurityLevel();

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
