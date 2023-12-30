//package declaration
package ch.nolix.system.application.basewebapplication;

//Java imports
import java.util.Base64;
import java.util.Optional;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.system.application.basewebapplicationprotocol.CommandProtocol;

//class
final class BaseWebClientFileReader {

  //constant
  private static final int MAX_WAITING_TIME_FOR_FILE_FROM_COUNTERPART_IN_SECONDS = 60;

  //attribute
  private final BaseWebClient<?, ?> parentBackendWebClient;

  //attribute
  private boolean isWaitingForFileFromCounterpart;

  //optional attribute
  private byte[] latestOptionalFileFromCounterpart;

  //constructor
  private BaseWebClientFileReader(final BaseWebClient<?, ?> parentBackendWebClient) {

    GlobalValidator.assertThat(parentBackendWebClient).thatIsNamed("parent backend web client").isNotNull();

    this.parentBackendWebClient = parentBackendWebClient;
  }

  //static method
  public static BaseWebClientFileReader forBackendWebClient(
    final BaseWebClient<?, ?> backendWebClient) {
    return new BaseWebClientFileReader(backendWebClient);
  }

  //method
  public Optional<byte[]> readOptionalFileFromCounterpart() {

    assertIsNotWaitingForFileFromCounterpart();

    return readOptionalFileFromCounterpartWhenIsNotWaitingForFileFromCounterpart();
  }

  //method
  public void receiveOptionalFileFromCounterpart(final IChainedNode receiveOptionalFileCommand) {
    switch (receiveOptionalFileCommand.getChildNodeCount()) {
      case 0:
        receiveEmptyFileSelectionFromCounterpart();
        break;
      case 1:

        final var fileString = receiveOptionalFileCommand.getSingleChildNode().getHeader();

        //Info: The received fileString is a Base64 encoded String.
        final var file = Base64.getDecoder().decode(fileString.substring(fileString.indexOf(',') + 1));

        receiveFileFromCounterpart(file);
        break;
      default:
        throw InvalidArgumentException.forArgumentNameAndArgument(
          "receive optional file commoand",
          receiveOptionalFileCommand);
    }
  }

  //method
  private void assertIsNotWaitingForFileFromCounterpart() {
    if (isWaitingForFileFromCounterpart()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        this,
        "is already waiting for a file from the counterpart");
    }
  }

  //method
  private void assertIsWaitingForFileFromCounterpart() {
    if (!isWaitingForFileFromCounterpart()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not waiting for a file from counterpart");
    }
  }

  //method
  private boolean isWaitingForFileFromCounterpart() {
    return isWaitingForFileFromCounterpart;
  }

  private Optional<byte[]> readOptionalFileFromCounterpartWhenIsNotWaitingForFileFromCounterpart() {
    try {

      isWaitingForFileFromCounterpart = true;

      parentBackendWebClient.internalRunOnCounterpart(ChainedNode.withHeader(CommandProtocol.SEND_OPTIONAL_FILE));

      GlobalSequencer
        .forMaxSeconds(MAX_WAITING_TIME_FOR_FILE_FROM_COUNTERPART_IN_SECONDS)
        .waitAsLongAs(this::isWaitingForFileFromCounterpart);

      return Optional.ofNullable(latestOptionalFileFromCounterpart);
    } finally {
      isWaitingForFileFromCounterpart = false;
    }
  }

  //method
  private void receiveEmptyFileSelectionFromCounterpart() {

    assertIsWaitingForFileFromCounterpart();

    isWaitingForFileFromCounterpart = false;
    latestOptionalFileFromCounterpart = null;
  }

  //method
  private void receiveFileFromCounterpart(final byte[] file) {

    GlobalValidator.assertThat(file).thatIsNamed(LowerCaseCatalogue.FILE).isNotNull();
    assertIsWaitingForFileFromCounterpart();

    isWaitingForFileFromCounterpart = false;
    latestOptionalFileFromCounterpart = file; //NOSONAR: The given file can be stored instead of a copy.
  }
}
