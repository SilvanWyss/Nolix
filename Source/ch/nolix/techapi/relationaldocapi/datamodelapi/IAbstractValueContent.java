//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//Java imports
import java.util.function.Predicate;

//own imports
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;

//interface
public interface IAbstractValueContent extends IValueContent {

  //method declaration
  IAbstractValueContent addConstraint(Predicate<String> constraint);

  //method declaration
  void removeConstraint(Predicate<String> constraint);

  //method declaration
  void removeConstraints();

  //method declaration
  IAbstractValueContent setDataType(DataType dataType);
}
