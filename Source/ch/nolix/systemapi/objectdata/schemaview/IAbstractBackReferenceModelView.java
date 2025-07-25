package ch.nolix.systemapi.objectdata.schemaview;

public interface IAbstractBackReferenceModelView<C extends IColumnView<T>, T> extends IContentModelView<T> {

  C getBackReferencedColumn();
}
