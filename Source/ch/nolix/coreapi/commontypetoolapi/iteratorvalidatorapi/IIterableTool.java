//package declaration
package ch.nolix.coreapi.commontypetoolapi.iteratorvalidatorapi;

//interface
public interface IIterableTool {

  //method declaration
  boolean containsAllObjects(Iterable<?> iterable, Object[] objects);

  //method declaration
  boolean containsAllObjects(Iterable<?> iterable, Object object, Object... objects);

  //method declaration
  boolean containsAny(Iterable<?> iterable);

  //method declaration
  boolean containsAnyThatEqualsTheObject(Iterable<?> iterable, Object object);

  //method
  boolean containsObject(Iterable<?> iterable, Object object);

  //method declaration
  int getCount(Iterable<?> iterable);
}
