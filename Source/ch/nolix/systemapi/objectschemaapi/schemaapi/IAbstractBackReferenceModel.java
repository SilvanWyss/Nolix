package ch.nolix.systemapi.objectschemaapi.schemaapi;

public interface IAbstractBackReferenceModel extends IContentModel {

  IColumn getBackReferencedColumn();
}
