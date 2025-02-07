package ch.nolix.coreapi.commontypetoolapi.iteratorvalidatorapi;

public interface IIterableExaminer {

  boolean containsAny(Iterable<?> iterable);

  boolean isEmpty(Iterable<?> iterable);
}
