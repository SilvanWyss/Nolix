/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.css.cssmodel;

import ch.nolix.baseapi.css.cssmodel.ICssProperty;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 */
public final class CssProperty implements ICssProperty {
  private final String name;

  private final String value;

  //For a better performance, this implementation does not use all available comfort methods.
  private CssProperty(final String name, final String value) {
    if (name == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableNameCatalog.NAME);
    }

    if (value == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableNameCatalog.VALUE);
    }

    this.name = name;
    this.value = value;
  }

  public static CssProperty fromCssProperty(final ICssProperty cssProperty) {
    if (cssProperty instanceof final CssProperty localCssProperty) {
      return localCssProperty;
    }

    return withNameAndValue(cssProperty.getName(), cssProperty.getValue());
  }

  public static CssProperty withNameAndValue(final String name, final double value) {
    return new CssProperty(name, String.valueOf(value));
  }

  public static CssProperty withNameAndValue(final String name, final Enum<?> value) {
    return new CssProperty(name, value.toString());
  }

  public static CssProperty withNameAndValue(final String name, final int value) {
    return new CssProperty(name, String.valueOf(value));
  }

  public static CssProperty withNameAndValue(final String name, final String value) {
    return new CssProperty(name, value);
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
    return (getName() + ": " + getValue() + ";");
  }
}
