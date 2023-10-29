//package declaration
package ch.nolix.core.document.chainednode;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;

//class
record HeaderLengthAndTaskAfterHeaderParameter(int headerLength, TaskAfterHeader taskAfterHeader) {

  //constructor
  public HeaderLengthAndTaskAfterHeaderParameter( //NOSONAR: This constructor does more than the default one.
    final int headerLength,
    final TaskAfterHeader taskAfterHeader) {

    if (taskAfterHeader == null) {
      throw ArgumentIsNullException.forArgumentName("task after header");
    }

    this.headerLength = headerLength;
    this.taskAfterHeader = taskAfterHeader;
  }

  //method
  public int getHeaderLength() {
    return headerLength;
  }

  //method
  public TaskAfterHeader getTaskAfterHeader() {
    return taskAfterHeader;
  }
}
