package ch.nolix.systemapi.objectdata.schemaview;

public interface IAbstractReferenceModelView<T> extends IContentModelView<T> {

  T getStoredReferencedTable();
}
