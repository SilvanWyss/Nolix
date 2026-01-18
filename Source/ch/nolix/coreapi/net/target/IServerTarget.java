/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.net.target;

import ch.nolix.coreapi.net.securityproperty.SecurityMode;

/**
 * @author Silvan Wyss
 */
public interface IServerTarget {
  String getIpOrDomain();

  int getPort();

  SecurityMode getSecurityModeForConnection();

  String toUrl();
}
