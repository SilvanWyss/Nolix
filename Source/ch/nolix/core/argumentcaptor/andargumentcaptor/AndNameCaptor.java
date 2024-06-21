package ch.nolix.core.argumentcaptor.andargumentcaptor;

//own imports
import ch.nolix.core.argumentcaptor.base.ArgumentCaptor;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public class AndNameCaptor<N> extends ArgumentCaptor<String, N> {

  //constructor
  public AndNameCaptor() {
  }

  //constructor
  public AndNameCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  //method
  public final String getName() {
    return getStoredArgument();
  }

  //method
  public final N andName(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();

    return setArgumentAndGetNext(name);
  }
}
