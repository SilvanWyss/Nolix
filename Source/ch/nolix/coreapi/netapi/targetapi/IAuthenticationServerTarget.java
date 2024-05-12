//package declaration
package ch.nolix.coreapi.netapi.targetapi;

//interface
public interface IAuthenticationServerTarget extends IServerTarget {

  //method declaration
  String getLoginName();

  //method declaration
  String getLoginPassword();
}
