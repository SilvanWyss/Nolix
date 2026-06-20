/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.SaveStampHolder;

/**
 * A {@link FluentMutableSaveStampHolder} is a {@link SaveStampHolder} whose
 * save stamp can be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableSaveStampHolder}
 */
public interface FluentMutableSaveStampHolder<H extends FluentMutableSaveStampHolder<H>> extends SaveStampHolder {
  /**
   * Sets the save stamp of the current {@link FluentMutableSaveStampHolder}.
   * 
   * @param saveStamp
   * @return the current {@link FluentMutableSaveStampHolder}
   * @throws RuntimeException if the given saveStamp is null or blank
   */
  H setSaveStamp(String saveStamp);
}
