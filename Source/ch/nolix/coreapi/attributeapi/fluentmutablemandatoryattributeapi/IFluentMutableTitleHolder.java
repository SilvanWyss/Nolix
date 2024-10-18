package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ITitleHolder;

/**
 * A {@link IFluentMutableTitleHolder} is a {@link ITitleHolder} whose title can
 * be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2019-07-26
 * @param <FMTH> is the type of a {@link IFluentMutableTitleHolder}.
 */
public interface IFluentMutableTitleHolder<FMTH extends IFluentMutableTitleHolder<FMTH>> extends ITitleHolder {

  /**
   * Sets the title of the current {@link IFluentMutableTitleHolder}.
   * 
   * @param title
   * @return the current {@link IFluentMutableTitleHolder}.
   * @throws RuntimeException if the given title is null.
   * @throws RuntimeException if the given title is blank.
   */
  FMTH setTitle(String title);
}
