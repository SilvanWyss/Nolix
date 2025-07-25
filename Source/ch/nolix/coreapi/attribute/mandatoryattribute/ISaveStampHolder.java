package ch.nolix.coreapi.attribute.mandatoryattribute;

/**
 * A {@link ISaveStampHolder} has a save stamp.
 * 
 * @author Silvan Wyss
 * @version 2024-02-11
 */
public interface ISaveStampHolder {

  /**
   * @return the save stamp of the current {@link ISaveStampHolder}.
   */
  String getSaveStamp();
}
