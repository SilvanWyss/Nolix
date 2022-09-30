//package declaration
package ch.nolix.coreapi.webapi.cssapi;

//enum
public enum CSSJustifyContent {
	CENTER("center"),
	LEFT("left"),
	RIGHT("right");
	
	//attribute
	private final String value;
	
	//constructor
	CSSJustifyContent(final String value) {
		this.value = value;
	}
	
	//method
	@Override
	public String toString() {
		return value;
	}
}
