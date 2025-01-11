package ch.nolix.system.element.multistateconfiguration;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;

final class State<S extends Enum<S>> implements INameHolder {

  private static final String NAME = PascalCaseVariableCatalog.STATE;

  private final String qualifyingPrefix;

  private final int index;

  private final S enumValue;

  private State(final String prefix, final int index, final S enumValue) {

    GlobalValidator.assertThat(prefix).thatIsNamed(LowerCaseVariableCatalog.PREFIX).isNotBlank();
    GlobalValidator.assertThat(index).thatIsNamed(LowerCaseVariableCatalog.INDEX).isNotNegative();
    GlobalValidator.assertThat(enumValue).thatIsNamed("enum value").isNotNull();

    this.qualifyingPrefix = prefix;
    this.index = index;
    this.enumValue = enumValue;
  }

  public static <S2 extends Enum<S2>> State<S2> withQualifyingPrefixAndIndexAndEnumValue(
    final String qualifyingPrefix,
    final int index,
    final S2 enumValue) {
    return new State<>(qualifyingPrefix, index, enumValue);
  }

  public S getEnumValue() {
    return enumValue;
  }

  public int getIndex() {
    return index;
  }

  @Override
  public String getName() {
    return NAME;
  }

  public String getQualifiedName() {
    return (getQualifyingPrefix() + getName());
  }

  public String getQualifyingPrefix() {
    return qualifyingPrefix;
  }

  public boolean hasEnumValue(final S enumValue) {
    return (getEnumValue() == enumValue);
  }
}
