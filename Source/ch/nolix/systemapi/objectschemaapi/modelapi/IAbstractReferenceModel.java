package ch.nolix.systemapi.objectschemaapi.modelapi;

public interface IAbstractReferenceModel extends IContentModel {

  ITable getReferencedTable();
}
