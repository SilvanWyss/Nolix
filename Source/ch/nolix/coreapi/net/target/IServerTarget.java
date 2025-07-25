package ch.nolix.coreapi.net.target;

import ch.nolix.coreapi.net.securityproperty.SecurityMode;

public interface IServerTarget {

  String getIpOrDomain();

  int getPort();

  SecurityMode getSecurityModeForConnection();

  String toUrl();
}
