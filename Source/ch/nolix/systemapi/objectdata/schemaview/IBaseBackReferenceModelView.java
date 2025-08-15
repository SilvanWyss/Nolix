package ch.nolix.systemapi.objectdata.schemaview;

public interface IBaseBackReferenceModelView<C extends IColumnView<T>, T> extends IContentModelView<T> {

  C getBackReferencedColumn();
}
