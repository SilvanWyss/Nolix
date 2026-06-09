/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.IOptionalTitleHolder;

/**
 * A {@link IFluentMutableOptionalTitleHolder} is a {@link IOptionalTitleHolder}
 * whose title can be set programmatically and fluently and removed
 * programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link IFluentMutableOptionalTitleHolder}
 */
public interface IFluentMutableOptionalTitleHolder<H extends IFluentMutableOptionalTitleHolder<H>>
extends IOptionalTitleHolder {
  /**
   * Removes the title of the current {@link IFluentMutableOptionalTitleHolder}.
   */
  void removeTitle();

  /**
   * Sets the title of the current {@link IFluentMutableOptionalTitleHolder}.
   * 
   * @param title
   * @return the current {@link IFluentMutableOptionalTitleHolder}
   * @throws RuntimeException if the given title is null or blank
   */
  H setTitle(String title);
}
