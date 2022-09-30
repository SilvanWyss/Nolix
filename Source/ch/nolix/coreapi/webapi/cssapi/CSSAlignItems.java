//package declaration
package ch.nolix.coreapi.webapi.cssapi;

//enum
public enum CSSAlignItems {
	CENTER("center"),
	END("end"),
	START("start");
	
	//attribute
	private final String value;
	
	//constructor
	CSSAlignItems(final String value) {
		this.value = value;
	}
	
	//method
	@Override
	public String toString() {
		return value;
	}
}
