package ch.nolix.systemapi.rawschemaapi.schemadtoapi;

import ch.nolix.systemapi.rawschemaapi.schemadto.IContentModelDto;

public interface IColumnDto {

  String getId();

  String getName();

  IContentModelDto getParameterizedFieldType();
}
