//package declaration
package ch.nolix.systemapi.rawdatabaseapi.schemainfoapi;

//own imports
import ch.nolix.systemapi.entitypropertyapi.datatypeapi.DataType;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;

//interface
public interface IColumnInfo {

  //method declaration
  DataType getColumnDataType();

  //method declaration
  String getColumnId();

  //method declaration
  String getColumnName();

  //method declaration
  PropertyType getColumnPropertyType();

  //method declaration
  int getColumnIndexOnEntityNode();
}
