package ch.nolix.coreapi.net.endpoint;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;

public interface ISlot extends INameHolder {

  void takeBackendEndPoint(IEndPoint backendEndPoint);
}
