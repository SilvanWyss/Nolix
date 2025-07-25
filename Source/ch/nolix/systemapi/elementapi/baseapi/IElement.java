package ch.nolix.systemapi.elementapi.baseapi;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.documentapi.xmlapi.IXmlNode;
import ch.nolix.coreapi.structureapi.typemarkerapi.AllowDefaultMethodsAsDesignPattern;

/**
 * @author Silvan Wyss
 * @version 2016-01-01
 */
@AllowDefaultMethodsAsDesignPattern
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
