//package declaration
package ch.nolix.coreapi.netapi.endpoint3api;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Named;

//interface
public interface ISlot extends Named {
	
	//method declaration
	void takeBackendEndPoint(IEndPoint backendEndPoint);
}
