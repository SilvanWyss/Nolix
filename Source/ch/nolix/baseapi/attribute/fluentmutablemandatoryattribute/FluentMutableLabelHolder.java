/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.LabelHolder;

/**
 * A {@link FluentMutableLabelHolder} is a {@link LabelHolder} whose label can
 * be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableLabelHolder}
 */
public interface FluentMutableLabelHolder<H extends FluentMutableLabelHolder<H>> extends LabelHolder {
  /**
   * Sets the label of the current {@link FluentMutableLabelHolder}.
   * 
   * @param label
   * @return the current {@link FluentMutableLabelHolder}
   * @throws RuntimeException if the given label is null or blank
   */
  H setLabel(String label);
}
