package ch.nolix.coreapi.commontypetoolapi.iterabletoolapi;

public interface IIterableExaminer {

  boolean containsAny(Iterable<?> iterable);

  boolean isEmpty(Iterable<?> iterable);
}
