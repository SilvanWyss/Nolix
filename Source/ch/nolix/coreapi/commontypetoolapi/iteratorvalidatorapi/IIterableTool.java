//package declaration
package ch.nolix.coreapi.commontypetoolapi.iteratorvalidatorapi;

//interface
public interface IIterableTool {

  //method declaration
  boolean containsAny(Iterable<?> iterable);

  //method declaration
  boolean containsEqualing(Iterable<?> iterable, Object object);

  //method declaration
  int getCount(Iterable<?> iterable);
}
