package ch.nolix.systemapi.objectschemaapi.schemaapi;

public interface IAbstractReferenceModel extends IContentModel {

  ITable getReferencedTable();
}
