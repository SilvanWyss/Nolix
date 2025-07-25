package ch.nolix.coreapi.net.endpoint3;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;

public interface ISlot extends INameHolder {

  void takeBackendEndPoint(IEndPoint backendEndPoint);
}
