/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.xml;

import ch.nolix.baseapi.attribute.fluentmutableoptionalattribute.IFluentMutableOptionalValueHolder;

/**
 * @author Silvan Wyss
 */
public interface IMutableXmlNode
extends IFluentMutableOptionalValueHolder<IMutableXmlNode, String>, IXmlNode<IMutableXmlNode> {
  IMutableXmlNode addAttribute(IXmlAttribute attribute);

  IMutableXmlNode addAttributes(IXmlAttribute attribute, IXmlAttribute... attributes);

  IMutableXmlNode addAttributeWithNameAndValue(String name, String value);

  IMutableXmlNode addChildNode(IMutableXmlNode childNode);

  IMutableXmlNode addChildNodes(IMutableXmlNode childNode, IMutableXmlNode... childNodes);
}
