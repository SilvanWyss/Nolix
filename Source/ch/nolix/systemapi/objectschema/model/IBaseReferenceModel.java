package ch.nolix.systemapi.objectschema.model;

public interface IBaseReferenceModel extends IContentModel {

  ITable getReferencedTable();
}
