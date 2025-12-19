package ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.ITagHolder;

/**
 * A {@link IFluentMutableTagHolder} is a {@link ITagHolder} whose tag can be
 * set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> is the type of a {@link IFluentMutableTagHolder}.
 */
public interface IFluentMutableTagHolder<H extends IFluentMutableTagHolder<H>> extends ITagHolder {
  /**
   * Sets the tag of the current {@link IFluentMutableTagHolder}.
   * 
   * @param tag
   * @return the current {@link IFluentMutableTagHolder}.
   * @throws RuntimeException if the given tag is null or blank.
   */
  H setTag(String tag);
}
