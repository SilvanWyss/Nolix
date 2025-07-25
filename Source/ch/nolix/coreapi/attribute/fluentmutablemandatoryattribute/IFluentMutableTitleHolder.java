package ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.ITitleHolder;

/**
 * A {@link IFluentMutableTitleHolder} is a {@link ITitleHolder} whose title can
 * be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2019-07-26
 * @param <H> is the type of a {@link IFluentMutableTitleHolder}.
 */
public interface IFluentMutableTitleHolder<H extends IFluentMutableTitleHolder<H>> extends ITitleHolder {

  /**
   * Sets the title of the current {@link IFluentMutableTitleHolder}.
   * 
   * @param title
   * @return the current {@link IFluentMutableTitleHolder}.
   * @throws RuntimeException if the given title is null.
   * @throws RuntimeException if the given title is blank.
   */
  H setTitle(String title);
}
