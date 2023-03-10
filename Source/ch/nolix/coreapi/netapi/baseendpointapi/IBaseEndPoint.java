//package declaration
package ch.nolix.coreapi.netapi.baseendpointapi;

import ch.nolix.coreapi.netapi.netproperty.ConnectionType;
//own imports
import ch.nolix.coreapi.netapi.netproperty.PeerType;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;

//interface
public interface IBaseEndPoint extends GroupCloseable {
	
	//method declaration
	ConnectionType getConnectionType();
	
	//method declaration
	String getCustomTargetSlot();
	
	//method declaration
	PeerType getPeerType();
	
	//method declaration
	TargetSlotDefinition getTargetSlotDefinition();
	
	//method declaration
	boolean hasCustomTargetSlot();
	
	//method declaration
	boolean hasDefaultTargetSlot();
	
	//method declaration
	boolean isBackendEndPoint();
	
	//method declaration
	boolean isFrontendEndPoint();
	
	//method declaration
	boolean isLocalEndPoint();
	
	//method declaration
	boolean isRegularNetEndPoint();
	
	//method declaration
	boolean isWebEndPoint();
}
