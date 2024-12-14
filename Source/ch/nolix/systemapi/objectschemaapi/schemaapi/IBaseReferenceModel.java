package ch.nolix.systemapi.objectschemaapi.schemaapi;

public interface IBaseReferenceModel extends IContentModel {

  ITable getReferencedTable();
}
