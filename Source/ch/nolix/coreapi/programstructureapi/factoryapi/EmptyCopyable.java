//package declaration
package ch.nolix.coreapi.programstructureapi.factoryapi;

//interface
public interface EmptyCopyable<EC extends EmptyCopyable<EC>> {

  //method declaration
  EC getEmptyCopy();
}
