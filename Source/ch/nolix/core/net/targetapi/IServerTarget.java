//package declaration
package ch.nolix.core.net.targetapi;

//interface
public interface IServerTarget {
	
	//method declaration
	String getIpOrAddressName();
	
	//method declaration
	int getPort();
	
	//method declaration
	String toURL();
}
