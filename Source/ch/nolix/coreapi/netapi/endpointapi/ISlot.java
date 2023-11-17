//package declaration
package ch.nolix.coreapi.netapi.endpointapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

//interface
public interface ISlot extends INameHolder {

  //method declaration
  void takeBackendEndPoint(IEndPoint backendEndPoint);
}
