//package declaration
package ch.nolix.systemapi.objectdataapi.propertytoolapi;

//own imports
import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;

//interface
public interface IPropertyTool extends IDatabaseObjectTool {

  //method
  boolean belongsToEntity(IField field);

  //method declaration
  boolean belongsToLoadedEntity(IField field);

  //method declaration
  Class<?> getDataType(IField field);

  //method declaration
  boolean isForMultiContent(IField field);

  //method declaration
  boolean isForSingleContent(IField field);

  //method declaration
  boolean isMandatoryAndEmptyBoth(IField field);

  //method declaration
  boolean isSetForCaseIsNewOrEditedAndMandatory(IField field);
}
