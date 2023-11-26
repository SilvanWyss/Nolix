//package declaration
package ch.nolix.systemapi.elementapi.multistateconfigurationapi;

//own imports
import ch.nolix.systemapi.elementapi.mutableelementapi.IRespondingMutableElement;

//interface
public interface IMultiStateConfiguration<MSC extends IMultiStateConfiguration<MSC, S>, S extends Enum<S>>
extends IRespondingMutableElement<MSC> {

  //method declaration
  S getBaseState();
}
