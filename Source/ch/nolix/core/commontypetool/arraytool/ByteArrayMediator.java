package ch.nolix.core.commontypetool.arraytool;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.commontypetoolapi.arraytoolapi.IByteArrayMediator;
import ch.nolix.coreapi.commontypetoolapi.arraytoolapi.IByteArrayMediatorWithStartIndex;

public final class ByteArrayMediator implements IByteArrayMediator {

  private final byte[] byteArray;

  private ByteArrayMediator(final byte[] byteArray) {

    GlobalValidator.assertThat(byteArray).thatIsNamed("byte array").isNotNull();

    this.byteArray = byteArray; //NOSONAR: A ByteArrayMediator operates on the original instance.
  }

  public static ByteArrayMediator forByteArray(final byte[] byteArray) {
    return new ByteArrayMediator(byteArray);
  }

  @Override
  public IByteArrayMediatorWithStartIndex fromIndex(final int index) {
    return ByteArrayMediatorWithStartIndex.forByteArrayAndStartIndex(byteArray, index);
  }
}
