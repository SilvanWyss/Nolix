package ch.nolix.coreapi.netapi.endpoint3api;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;

public interface ISlot extends INameHolder {

  void takeBackendEndPoint(IEndPoint backendEndPoint);
}
