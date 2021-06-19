//package declaration
package ch.nolix.common.attributeapi.mandatoryattributeapi;

//interface
/**
* A {@link ITextHolder} has a text.
* 
* @author Silvan Wyss
* @date 2021-06-19
* @lines 50
*/
public interface ITextHolder {
	
	//method declaration
	/**
	 * @return the text of the current {@link ITextHolder}.
	 */
	String getText();
	
	//method
	/**
	 * @return the text of the current {@link ITextHolder} in quotes.
	 */
	default String getTextInQuotes() {
		return ("'" + getText() + "'");
	}
	
	//method
	/**
	 * @param object
	 * @return true if the current {@link ITextHolder} has the same text as the given object.
	 */
	default boolean hasSameTextAs(final ITextHolder object) {
		
		//Handles the case that the given object is null.
		if (object == null) {
			return false;
		}
		
		//Handles the case that the given object is not null.
		return getText().equals(object.getText());
	}
	
	//method
	/**
	 * @param text
	 * @return true if the current {@link ITextHolder} has the given text.
	 */
	default boolean hasText(final String text) {
		return getText().equals(text);
	}
}
