package ch.nolix.coreapi.attribute.optionalattribute;

/**
 * A {@link IOptionalSaveStampHolder} can have a save stamp.
 * 
 * @author Silvan Wyss
 */
public interface IOptionalSaveStampHolder {
  /**
   * @return the save stamp of the current {@link IOptionalSaveStampHolder}.
   * @throws RuntimeException if the current {@link IOptionalSaveStampHolder} does
   *                          not have a save stamp.
   */
  String getSaveStamp();

  /**
   * @return true if the current {@link IOptionalSaveStampHolder} has a save
   *         stamp, false otherwise.
   */
  boolean hasSaveStamp();
}
