package ch.nolix.coreapi.commontypetoolapi.iteratorvalidatorapi;

public interface IIterableTool {

  boolean containsAny(Iterable<?> iterable);

  int getCount(Iterable<?> iterable);

  boolean isEmpty(Iterable<?> iterable);
}
