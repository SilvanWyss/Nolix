package ch.nolix.coreapi.commontypetool.iterabletool;

public interface IIterableExaminer {

  boolean containsAny(Iterable<?> iterable);

  boolean isEmpty(Iterable<?> iterable);
}
