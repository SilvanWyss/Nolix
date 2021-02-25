package ch.nolix.common.chainednode;

import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;

final class HeaderLengthAndTaskAfterHeaderParameter {
	
	private final int headerLength;
	private final TaskAfterHeader taskAfterHeader;
	
	public HeaderLengthAndTaskAfterHeaderParameter(final int headerLength, final TaskAfterHeader taskAfterHeader) {
		
		if (taskAfterHeader == null) {
			throw new ArgumentIsNullException("task after header");
		}
		
		this.headerLength = headerLength;
		this.taskAfterHeader = taskAfterHeader;
	}
	
	public int getHeaderLength() {
		return headerLength;
	}
	
	public TaskAfterHeader getTaskAfterHeader() {
		return taskAfterHeader;
	}
}
