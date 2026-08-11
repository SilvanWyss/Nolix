/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.xml;

/**
 * @author Silvan Wyss
 */
public interface IMutableXmlNode extends IXmlNode<IMutableXmlNode> {
  IMutableXmlNode addAttribute(IXmlAttribute attribute);

  IMutableXmlNode addAttributes(Iterable<IXmlAttribute> attributes);

  IMutableXmlNode addAttributes(IXmlAttribute... attributes);

  IMutableXmlNode addAttributeWithNameAndValue(String name, String value);

  IMutableXmlNode addChildNode(IMutableXmlNode childNode);

  IMutableXmlNode addChildNodes(IMutableXmlNode... childNodes);

  IMutableXmlNode addChildNodes(Iterable<IMutableXmlNode> childNodes);

  void removeValue();

  IMutableXmlNode setValue(String value);
}
