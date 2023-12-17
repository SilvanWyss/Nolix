//package declaration
package ch.nolix.core.programstructure.builder.usingargumentcapturer;

//own imports
import ch.nolix.core.programstructure.builder.main.ArgumentCapturer;

//class
public class AndSchemaCapturer<S, N> extends ArgumentCapturer<S, N> {

  //constructor
  public AndSchemaCapturer() {
  }

  //constructor
  public AndSchemaCapturer(final N nextArgumentCapturer) {
    super(nextArgumentCapturer);
  }

  //method
  public final S getStoredSchema() {
    return getStoredArgument();
  }

  //method
  public final N andSchema(final S schema) {
    return setArgumentAndGetNext(schema);
  }
}