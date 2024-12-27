package ch.nolix.systemapi.objectdataapi.modelapi;

public interface IAbstractBackReferenceModel<C extends IColumn> extends IContentModel {

  C getBackReferencedColumn();
}
