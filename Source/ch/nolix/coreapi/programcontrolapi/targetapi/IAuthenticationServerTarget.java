//package declaration
package ch.nolix.coreapi.programcontrolapi.targetapi;

//interface
public interface IAuthenticationServerTarget extends IServerTarget {

  // method declaration
  String getLoginName();

  // method declaration
  String getLoginPassword();
}
