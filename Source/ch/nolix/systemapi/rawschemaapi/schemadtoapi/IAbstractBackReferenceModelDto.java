package ch.nolix.systemapi.rawschemaapi.schemadtoapi;

public interface IAbstractBackReferenceModelDto extends IContentModelDto {

  String getBackReferencedColumnId();
}
