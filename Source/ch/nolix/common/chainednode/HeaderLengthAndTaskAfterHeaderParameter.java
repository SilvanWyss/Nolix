//package declaration
package ch.nolix.common.chainednode;

//own import
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;

//class
final class HeaderLengthAndTaskAfterHeaderParameter {
	
	//attributes
	private final int headerLength;
	private final TaskAfterHeader taskAfterHeader;
	
	//constructor
	public HeaderLengthAndTaskAfterHeaderParameter(final int headerLength, final TaskAfterHeader taskAfterHeader) {
		
		if (taskAfterHeader == null) {
			throw new ArgumentIsNullException("task after header");
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
