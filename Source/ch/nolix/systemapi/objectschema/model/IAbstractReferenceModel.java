package ch.nolix.systemapi.objectschema.model;

public interface IAbstractReferenceModel extends IContentModel {

  ITable getReferencedTable();
}
