//package declaration
package ch.nolix.core.programstructure.builder.andargumentcaptor;

//own imports
import ch.nolix.core.programstructure.builder.main.ArgumentCaptor;

//class
public class AndSchemaCaptor<S, N> extends ArgumentCaptor<S, N> {

  //constructor
  public AndSchemaCaptor() {
  }

  //constructor
  public AndSchemaCaptor(final N nextArgumentCaptor) {
    super(nextArgumentCaptor);
  }

  //method
  public final N andSchema(final S schema) {
    return setArgumentAndGetNext(schema);
  }

  //method
  public final S getStoredSchema() {
    return getStoredArgument();
  }
}
