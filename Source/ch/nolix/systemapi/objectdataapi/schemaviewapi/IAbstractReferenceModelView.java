package ch.nolix.systemapi.objectdataapi.schemaviewapi;

public interface IAbstractReferenceModelView<T> extends IContentModelView<T> {

  T getStoredReferencedTable();
}
