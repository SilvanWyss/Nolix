package ch.nolix.coreapi.net.endpoint3;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;

/**
 * @author Silvan Wyss
 */
public interface ISlot extends INameHolder {
  void takeBackendEndPoint(IEndPoint backendEndPoint);
}
