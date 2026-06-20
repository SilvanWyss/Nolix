/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

/**
 * A {@link SaveStampHolder} has a save stamp.
 * 
 * @author Silvan Wyss
 */
public interface SaveStampHolder {
  /**
   * @return the save stamp of the current {@link SaveStampHolder}
   */
  String getSaveStamp();
}
