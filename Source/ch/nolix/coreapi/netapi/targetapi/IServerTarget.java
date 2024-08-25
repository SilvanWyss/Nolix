//package declaration
package ch.nolix.coreapi.netapi.targetapi;

//own imports
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;

//interface
public interface IServerTarget {

  //method declaration
  String getIpOrDomain();

  //method declaration
  int getPort();

  //method declaration
  SecurityMode getSecurityModeForConnection();

  //method declaration
  String toUrl();
}
