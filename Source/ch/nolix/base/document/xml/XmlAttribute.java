/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.document.xml;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.document.xml.IXmlAttribute;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 */
public final class XmlAttribute implements IXmlAttribute {
  private final String name;

  private final String value;

  private XmlAttribute(final String name) {
    Validator.assertThat(name).thatIsNamed(LowerCaseVariableNameCatalog.NAME).isNotBlank();

    this.name = name;
    this.value = StringCatalog.EMPTY_STRING;
  }

  private XmlAttribute(final String name, final String value) {
    Validator.assertThat(name).thatIsNamed(LowerCaseVariableNameCatalog.NAME).isNotBlank();
    Validator.assertThat(value).thatIsNamed(LowerCaseVariableNameCatalog.VALUE).isNotNull();

    this.name = name;
    this.value = value;
  }

  public static XmlAttribute withName(final String name) {
    return new XmlAttribute(name);
  }

  public static XmlAttribute withNameAndValue(final String name, final String value) {
    return new XmlAttribute(name, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getValue() {
    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return getName() + "='" + getValue() + "'";
  }
}
