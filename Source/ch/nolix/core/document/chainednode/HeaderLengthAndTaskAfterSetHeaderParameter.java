package ch.nolix.core.document.chainednode;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;

record HeaderLengthAndTaskAfterSetHeaderParameter(int headerLength, TaskAfterSetHeader taskAfterSetHeader) {

  public HeaderLengthAndTaskAfterSetHeaderParameter( //NOSONAR: This constructor does more than the default one.
    final int headerLength,
    final TaskAfterSetHeader taskAfterSetHeader) {

    if (taskAfterSetHeader == null) {
      throw ArgumentIsNullException.forArgumentName("task after header");
    }

    this.headerLength = headerLength;
    this.taskAfterSetHeader = taskAfterSetHeader;
  }

  public int getHeaderLength() {
    return headerLength;
  }

  public TaskAfterSetHeader getTaskAfterSetHeader() {
    return taskAfterSetHeader;
  }
}
