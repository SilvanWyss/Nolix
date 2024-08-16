//package declaration
package ch.nolix.coreapi.commontypetoolapi.iteratorvalidatorapi;

//interface
public interface IIterableTool {

  //method declaration
  boolean containsAny(Iterable<?> iterable);

  //method declaration
  int getCount(Iterable<?> iterable);

  //method declaration
  boolean isEmpty(Iterable<?> iterable);
}
