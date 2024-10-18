package ch.nolix.systemapi.rawdataapi.schemainfoapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public interface IColumnInfo {

  DataType getColumnDataType();

  ContentType getColumnFieldType();

  String getColumnId();

  int getColumnIndexOnEntityNode();

  String getColumnName();
}
