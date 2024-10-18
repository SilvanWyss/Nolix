package ch.nolix.systemapi.rawschemaapi.schemadtoapi;

public interface IBaseParameterizedReferenceTypeDto extends IParameterizedFieldTypeDto {

  String getReferencedTableId();
}
