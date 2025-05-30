package ch.nolix.coreapi.commontypetoolapi.iterabletoolapi;

public interface IIterableTool {

  int getCount(Iterable<?> iterable);

  <E> E getStoredAtOneBasedIndex(final Iterable<E> iterable, int oneBasedIndex);
}
