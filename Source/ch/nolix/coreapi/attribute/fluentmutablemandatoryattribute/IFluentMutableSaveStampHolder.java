package ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.ISaveStampHolder;

/**
 * A {@link IFluentMutableSaveStampHolder} is a {@link ISaveStampHolder} whose
 * save stamp can be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2024-02-11
 * @param <H> is the type of a {@link IFluentMutableSaveStampHolder}.
 */
public interface IFluentMutableSaveStampHolder<H extends IFluentMutableSaveStampHolder<H>> extends ISaveStampHolder {
  /**
   * Sets the save stamp of the current {@link IFluentMutableSaveStampHolder}.
   * 
   * @param saveStamp
   * @return the current {@link IFluentMutableSaveStampHolder}.
   * @throws RuntimeException if the given saveStamp is null.
   * @throws RuntimeException if the given saveStamp is blank.
   */
  H setSaveStamp(String saveStamp);
}
