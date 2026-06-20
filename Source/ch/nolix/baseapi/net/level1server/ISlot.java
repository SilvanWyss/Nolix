/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.level1server;

import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;

/**
 * @author Silvan Wyss
 */
public interface ISlot extends NameHolder {
  void takeBackendEndPoint(IEndPoint backendEndPoint);
}
