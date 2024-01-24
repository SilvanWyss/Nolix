//package declaration
package ch.nolix.systemapi.objectschemaapi.schematoolapi;

import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectTool;
import ch.nolix.systemapi.entitypropertyapi.mainapi.BasePropertyType;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//interface
public interface IColumnTool extends IDatabaseObjectTool {

  //method declaration
  void assertBelongsToTable(IColumn column);

  //method declaration
  void assertDoesNotBelongToTable(IColumn column);

  //method declaration
  void assertIsABackReferenceColumn(IColumn column);

  //method declaration
  void assertIsAReferenceColumn(IColumn column);

  //method declaration
  boolean belongsToDatabase(IColumn column);

  //method declaration
  BasePropertyType getBasePropertyType(IColumn column);

  //method declaration
  IDatabase getParentDatabase(IColumn column);

  //method declaration
  PropertyType getPropertyType(IColumn column);

  //method declaration
  boolean isABackReferenceColumn(IColumn column);

  //method declaration
  boolean isAReferenceColumn(IColumn column);

  //method declaration
  boolean isAValidBackReferenceColumn(IColumn column);

  //method declaration
  boolean isAValueColumn(IColumn column);

  //method declaration
  boolean referencesBackGivenColumn(IColumn column, IColumn probableBackReferencedColumn);

  //method declaration
  boolean referencesGivenTable(IColumn column, ITable table);
}