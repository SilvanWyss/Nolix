//package declaration
package ch.nolix.core.programstructure.builder.withargumentcapturer;

import ch.nolix.core.programstructure.builder.main.ArgumentCapturer;

//class
public class WithNameCapturer<N> extends ArgumentCapturer<String, N> {

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
    return setArgumentAndGetNext(name);
  }
}
