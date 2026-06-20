/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalSaveStampHolder;

/**
 * A {@link FluentMutableOptionalSaveStampHolder} is a
 * {@link OptionalSaveStampHolder} whose save stamp can be set programmatically
 * and fluently and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableOptionalSaveStampHolder}
 */
public interface FluentMutableOptionalSaveStampHolder<H extends FluentMutableOptionalSaveStampHolder<H>>
extends OptionalSaveStampHolder {
  /**
   * Removes the save stamp of the current
   * {@link FluentMutableOptionalSaveStampHolder}.
   */
  void removeSaveStamp();

  /**
   * Sets the save stamp of the current
   * {@link FluentMutableOptionalSaveStampHolder}.
   * 
   * @param saveStamp
   * @return the current {@link FluentMutableOptionalSaveStampHolder}
   * @throws RuntimeException if the given saveStamp is null or blank
   */
  H setSaveStamp(String saveStamp);
}
