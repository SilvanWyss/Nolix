package ch.nolix.systemapi.rawdataapi.schemaviewapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public interface IColumnView {

  ContentType getColumnContentType();

  DataType getColumnDataType();

  String getColumnId();

  int getColumnIndexOnEntityNode();

  String getColumnName();
}
