package ch.nolix.systemapi.objectschemaapi.schemaapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.rawschemaapi.schemadto.IContentModelDto;

public interface IContentModel {

  ContentType getContentType();

  DataType getDataType();

  boolean referencesBackColumn(IColumn column);

  boolean referencesTable(ITable table);

  IContentModelDto toDto();
}
