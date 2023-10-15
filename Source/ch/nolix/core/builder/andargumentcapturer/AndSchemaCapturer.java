//package declaration
package ch.nolix.core.builder.andargumentcapturer;

//own imports
import ch.nolix.core.builder.main.ArgumentCapturer;

//class
public class AndSchemaCapturer<S, N>
    extends ArgumentCapturer<S, N> {

  // constructor
  public AndSchemaCapturer(final N nextArgumentCapturer) {
    super(nextArgumentCapturer);
  }

  // method
  public final N andSchema(final S schema) {
    return setArgumentAndGetNext(schema);
  }

  // method
  public final S getStoredSchema() {
    return getStoredArgument();
  }
}
