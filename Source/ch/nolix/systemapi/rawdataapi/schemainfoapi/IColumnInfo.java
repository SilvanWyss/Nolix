package ch.nolix.systemapi.rawdataapi.schemainfoapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public interface IColumnInfo {

  ContentType getColumnContentType();

  DataType getColumnDataType();

  String getColumnId();

  int getColumnIndexOnEntityNode();

  String getColumnName();
}
