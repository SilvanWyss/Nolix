package ch.nolix.systemapi.objectdata.schemaview;

public interface IBaseValueModelView<V, T> extends IContentModelView<T> {
  Class<V> getValueType();
}
