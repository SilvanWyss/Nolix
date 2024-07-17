//package declaration
package ch.nolix.core.commontypetool.arraytool;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public final class ByteArrayMediatorWithStartIndex {

  //attribute
  private final byte[] byteArray;

  //attribute
  private int index;

  //constructor
  ByteArrayMediatorWithStartIndex(final byte[] byteArray, final int startIndex) {

    GlobalValidator.assertThat(byteArray).thatIsNamed("byte array").isNotNull();

    GlobalValidator
      .assertThat(startIndex)
      .thatIsNamed(LowerCaseVariableCatalogue.START_INDEX)
      .isBetween(0, byteArray.length);

    this.byteArray = byteArray; //NOSONAR: A ByteArrayMediatorWithStartIndex operates on the original instance.
    index = startIndex;
  }

  //method
  public NextIndexMediator write(final byte[] bytes) {

    for (var i = 0; i < bytes.length; i++) {
      byteArray[index + i] = bytes[i];
    }

    index += bytes.length;

    return new NextIndexMediator(index);
  }
}
