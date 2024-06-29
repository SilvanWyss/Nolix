//package declaration
package ch.nolix.systemapi.elementapi.baseapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.documentapi.xmlapi.IXmlNode;
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * @author Silvan Wyss
 * @version 2016-01-01
 */
@AllowDefaultMethodsAsDesignPattern
public interface IElement {

  //method declaration
  /**
   * @return the attributes of the current {@link IElement}.
   */
  IContainer<INode<?>> getAttributes();

  //method declaration
  /**
   * @return the specification of the current {@link IElement}.
   */
  INode<?> getSpecification();

  //method
  /**
   * @return a formated {@link String} representation of the current
   *         {@link IElement}.
   */
  default String toFormatedString() {
    return getSpecification().toFormattedString();
  }

  //method
  /**
   * @return a XML representation of the current {@link IElement}.
   */
  default IXmlNode<?> toXml() {
    return getSpecification().toXml();
  }
}
