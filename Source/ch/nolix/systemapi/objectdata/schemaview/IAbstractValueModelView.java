package ch.nolix.systemapi.objectdata.schemaview;

public interface IAbstractValueModelView<V, T> extends IContentModelView<T> {

  Class<V> getValueType();
}
