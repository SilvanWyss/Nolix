/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.netattribute;

/**
 * A {@link HostHolder} has a host.
 * 
 * @author Silvan Wyss
 */
public interface HostHolder {
  /**
   * @return the host of the current {@link HostHolder}
   */
  String getHost();
}
