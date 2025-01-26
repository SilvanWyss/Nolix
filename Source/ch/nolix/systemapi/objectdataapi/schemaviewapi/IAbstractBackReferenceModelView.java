package ch.nolix.systemapi.objectdataapi.schemaviewapi;

public interface IAbstractBackReferenceModelView<C extends IColumnView<T>, T> extends IContentModelView<T> {

  C getBackReferencedColumn();
}
