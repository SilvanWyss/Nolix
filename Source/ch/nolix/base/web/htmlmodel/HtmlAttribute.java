/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.web.htmlmodel;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.web.htmlmodel.IHtmlAttribute;

/**
 * @author Silvan Wyss
 */
public final class HtmlAttribute implements IHtmlAttribute {
  private final String name;

  private final String value;

  // For a better performance, this implementation does not use all available comfort methods.
  private HtmlAttribute(final String name, final String value) {
    if (name == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableNameCatalog.KEY);
    }

    if (value == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableNameCatalog.VALUE);
    }

    this.name = name;
    this.value = value;
  }

  /**
   * @param htmlAttribute
   * @return a new {@link HtmlAttribute} from the given htmlAttribute
   */
  public static HtmlAttribute fromHtmlAttribute(final IHtmlAttribute htmlAttribute) {
    if (htmlAttribute instanceof final HtmlAttribute concreteHtmlAttribute) {
      return concreteHtmlAttribute;
    }

    return withNameAndValue(htmlAttribute.getName(), htmlAttribute.getValue());
  }

  /**
   * @param name
   * @param value
   * @return a new {@link HtmlAttribute} with the given name and value
   */
  public static HtmlAttribute withNameAndValue(final String name, final int value) {
    return withNameAndValue(name, String.valueOf(value));
  }

  /**
   * @param name
   * @param value
   * @return a new {@link HtmlAttribute} with the given name and value
   */
  public static HtmlAttribute withNameAndValue(final String name, final String value) {
    return new HtmlAttribute(name, value);
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
    return (getName() + "=\"" + getValue() + "\"");
  }
}
