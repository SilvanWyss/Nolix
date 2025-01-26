package ch.nolix.systemapi.objectdataapi.schemaviewapi;

public interface IAbstractValueModelView<V> extends IContentModelView {

  Class<V> getValueType();
}
