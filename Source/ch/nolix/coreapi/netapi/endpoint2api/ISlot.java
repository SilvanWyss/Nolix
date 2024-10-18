package ch.nolix.coreapi.netapi.endpoint2api;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

public interface ISlot extends INameHolder {

  void takeBackendEndPoint(IEndPoint backendEndPoint);
}
