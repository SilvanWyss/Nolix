/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.multistateconfiguration;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <S> the type of the {@link Enum} representation of a {@link State}.
 */
public final class State<S extends Enum<S>> {
  private final String qualifyingPrefix;

  private final int index;

  private final S memberEnumValue;

  private State(final String prefix, final int index, final S enumValue) {
    Validator.assertThat(prefix).thatIsNamed(LowerCaseVariableNameCatalog.PREFIX).isNotBlank();
    Validator.assertThat(index).thatIsNamed(LowerCaseVariableNameCatalog.INDEX).isNotNegative();
    Validator.assertThat(enumValue).thatIsNamed("enum value").isNotNull();

    this.qualifyingPrefix = prefix;
    this.index = index;
    memberEnumValue = enumValue;
  }

  /**
   * @param qualifyingPrefix
   * @param index
   * @param enumValue
   * @param <S2>             the type of the given enumValue
   * @return a new {@link State} with the given qualifyingPrefix, index and
   *         enumValue
   */
  public static <S2 extends Enum<S2>> State<S2> withQualifyingPrefixAndIndexAndEnumValue(
    final String qualifyingPrefix,
    final int index,
    final S2 enumValue) {
    return new State<>(qualifyingPrefix, index, enumValue);
  }

  /**
   * @return the enum value of the current {@link State}
   */
  public S getEnumValue() {
    return memberEnumValue;
  }

  /**
   * @return the index of the current {@link State}
   */
  public int getIndex() {
    return index;
  }

  /**
   * @return the qualifying prefix of the current {@link State}
   */
  public String getQualifyingPrefix() {
    return qualifyingPrefix;
  }

  /**
   * @param enumValue
   * @return true if the current {@link State} has the given enumValue, false
   *         otherwise
   */
  public boolean hasEnumValue(final S enumValue) {
    return (getEnumValue() == enumValue);
  }
}
