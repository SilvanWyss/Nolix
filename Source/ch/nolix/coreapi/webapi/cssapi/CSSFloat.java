//package declaration
package ch.nolix.coreapi.webapi.cssapi;

//enum
public enum CSSFloat {
	LEFT("left"),
	RIGHT("right");
	
	//attribute
	private final String value;
	
	//constructor
	CSSFloat(final String value) {
		this.value = value;
	}
	
	//method
	@Override
	public String toString() {
		return value;
	}
}
