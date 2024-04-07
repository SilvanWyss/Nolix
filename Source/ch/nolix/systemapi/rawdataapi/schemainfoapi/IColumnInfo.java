//package declaration
package ch.nolix.systemapi.rawdataapi.schemainfoapi;

import ch.nolix.systemapi.fieldapi.datatypeapi.DataType;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;

//interface
public interface IColumnInfo {

  //method declaration
  DataType getColumnDataType();

  //method declaration
  String getColumnId();

  //method declaration
  String getColumnName();

  //method declaration
  FieldType getColumnPropertyType();

  //method declaration
  int getColumnIndexOnEntityNode();
}
