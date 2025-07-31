package ch.nolix.systemapi.element.base;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.document.xml.IXmlNode;

/**
 * @author Silvan Wyss
 * @version 2016-01-01
 */
public interface IElement {

  /**
   * @return the attributes of the current {@link IElement}.
   */
  IContainer<INode<?>> getAttributes();

  /**
   * @return the specification of the current {@link IElement}.
   */
  INode<?> getSpecification();

  /**
   * @return a formated {@link String} representation of the current
   *         {@link IElement}.
   */
  default String toFormatedString() {
    return getSpecification().toFormattedString();
  }

  /**
   * @return a XML representation of the current {@link IElement}.
   */
  default IXmlNode<?> toXml() {
    return getSpecification().toXml();
  }
}
