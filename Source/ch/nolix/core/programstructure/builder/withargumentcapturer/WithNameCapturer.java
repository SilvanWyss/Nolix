//package declaration
package ch.nolix.core.programstructure.builder.withargumentcapturer;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.builder.main.ArgumentCapturer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public class WithNameCapturer<N> extends ArgumentCapturer<String, N> {

  //constructor
  public WithNameCapturer() {
  }

  //constructor
  public WithNameCapturer(final N nextArgumentCapturer) {
    super(nextArgumentCapturer);
  }

  //method
  public final String getName() {
    return getStoredArgument();
  }

  //method
  public final N withName(final String name) {

    GlobalValidator.assertThat(name).thatIsNamed(LowerCaseVariableCatalogue.NAME).isNotBlank();

    return setArgumentAndGetNext(name);
  }
}
