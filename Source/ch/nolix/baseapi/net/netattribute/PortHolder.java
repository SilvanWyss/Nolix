/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.netattribute;

/**
 * A {@link PortHolder} has a port.
 * 
 * @author Silvan Wyss
 */
public interface PortHolder {
  /**
   * @return the port of the current {@link PortHolder}
   */
  int getPort();
}
