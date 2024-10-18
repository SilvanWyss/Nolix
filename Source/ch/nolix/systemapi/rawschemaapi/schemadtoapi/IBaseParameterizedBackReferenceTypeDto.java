package ch.nolix.systemapi.rawschemaapi.schemadtoapi;

public interface IBaseParameterizedBackReferenceTypeDto extends IParameterizedFieldTypeDto {

  String getBackReferencedColumnId();
}
