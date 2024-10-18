package ch.nolix.coreapi.netapi.targetapi;

import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;

public interface IServerTarget {

  String getIpOrDomain();

  int getPort();

  SecurityMode getSecurityModeForConnection();

  String toUrl();
}
