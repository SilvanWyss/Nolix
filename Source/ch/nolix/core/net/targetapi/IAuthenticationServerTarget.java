//package declaration
package ch.nolix.core.net.targetapi;

//interface
public interface IAuthenticationServerTarget extends IServerTarget {
	
	//method declaration
	String getLoginName();
	
	//method declaration
	String getLoginPassword();
}
