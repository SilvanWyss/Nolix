package ch.nolix.coreapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.coreapi.attribute.optionalattribute.IOptionalTitleHolder;

/**
 * A {@link IFluentMutableOptionalTitleHolder} is a {@link IOptionalTitleHolder}
 * whose title can be set and removed programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> is the type of a {@link IFluentMutableOptionalTitleHolder}.
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
   * @return the current {@link IFluentMutableOptionalTitleHolder}.
   * @throws RuntimeException if the given title is null.
   * @throws RuntimeException if the given title is blank.
   */
  H setTitle(String title);
}
