package ch.nolix.systemapi.objectdataapi.schemaviewapi;

public interface IAbstractBackReferenceModelView<C extends IColumnView> extends IContentModelView {

  C getBackReferencedColumn();
}
