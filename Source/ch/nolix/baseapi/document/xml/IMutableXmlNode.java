/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.xml;

/**
 * @author Silvan Wyss
 */
public interface IMutableXmlNode extends IXmlNode<IMutableXmlNode> {
  /**
   * Adds the given attribute to the current {@link IMutableXmlNode}.
   * 
   * @param attribute
   * @return the current {@link IMutableXmlNode}
   */
  IMutableXmlNode addAttribute(IXmlAttribute attribute);

  /**
   * Adds the given attributes to the current {@link IMutableXmlNode}.
   * 
   * @param attributes
   * @return the current {@link IMutableXmlNode}
   */
  IMutableXmlNode addAttributes(Iterable<IXmlAttribute> attributes);

  /**
   * Adds the given attributes to the current {@link IMutableXmlNode}.
   * 
   * @param attributes
   * @return the current {@link IMutableXmlNode}
   */
  IMutableXmlNode addAttributes(IXmlAttribute... attributes);

  /**
   * Adds a new attribute with the given name and value to the current
   * {@link IMutableXmlNode}.
   * 
   * @param name
   * @param value
   * @return the current {@link IMutableXmlNode}
   */
  IMutableXmlNode addAttributeWithNameAndValue(String name, String value);

  /**
   * Adds the given childNode to the current {@link IMutableXmlNode}.
   * 
   * @param childNode
   * @return the current {@link IMutableXmlNode}
   */
  IMutableXmlNode addChildNode(IMutableXmlNode childNode);

  /**
   * Adds the given childNodes to the current {@link IMutableXmlNode}.
   * 
   * @param childNodes
   * @return the current {@link IMutableXmlNode}
   */
  IMutableXmlNode addChildNodes(IMutableXmlNode... childNodes);

  /**
   * Adds the given childNodes to the current {@link IMutableXmlNode}.
   * 
   * @param childNodes
   * @return the current {@link IMutableXmlNode}
   */
  IMutableXmlNode addChildNodes(Iterable<IMutableXmlNode> childNodes);

  /**
   * Removes the value of the current {@link IMutableXmlNode}.
   */
  void removeValue();

  /**
   * Sets the given value to the current {@link IMutableXmlNode}.
   * 
   * @param value
   * @return the current {@link IMutableXmlNode}
   */
  IMutableXmlNode setValue(String value);
}
