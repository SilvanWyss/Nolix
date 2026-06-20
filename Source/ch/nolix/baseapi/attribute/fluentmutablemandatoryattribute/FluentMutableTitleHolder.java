/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.TitleHolder;

/**
 * A {@link FluentMutableTitleHolder} is a {@link TitleHolder} whose title can
 * be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableTitleHolder}
 */
public interface FluentMutableTitleHolder<H extends FluentMutableTitleHolder<H>> extends TitleHolder {
  /**
   * Sets the title of the current {@link FluentMutableTitleHolder}.
   * 
   * @param title
   * @return the current {@link FluentMutableTitleHolder}
   * @throws RuntimeException if the given title is null or blank
   */
  H setTitle(String title);
}
