//package declaration
package ch.nolix.coreapi.netapi.targetuniversalapi;

//interface
public interface IAuthenticationServerTarget extends IServerTarget {
	
	//method declaration
	String getLoginName();
	
	//method declaration
	String getLoginPassword();
}
