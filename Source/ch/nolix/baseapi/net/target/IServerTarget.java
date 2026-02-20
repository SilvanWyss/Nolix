/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.target;

import ch.nolix.baseapi.net.securityproperty.SecurityMode;

/**
 * @author Silvan Wyss
 */
public interface IServerTarget {
  String getIpOrDomain();

  int getPort();

  SecurityMode getSecurityModeForConnection();

  String toUrl();
}
