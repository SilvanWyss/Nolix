package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalSaveStampHolder;

/**
 * A {@link IFluentMutableOptionalSaveStampHolder} is a
 * {@link IOptionalSaveStampHolder} whose save stamp can be set and removed
 * programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2024-02-11
 * @param <FMSSH> is the type of a
 *                {@link IFluentMutableOptionalSaveStampHolder}.
 */
public interface IFluentMutableOptionalSaveStampHolder<FMSSH extends IFluentMutableOptionalSaveStampHolder<FMSSH>>
extends IOptionalSaveStampHolder {

  /**
   * Removes the save stamp of the current
   * {@link IFluentMutableOptionalSaveStampHolder}.
   * 
   * @return the current {@link IFluentMutableOptionalSaveStampHolder}.
   */
  FMSSH removeSaveStamp();

  /**
   * Sets the save stamp of the current
   * {@link IFluentMutableOptionalSaveStampHolder}.
   * 
   * @param saveStamp
   * @return the current {@link IFluentMutableOptionalSaveStampHolder}.
   * @throws RuntimeException if the given saveStamp is null.
   * @throws RuntimeException if the given saveStamp is blank.
   */
  FMSSH setSaveStamp(String saveStamp);
}
