package ch.nolix.coreapi.netapi.baseendpointapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Named;

//interface
public interface IBaseSlot<EP extends IBaseEndPoint> extends Named {
	
	//method declaration
	void takeBackendEndPoint(EP backendEndPoint);
}
