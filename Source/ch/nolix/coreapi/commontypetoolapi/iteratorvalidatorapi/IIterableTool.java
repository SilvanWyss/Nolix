package ch.nolix.coreapi.commontypetoolapi.iteratorvalidatorapi;

public interface IIterableTool {

  int getCount(Iterable<?> iterable);

  <E> E getStoredAtOneBasedIndex(final Iterable<E> iterable, int oneBasedIndex);
}
