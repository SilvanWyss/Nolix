/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.document.xml;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.stringtool.StringCatalog;
import ch.nolix.coreapi.document.xml.IXmlAttribute;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 */
public final class XmlAttribute implements IXmlAttribute {
  private final String name;

  private final String value;

  public XmlAttribute(final String name) {
    this(name, StringCatalog.EMPTY_STRING);
  }

  public XmlAttribute(final String name, final String value) {
    Validator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();
    Validator.assertThat(value).thatIsNamed(LowerCaseVariableCatalog.VALUE).isNotNull();

    this.name = name;
    this.value = value;
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
    return (getName() + "='" + getValue() + "'");
  }
}
