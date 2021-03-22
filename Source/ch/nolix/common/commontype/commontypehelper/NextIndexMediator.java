//package declaration
package ch.nolix.common.commontype.commontypehelper;

//class
public final class NextIndexMediator {
	
	//attribute
	private final int index;
	
	//visibility-reduced constructor
	NextIndexMediator(final int index) {
		this.index = index;
	}
	
	//method
	public int andGetNextIndex() {
		return index;
	}
}
