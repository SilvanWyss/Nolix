//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.coreapi.datamodelapi.constraintapi.IConstraint;
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;

//interface
public interface IAbstractValueContent extends IValueContent {

  // method declaration
  IAbstractValueContent addConstraint(IConstraint<String> constraint);

  // method declaration
  void removeConstraint(IConstraint<String> constraint);

  // method declaration
  void removeConstraints();

  // method declaration
  IAbstractValueContent setDataType(DataType dataType);
}
