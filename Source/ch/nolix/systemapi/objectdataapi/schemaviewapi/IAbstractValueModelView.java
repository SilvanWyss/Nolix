package ch.nolix.systemapi.objectdataapi.schemaviewapi;

public interface IAbstractValueModelView<V, T> extends IContentModelView<T> {

  Class<V> getValueType();
}
