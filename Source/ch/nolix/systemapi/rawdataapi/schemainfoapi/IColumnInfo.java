//package declaration
package ch.nolix.systemapi.rawdataapi.schemainfoapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;

//interface
public interface IColumnInfo {

  //method declaration
  DataType getColumnDataType();

  //method declaration
  FieldType getColumnFieldType();

  //method declaration
  String getColumnId();

  //method declaration
  int getColumnIndexOnEntityNode();

  //method declaration
  String getColumnName();
}
