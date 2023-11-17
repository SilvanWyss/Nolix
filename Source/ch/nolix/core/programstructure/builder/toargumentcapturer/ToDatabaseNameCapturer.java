//package declaration
package ch.nolix.core.programstructure.builder.toargumentcapturer;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programstructure.builder.main.ArgumentCapturer;

//class
public class ToDatabaseNameCapturer<N> extends ArgumentCapturer<String, N> {

  //constructor
  public ToDatabaseNameCapturer() {
  }

  //constructor
  public ToDatabaseNameCapturer(final N nextArgumentCapturer) {
    super(nextArgumentCapturer);
  }

  //method
  public final String getDatabaseName() {
    return getStoredArgument();
  }

  //method
  public final N toDatabase(final String databaseName) {

    GlobalValidator.assertThat(databaseName).thatIsNamed(LowerCaseCatalogue.DATABASE_NAME).isNotBlank();

    return setArgumentAndGetNext(databaseName);
  }
}
