package ch.nolix.systemapi.rawschemaapi.schemadtoapi;

public interface IColumnDto {

  String getId();

  String getName();

  IContentModelDto getParameterizedFieldType();
}
