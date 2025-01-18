package ch.nolix.core.web.html;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlAttribute;

public final class HtmlAttribute implements IHtmlAttribute {

  private final String name;

  private final String value;

  //For a better performance, this implementation does not use all available comfort methods.
  private HtmlAttribute(final String name, final String value) {

    if (name == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.KEY);
    }

    if (value == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.VALUE);
    }

    this.name = name;
    this.value = value;
  }

  public static HtmlAttribute fromHtmlAttribute(final IHtmlAttribute htmlAttribute) {

    if (htmlAttribute instanceof final HtmlAttribute concreteHtmlAttribute) {
      return concreteHtmlAttribute;
    }

    return withNameAndValue(htmlAttribute.getName(), htmlAttribute.getValue());
  }

  public static HtmlAttribute withNameAndValue(final String name, final int value) {
    return withNameAndValue(name, String.valueOf(value));
  }

  public static HtmlAttribute withNameAndValue(final String name, final String value) {
    return new HtmlAttribute(name, value);
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
    return (getName() + "=\"" + getValue() + "\"");
  }
}
