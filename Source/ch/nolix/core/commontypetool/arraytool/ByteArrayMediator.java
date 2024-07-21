//package declaration
package ch.nolix.core.commontypetool.arraytool;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.commontypetoolapi.arraytoolapi.IByteArrayMediator;
import ch.nolix.coreapi.commontypetoolapi.arraytoolapi.IByteArrayMediatorWithStartIndex;

//class
public final class ByteArrayMediator implements IByteArrayMediator {

  //attribute
  private final byte[] byteArray;

  //constructor
  private ByteArrayMediator(final byte[] byteArray) {

    GlobalValidator.assertThat(byteArray).thatIsNamed("byte array").isNotNull();

    this.byteArray = byteArray; //NOSONAR: A ByteArrayMediator operates on the original instance.
  }

  //static method
  public static ByteArrayMediator forByteArray(final byte[] byteArray) {
    return new ByteArrayMediator(byteArray);
  }

  //method
  @Override
  public IByteArrayMediatorWithStartIndex fromIndex(final int index) {
    return ByteArrayMediatorWithStartIndex.forByteArrayAndStartIndex(byteArray, index);
  }
}
