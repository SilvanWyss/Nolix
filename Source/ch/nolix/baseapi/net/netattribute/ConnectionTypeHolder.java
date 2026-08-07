/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.netattribute;

import ch.nolix.baseapi.net.netproperty.ConnectionType;

/**
 * A {@link ConnectionTypeHolder} has a {@link ConnectionType}.
 * 
 * @author Silvan Wyss
 */
public interface ConnectionTypeHolder {
  /**
   * @return the {@link ConnectionType} of the current
   *         {@link ConnectionTypeHolder}
   */
  ConnectionType getConnectionType();
}
