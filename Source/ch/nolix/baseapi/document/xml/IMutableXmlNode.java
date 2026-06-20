/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.xml;

import ch.nolix.baseapi.attribute.fluentmutableoptionalattribute.FluentMutableOptionalValueHolder;

/**
 * @author Silvan Wyss
 */
public interface IMutableXmlNode
extends FluentMutableOptionalValueHolder<IMutableXmlNode, String>, IXmlNode<IMutableXmlNode> {
  IMutableXmlNode addAttribute(IXmlAttribute attribute);

  IMutableXmlNode addAttributes(Iterable<IXmlAttribute> attributes);

  IMutableXmlNode addAttributes(IXmlAttribute... attributes);

  IMutableXmlNode addAttributeWithNameAndValue(String name, String value);

  IMutableXmlNode addChildNode(IMutableXmlNode childNode);

  IMutableXmlNode addChildNodes(IMutableXmlNode... childNodes);

  IMutableXmlNode addChildNodes(Iterable<IMutableXmlNode> childNodes);
}
