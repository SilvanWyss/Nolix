package ch.nolix.system.webapplication.base;

import java.util.Base64;
import java.util.Optional;

import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.flowcontrol.FlowController;
import ch.nolix.coreapi.document.chainednode.IChainedNode;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.systemapi.application.basewebapplicationprotocol.CommandProtocol;

final class FileReader {

  private static final int MAX_WAITING_TIME_FOR_FILE_FROM_COUNTERPART_IN_SECONDS = 60;

  private final AbstractWebClient<?, ?> parentBackendWebClient;

  private boolean isWaitingForFileFromCounterpart;

  private byte[] latestOptionalFileFromCounterpart;

  private FileReader(final AbstractWebClient<?, ?> parentBackendWebClient) {

    Validator.assertThat(parentBackendWebClient).thatIsNamed("parent backend web client").isNotNull();

    this.parentBackendWebClient = parentBackendWebClient;
  }

  public static FileReader forBackendWebClient(
    final AbstractWebClient<?, ?> backendWebClient) {
    return new FileReader(backendWebClient);
  }

  public Optional<byte[]> readOptionalFileFromCounterpart() {

    assertIsNotWaitingForFileFromCounterpart();

    return readOptionalFileFromCounterpartWhenIsNotWaitingForFileFromCounterpart();
  }

  public void receiveOptionalFileFromCounterpart(final IChainedNode receiveOptionalFileCommand) {
    switch (receiveOptionalFileCommand.getChildNodeCount()) {
      case 0:
        receiveEmptyFileSelectionFromCounterpart();
        break;
      case 1:

        final var fileString = receiveOptionalFileCommand.getSingleChildNode().getHeader();

        //The received fileString is a Base64-encoded String.
        final var file = Base64.getDecoder().decode(fileString.substring(fileString.indexOf(',') + 1));

        receiveFileFromCounterpart(file);
        break;
      default:
        throw //
        InvalidArgumentException.forArgumentAndArgumentName(
          receiveOptionalFileCommand,
          "receive optional file commoand");
    }
  }

  private void assertIsNotWaitingForFileFromCounterpart() {
    if (isWaitingForFileFromCounterpart()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        this,
        "is already waiting for a file from the counterpart");
    }
  }

  private void assertIsWaitingForFileFromCounterpart() {
    if (!isWaitingForFileFromCounterpart()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not waiting for a file from counterpart");
    }
  }

  private boolean isWaitingForFileFromCounterpart() {
    return isWaitingForFileFromCounterpart;
  }

  private Optional<byte[]> readOptionalFileFromCounterpartWhenIsNotWaitingForFileFromCounterpart() {
    try {

      isWaitingForFileFromCounterpart = true;

      parentBackendWebClient.internalRunOnCounterpart(ChainedNode.withHeader(CommandProtocol.SEND_OPTIONAL_FILE));

      FlowController
        .forMaxSeconds(MAX_WAITING_TIME_FOR_FILE_FROM_COUNTERPART_IN_SECONDS)
        .waitAsLongAs(this::isWaitingForFileFromCounterpart);

      return Optional.ofNullable(latestOptionalFileFromCounterpart);
    } finally {
      isWaitingForFileFromCounterpart = false;
    }
  }

  private void receiveEmptyFileSelectionFromCounterpart() {

    assertIsWaitingForFileFromCounterpart();

    isWaitingForFileFromCounterpart = false;
    latestOptionalFileFromCounterpart = null;
  }

  private void receiveFileFromCounterpart(final byte[] file) {

    Validator.assertThat(file).thatIsNamed(LowerCaseVariableCatalog.FILE).isNotNull();
    assertIsWaitingForFileFromCounterpart();

    isWaitingForFileFromCounterpart = false;
    latestOptionalFileFromCounterpart = file; //NOSONAR: The given file can be stored instead of a copy.
  }
}
