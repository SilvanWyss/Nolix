//package declaration
package ch.nolix.core.net.baseendpoint;

//own imports
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.coreapi.netapi.baseendpointapi.IBaseEndPoint;
import ch.nolix.coreapi.netapi.baseendpointapi.TargetSlotDefinition;
import ch.nolix.coreapi.netapi.netproperty.ConnectionType;
import ch.nolix.coreapi.netapi.netproperty.PeerType;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.ICloseController;

//class
public abstract class BaseEndPoint implements IBaseEndPoint {
	
	//attribute
	private final CloseController closeController = CloseController.forElement(this);
	
	//method
	@Override
	public final ICloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public final boolean hasCustomTargetSlot() {
		return (getTargetSlotDefinition() == TargetSlotDefinition.CUSTOM);
	}
	
	//method
	@Override
	public final boolean hasDefaultTargetSlot() {
		return (getTargetSlotDefinition() == TargetSlotDefinition.DEFAULT);
	}
	
	//method
	@Override
	public final boolean isBackendEndPoint() {
		return (getPeerType() == PeerType.BACKEND);
	}
	
	//method
	@Override
	public final boolean isFrontendEndPoint() {
		return (getPeerType() == PeerType.FRONTEND);
	}
	
	//method
	@Override
	public final boolean isLocalEndPoint() {
		return (getConnectionType() == ConnectionType.LOCAL);
	}
	
	//method
	@Override
	public final boolean isRegularNetEndPoint() {
		return (getConnectionType() == ConnectionType.REGULAR_SOCKET);
	}
	
	//method
	@Override
	public final boolean isWebEndPoint() {
		return (getConnectionType() == ConnectionType.WEB_SOCKET);
	}
}
