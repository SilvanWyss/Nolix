//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

//interface
/**
 * A {@link ISaveStampHolder} has a save stamp.
 * 
 * @author Silvan Wyss
 * @date 2024-02-11
 */
public interface ISaveStampHolder {

  //method declaration
  /**
   * @return the save stamp of the current {@link ISaveStampHolder}.
   */
  String getSaveStamp();
}
