/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.endpoint2;

import ch.nolix.baseapi.attribute.mandatoryattribute.INameHolder;

/**
 * @author Silvan Wyss
 */
public interface ISlot extends INameHolder {
  void takeBackendEndPoint(IEndPoint backendEndPoint);
}
