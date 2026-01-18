/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.attribute.mandatoryattribute;

/**
 * A {@link ISaveStampHolder} has a save stamp.
 * 
 * @author Silvan Wyss
 */
public interface ISaveStampHolder {
  /**
   * @return the save stamp of the current {@link ISaveStampHolder}.
   */
  String getSaveStamp();
}
