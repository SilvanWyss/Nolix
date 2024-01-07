//package declaration
package ch.nolix.coreapi.programstructureapi.builderapi;

//interface
public interface EmptyCopyable<EC extends EmptyCopyable<EC>> {

  //method declaration
  EC getEmptyCopy();
}
