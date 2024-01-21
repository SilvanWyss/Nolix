//package declaration
package ch.nolix.system.element.multistateconfiguration;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;

//class
final class State<S extends Enum<S>> implements INameHolder {

  //constant
  private static final String NAME = PascalCaseVariableCatalogue.STATE;

  //attribute
  private final String qualifyingPrefix;

  //attribute
  private final int index;

  //attribute
  private final S enumValue;

  //constructor
  private State(final String prefix, final int index, final S enumValue) {

    GlobalValidator.assertThat(prefix).thatIsNamed(LowerCaseVariableCatalogue.PREFIX).isNotBlank();
    GlobalValidator.assertThat(index).thatIsNamed(LowerCaseVariableCatalogue.INDEX).isNotNegative();
    GlobalValidator.assertThat(enumValue).thatIsNamed("enum value").isNotNull();

    this.qualifyingPrefix = prefix;
    this.index = index;
    this.enumValue = enumValue;
  }

  //static method
  public static <S2 extends Enum<S2>> State<S2> withQualifyingPrefixAndIndexAndEnumValue(
    final String qualifyingPrefix,
    final int index,
    final S2 enumValue) {
    return new State<>(qualifyingPrefix, index, enumValue);
  }

  //method
  public S getEnumValue() {
    return enumValue;
  }

  //method
  public int getIndex() {
    return index;
  }

  //method
  @Override
  public String getName() {
    return NAME;
  }

  //method
  public String getQualifiedName() {
    return (getQualifyingPrefix() + getName());
  }

  //method
  public String getQualifyingPrefix() {
    return qualifyingPrefix;
  }

  //method
  public boolean hasEnumValue(final S enumValue) {
    return (getEnumValue() == enumValue);
  }
}
