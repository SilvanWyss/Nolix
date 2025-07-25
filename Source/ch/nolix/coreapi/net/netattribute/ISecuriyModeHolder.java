package ch.nolix.coreapi.net.netattribute;

import ch.nolix.coreapi.net.securityproperty.SecurityMode;

/**
 * A {@link ISecuriyModeHolder} has a {@link SecurityMode}.
 * 
 * @author Silvan Wyss
 * @version 2025-07-11
 */
public interface ISecuriyModeHolder {

  /**
   * @return the {@link SecurityMode} of the current {@link ISecuriyModeHolder}.
   */
  SecurityMode getSecurityMode();
}
