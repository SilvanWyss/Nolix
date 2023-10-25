//own imports
package ch.nolix.coreapi.netapi.endpoint2api;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

//interface
public interface ISlot extends INameHolder {

  //method declaration
  void takeBackendEndPoint(IEndPoint backendEndPoint);
}
