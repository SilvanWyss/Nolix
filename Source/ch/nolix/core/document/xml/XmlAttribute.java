package ch.nolix.core.document.xml;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.xmlapi.IXmlAttribute;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public final class XmlAttribute implements IXmlAttribute {

  private final String name;

  private final String value;

  public XmlAttribute(final String name) {
    this(name, StringCatalogue.EMPTY_STRING);
  }

  public XmlAttribute(final String name, final String value) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();
    GlobalValidator.assertThat(value).thatIsNamed(LowerCaseVariableCatalogue.VALUE).isNotNull();

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
