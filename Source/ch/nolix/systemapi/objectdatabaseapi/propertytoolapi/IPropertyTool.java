//package declaration
package ch.nolix.systemapi.objectdatabaseapi.propertytoolapi;

import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectTool;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;

//interface
public interface IPropertyTool extends IDatabaseObjectTool {

  //method
  boolean belongsToEntity(IProperty property);

  //method declaration
  boolean belongsToLoadedEntity(IProperty property);

  //method declaration
  Class<?> getDataType(IProperty property);

  //method declaration
  boolean isForMultiContent(IProperty property);

  //method declaration
  boolean isForSingleContent(IProperty property);

  //method declaration
  boolean isMandatoryAndEmptyBoth(IProperty property);

  //method declaration
  boolean isSetForCaseIsNewOrEditedAndMandatory(IProperty property);
}
