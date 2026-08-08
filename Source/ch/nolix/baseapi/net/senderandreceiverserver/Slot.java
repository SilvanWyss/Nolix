/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.senderandreceiverserver;

import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;

/**
 * @author Silvan Wyss
 */
public interface Slot extends NameHolder {
  void takeBackendEndPoint(EndPoint backendEndPoint);
}
