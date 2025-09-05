package ch.nolix.coreapi.commontypetool.iterabletool;

public interface IIterableTool {
  int getCount(Iterable<?> iterable);

  <E> E getStoredAtOneBasedIndex(final Iterable<E> iterable, int oneBasedIndex);
}
