//package declaration
package ch.nolix.core.builder.andargumentcapturer;

//own imports
import ch.nolix.core.builder.main.ArgumentCapturer;

//class
public class AndPasswordCapturer<N> extends ArgumentCapturer<String, N> {

  //constructor
  public AndPasswordCapturer() {
  }

  //constructor
  public AndPasswordCapturer(final N nextArgumentCapturer) {
    super(nextArgumentCapturer);
  }

  //method
  public final N andPassword(final String password) {
    return setArgumentAndGetNext(password);
  }

  //method
  public final String getPassword() {
    return getStoredArgument();
  }
}
