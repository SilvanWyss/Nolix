//package declaration
package ch.nolix.coreapi.netapi.endpoint3api;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

//interface
public interface ISlot extends INameHolder {

  //method declaration
  void takeBackendEndPoint(IEndPoint backendEndPoint);
}
