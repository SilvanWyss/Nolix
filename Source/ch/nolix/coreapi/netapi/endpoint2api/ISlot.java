//own imports
package ch.nolix.coreapi.netapi.endpoint2api;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Named;

//interface
public interface ISlot extends Named {

  //method declaration
  void takeBackendEndPoint(IEndPoint backendEndPoint);
}
