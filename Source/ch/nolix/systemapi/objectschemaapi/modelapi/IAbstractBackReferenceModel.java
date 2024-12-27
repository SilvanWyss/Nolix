package ch.nolix.systemapi.objectschemaapi.modelapi;

public interface IAbstractBackReferenceModel extends IContentModel {

  IColumn getBackReferencedColumn();
}
