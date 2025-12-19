package ch.nolix.coreapi.commontypetool.iterabletool;

/**
 * @author Silvan Wyss
 */
public interface IIterableExaminer {
  boolean containsAny(Iterable<?> iterable);

  boolean isEmpty(Iterable<?> iterable);
}
