package ch.nolix.systemapi.objectdata.schemaview;

public interface IBaseReferenceModelView<T> extends IContentModelView<T> {

  T getStoredReferencedTable();
}
