//package declaration
package ch.nolix.element.baseAPI;

//own imports
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.node.Node;

//interface
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 50
 */
public interface IElementEnum extends IElement {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public default LinkedList<Node> getAttributes() {
		return new Node(toString()).intoList();
	}
	
	//method
	/**
	 * @return a {@link String} representation of the current {@link IElementEnum} in camel case.
	 */
	public default String toCamelCaseString() {
		
		final var stringBuilder = new StringBuilder();
		
		final var string = toString();
		var nextCharacterIsUpperCase = true;
		for (var i = 0; i < string.length(); i++) {
			
			final var character = string.charAt(i);
			
			if (character == '_') {
				nextCharacterIsUpperCase = true;
			}
			else if (!nextCharacterIsUpperCase) {
				stringBuilder.append(Character.toLowerCase(character));
			}
			else {
				stringBuilder.append(Character.toUpperCase(character));
				nextCharacterIsUpperCase = false;
			}
		}
		
		return stringBuilder.toString();
	}
}
