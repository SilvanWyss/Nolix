//package declaration
package ch.nolix.core.programstructure.builder.usingargumentcapturer;

//own imports
import ch.nolix.core.programstructure.builder.main.ArgumentCapturer;

//class
public class UsingSchemaCapturer<S, N> extends ArgumentCapturer<S, N> {

  //constructor
  public UsingSchemaCapturer() {
  }

  //constructor
  public UsingSchemaCapturer(final N nextArgumentCapturer) {
    super(nextArgumentCapturer);
  }

  //method
  public final S getStoredSchema() {
    return getStoredArgument();
  }

  //method
  public final N usingSchema(final S schema) {
    return setArgumentAndGetNext(schema);
  }
}
