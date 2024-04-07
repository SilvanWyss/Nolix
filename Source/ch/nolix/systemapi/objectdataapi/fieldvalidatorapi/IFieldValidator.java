//package declaration
package ch.nolix.systemapi.objectdataapi.fieldvalidatorapi;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IField;

//interface
public interface IFieldValidator {

  //method declaration
  void assertBelongsToEntity(IField field);

  //method declaration
  void assertDoesNotBelongToEntity(IField field);

  //method declaration
  void assertIsNotEmpty(IField field);

  //method declaration
  void assertIsNotMandatoryAndEmptyBoth(IField field);

  //method declaration
  void assertKnowsParentColumn(IField field);
}
