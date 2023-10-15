//package declaration
package ch.nolix.techapi.relationaldocapi.baseapi;

//own imports
import ch.nolix.coreapi.datamodelapi.entityrequestapi.AbstractnessRequestable;

//interface
public interface Abstractable<A extends Abstractable<A>> extends AbstractnessRequestable {

  // method declaration
  A setAsAbstract();

  // method declaration
  A setAsConcrete();
}
