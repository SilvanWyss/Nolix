//package declaration
package ch.nolix.core.programstructure.builder.andargumentcaptor;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.builder.main.ArgumentCaptor;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public class AndPasswordCaptor<N> extends ArgumentCaptor<String, N> {

  //constructor
  public AndPasswordCaptor() {
  }

  //constructor
  public AndPasswordCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  //method
  public final N andPassword(final String password) {

    GlobalValidator.assertThat(password).thatIsNamed(LowerCaseVariableCatalogue.PASSWORD).isNotNull();

    return setArgumentAndGetNext(password);
  }

  //method
  public final String getPassword() {
    return getStoredArgument();
  }
}
