package ch.nolix.systemapi.objectschema.model;

public interface IBaseBackReferenceModel extends IContentModel {

  IColumn getBackReferencedColumn();
}
