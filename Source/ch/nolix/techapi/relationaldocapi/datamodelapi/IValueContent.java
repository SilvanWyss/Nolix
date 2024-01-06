//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//Java imports
import java.util.function.Predicate;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;

//interface
public interface IValueContent extends IContent {

  //method declaration
  IContainer<Predicate<String>> getConstraints();

  //method declaration
  DataType getDataType();
}
