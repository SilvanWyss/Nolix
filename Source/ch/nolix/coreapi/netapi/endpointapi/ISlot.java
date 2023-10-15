//package declaration
package ch.nolix.coreapi.netapi.endpointapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Named;

//interface
public interface ISlot extends Named {

  // method declaration
  void takeBackendEndPoint(IEndPoint backendEndPoint);
}
