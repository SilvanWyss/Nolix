/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.net.endpoint2;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;

/**
 * @author Silvan Wyss
 */
public interface ISlot extends INameHolder {
  void takeBackendEndPoint(IEndPoint backendEndPoint);
}
