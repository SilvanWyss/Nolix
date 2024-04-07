//package declaration
package ch.nolix.systemapi.objectdataapi.propertyvalidatorapi;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IField;

//interface
public interface IPropertyValidator {

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
