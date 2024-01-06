//package declaration
package ch.nolix.core.commontypetool.commontypehelper;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
public final class ByteArrayMediator {

  //attribute
  private final byte[] byteArray;

  //constructor
  ByteArrayMediator(final byte[] byteArray) {

    GlobalValidator.assertThat(byteArray).thatIsNamed("byte array").isNotNull();

    this.byteArray = byteArray; //NOSONAR: A ByteArrayMediator operates on the original instance.
  }

  //method
  public ByteArrayMediatorWithStartIndex fromIndex(final int index) {
    return new ByteArrayMediatorWithStartIndex(byteArray, index);
  }
}
