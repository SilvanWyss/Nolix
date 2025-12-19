package ch.nolix.coreapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.coreapi.attribute.optionalattribute.IOptionalSaveStampHolder;

/**
 * A {@link IFluentMutableOptionalSaveStampHolder} is a
 * {@link IOptionalSaveStampHolder} whose save stamp can be set and removed
 * programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> is the type of a {@link IFluentMutableOptionalSaveStampHolder}.
 */
public interface IFluentMutableOptionalSaveStampHolder<H extends IFluentMutableOptionalSaveStampHolder<H>>
extends IOptionalSaveStampHolder {
  /**
   * Removes the save stamp of the current
   * {@link IFluentMutableOptionalSaveStampHolder}.
   */
  void removeSaveStamp();

  /**
   * Sets the save stamp of the current
   * {@link IFluentMutableOptionalSaveStampHolder}.
   * 
   * @param saveStamp
   * @return the current {@link IFluentMutableOptionalSaveStampHolder}.
   * @throws RuntimeException if the given saveStamp is null.
   * @throws RuntimeException if the given saveStamp is blank.
   */
  H setSaveStamp(String saveStamp);
}
