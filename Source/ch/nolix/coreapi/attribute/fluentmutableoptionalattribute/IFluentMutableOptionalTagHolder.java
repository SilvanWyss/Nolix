package ch.nolix.coreapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.ITagHolder;

/**
 * A {@link IFluentMutableOptionalTagHolder} is a {@link ITagHolder} whose tag
 * can be set and removed programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2025-11-07
 * @param <H> is the type of a {@link IFluentMutableOptionalTagHolder}.
 */
public interface IFluentMutableOptionalTagHolder<H extends IFluentMutableOptionalTagHolder<H>> extends ITagHolder {
  /**
   * Removes the tag of the current {@link IFluentMutableOptionalTagHolder}.
   */
  void removeTag();

  /**
   * Sets the tag of the current {@link IFluentMutableOptionalTagHolder}.
   * 
   * @param tag
   * @return the current {@link IFluentMutableOptionalTagHolder}.
   * @throws RuntimeException if the given tag is null or blank.
   */
  H setTag(String tag);
}
