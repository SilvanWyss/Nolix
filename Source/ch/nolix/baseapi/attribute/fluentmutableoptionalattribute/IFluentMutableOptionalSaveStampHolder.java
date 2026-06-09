/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.IOptionalSaveStampHolder;

/**
 * A {@link IFluentMutableOptionalSaveStampHolder} is a
 * {@link IOptionalSaveStampHolder} whose save stamp can be set programmatically
 * and fluently and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link IFluentMutableOptionalSaveStampHolder}
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
   * @return the current {@link IFluentMutableOptionalSaveStampHolder}
   * @throws RuntimeException if the given saveStamp is null or blank
   */
  H setSaveStamp(String saveStamp);
}
