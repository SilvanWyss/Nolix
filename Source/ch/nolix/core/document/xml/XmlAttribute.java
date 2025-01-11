package ch.nolix.core.document.xml;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.xmlapi.IXmlAttribute;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.StringCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public final class XmlAttribute implements IXmlAttribute {

  private final String name;

  private final String value;

  public XmlAttribute(final String name) {
    this(name, StringCatalog.EMPTY_STRING);
  }

  public XmlAttribute(final String name, final String value) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();
    GlobalValidator.assertThat(value).thatIsNamed(LowerCaseVariableCatalog.VALUE).isNotNull();

    this.name = name;
    this.value = value;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return (getName() + "='" + getValue() + "'");
  }
}
