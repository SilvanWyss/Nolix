package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ILabelHolder;

/**
 * A {@link IFluentMutableLabelHolder} is a {@link ILabelHolder} whose label can
 * be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2023-10-25
 * @param <FMLH> is the type of a {@link IFluentMutableLabelHolder}.
 */
public interface IFluentMutableLabelHolder<FMLH extends IFluentMutableLabelHolder<FMLH>> extends ILabelHolder {

  /**
   * Sets the label of the current {@link IFluentMutableLabelHolder}.
   * 
   * @param label
   * @return the current {@link IFluentMutableLabelHolder}.
   * @throws RuntimeException if the given label is null.
   * @throws RuntimeException if the given label is blank.
   */
  FMLH setLabel(String label);
}
