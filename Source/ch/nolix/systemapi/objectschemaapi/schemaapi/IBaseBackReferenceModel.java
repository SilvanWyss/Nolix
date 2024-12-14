package ch.nolix.systemapi.objectschemaapi.schemaapi;

public interface IBaseBackReferenceModel extends IContentModel {

  IColumn getBackReferencedColumn();
}
