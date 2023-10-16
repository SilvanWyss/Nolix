//package declaration
package ch.nolix.coreapi.datamodelapi.constraintapi;

//interface
public interface IConstraint<E> {

  //method declaration
  boolean acceptsEntry(E entry);
}
