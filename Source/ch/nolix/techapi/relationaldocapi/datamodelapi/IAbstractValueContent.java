//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;

//interface
public interface IAbstractValueContent extends IValueContent {

  //method declaration
  IAbstractValueContent setDataType(DataType dataType);
}
