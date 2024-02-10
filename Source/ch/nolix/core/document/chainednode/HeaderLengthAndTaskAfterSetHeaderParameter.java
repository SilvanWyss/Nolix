//package declaration
package ch.nolix.core.document.chainednode;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;

//class
record HeaderLengthAndTaskAfterSetHeaderParameter(int headerLength, TaskAfterSetHeader taskAfterSetHeader) {

  //constructor
  public HeaderLengthAndTaskAfterSetHeaderParameter( //NOSONAR: This constructor does more than the default one.
    final int headerLength,
    final TaskAfterSetHeader taskAfterSetHeader) {

    if (taskAfterSetHeader == null) {
      throw ArgumentIsNullException.forArgumentName("task after header");
    }

    this.headerLength = headerLength;
    this.taskAfterSetHeader = taskAfterSetHeader;
  }

  //method
  public int getHeaderLength() {
    return headerLength;
  }

  //method
  public TaskAfterSetHeader getTaskAfterSetHeader() {
    return taskAfterSetHeader;
  }
}
