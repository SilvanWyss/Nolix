//package declaration
package ch.nolix.coreapi.programcontrolapi.targetapi;

//own imports
import ch.nolix.coreapi.netapi.securityapi.SecurityLevel;

//interface
public interface IServerTarget {

  //method declaration
  String getIpOrDomain();

  //method declaration
  int getPort();

  //method declaration
  SecurityLevel getSecurityLevelForConnections();

  //method declaration
  String toUrl();
}
