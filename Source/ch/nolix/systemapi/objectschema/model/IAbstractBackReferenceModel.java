package ch.nolix.systemapi.objectschema.model;

public interface IAbstractBackReferenceModel extends IContentModel {

  IColumn getBackReferencedColumn();
}
