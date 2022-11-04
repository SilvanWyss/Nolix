//package declaration
package ch.nolix.coreapi.programcontrolapi.targetuniversalapi;

//interface
public interface IServerTarget {
	
	//method declaration
	String getIpOrAddressName();
	
	//method declaration
	int getPort();
	
	//method declaration
	String toURL();
}
