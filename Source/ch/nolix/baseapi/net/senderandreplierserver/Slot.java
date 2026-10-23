/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.senderandreplierserver;

import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;

/**
 * @author Silvan Wyss
 */
public interface Slot extends NameHolder {
  /**
   * Lets the current {@link Slot} take the given backendEndPoint.
   * 
   * @param backendEndPoint
   */
  void takeBackendEndPoint(EndPoint backendEndPoint);
}
