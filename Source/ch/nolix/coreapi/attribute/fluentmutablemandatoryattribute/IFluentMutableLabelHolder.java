/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.ILabelHolder;

/**
 * A {@link IFluentMutableLabelHolder} is a {@link ILabelHolder} whose label can
 * be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> is the type of a {@link IFluentMutableLabelHolder}.
 */
public interface IFluentMutableLabelHolder<H extends IFluentMutableLabelHolder<H>> extends ILabelHolder {
  /**
   * Sets the label of the current {@link IFluentMutableLabelHolder}.
   * 
   * @param label
   * @return the current {@link IFluentMutableLabelHolder}.
   * @throws RuntimeException if the given label is null.
   * @throws RuntimeException if the given label is blank.
   */
  H setLabel(String label);
}
