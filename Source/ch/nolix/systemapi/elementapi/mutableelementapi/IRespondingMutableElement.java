//package declaration
package ch.nolix.systemapi.elementapi.mutableelementapi;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//interface
/**
 * @author Silvan Wyss
 * @version 2021-04-01
 * @param <RME> is the type of a {@link IRespondingMutableElement}.
 */
public interface IRespondingMutableElement<RME extends IRespondingMutableElement<RME>> extends IMutableElement {

  //method declaration
  /**
   * Adds or changes the given attribute to the current
   * {@link IRespondingMutableElement} if the given attributes matches.
   * 
   * @param attribute
   * @return true if the given attribute was added or changed to the current
   *         {@link IRespondingMutableElement}.
   */
  boolean addedOrChangedAttribute(INode<?> attribute);
}
