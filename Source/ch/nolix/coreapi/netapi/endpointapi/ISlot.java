package ch.nolix.coreapi.netapi.endpointapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

public interface ISlot extends INameHolder {

  void takeBackendEndPoint(IEndPoint backendEndPoint);
}
