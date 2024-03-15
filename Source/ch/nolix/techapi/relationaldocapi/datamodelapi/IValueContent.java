//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;

//interface
public interface IValueContent extends IContent {

  //method declaration
  DataType getDataType();
}
