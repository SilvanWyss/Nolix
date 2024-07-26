//package declaration
package ch.nolix.coreapi.programstructureapi.builderapi;

//own imports
import ch.nolix.coreapi.stateapi.staterequestapi.MaterializationRequestable;

//interface
public interface Materiazable<M> extends MaterializationRequestable {

  //method declaration
  M toMaterialized();
}
