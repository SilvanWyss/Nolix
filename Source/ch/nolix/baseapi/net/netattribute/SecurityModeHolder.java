/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.netattribute;

import ch.nolix.baseapi.net.netproperty.SecurityMode;

/**
 * A {@link SecurityModeHolder} has a {@link SecurityMode}.
 * 
 * @author Silvan Wyss
 */
public interface SecurityModeHolder {
  /**
   * @return the {@link SecurityMode} of the current {@link SecurityModeHolder}.
   */
  SecurityMode getSecurityMode();
}
