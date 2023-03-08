//package declaration
package ch.nolix.coreapi.netapi.baseendpointapi;

//own imports
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;

//interface
public interface IBaseEndPoint extends GroupCloseable {
	
	//method declaration
	ConnectionRequestRole getConnectionRequestRole();
	
	//method declaration
	String getCustomTargetSlot();
	
	//method declaration
	TargetSlotDefinition getTargetSlotDefinition();
	
	//method declaration
	EndPointType getType();
	
	//method declaration
	boolean hasBeenRequestedForConnection();
	
	//method declaration
	boolean hasCustomTargetSlot();
	
	//method declaration
	boolean hasDefaultTargetSlot();
		
	//method declaration
	boolean hasRequestedConnection();
	
	//method declaration
	boolean isLocalEndPoint();
	
	//method declaration
	boolean isRegularNetEndPoint();
	
	//method declaration
	boolean isWebEndPoint();
}
