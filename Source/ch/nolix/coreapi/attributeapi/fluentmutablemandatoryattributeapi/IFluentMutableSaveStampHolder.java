//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ISaveStampHolder;

//interface
/**
 * A {@link IFluentMutableSaveStampHolder} is a {@link ISaveStampHolder} whose
 * save stamp can be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @date 2024-02-11
 * @param <FMSSH> is the type of a {@link IFluentMutableSaveStampHolder}.
 */
public interface IFluentMutableSaveStampHolder<FMSSH extends IFluentMutableSaveStampHolder<FMSSH>>
extends ISaveStampHolder {

  //method declaration
  /**
   * Sets the save stamp of the current {@link IFluentMutableSaveStampHolder}.
   * 
   * @param saveStamp
   * @return the current {@link IFluentMutableSaveStampHolder}.
   * @throws RuntimeException if the given saveStamp is null.
   * @throws RuntimeException if the given saveStamp is blank.
   */
  FMSSH setSaveStamp(String saveStamp);
}
