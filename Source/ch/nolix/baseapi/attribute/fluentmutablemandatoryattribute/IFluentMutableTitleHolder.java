/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.ITitleHolder;

/**
 * A {@link IFluentMutableTitleHolder} is a {@link ITitleHolder} whose title can
 * be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link IFluentMutableTitleHolder}
 */
public interface IFluentMutableTitleHolder<H extends IFluentMutableTitleHolder<H>> extends ITitleHolder {
  /**
   * Sets the title of the current {@link IFluentMutableTitleHolder}.
   * 
   * @param title
   * @return the current {@link IFluentMutableTitleHolder}
   * @throws RuntimeException if the given title is null or blank
   */
  H setTitle(String title);
}
