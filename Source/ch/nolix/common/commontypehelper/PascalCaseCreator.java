//package declaration
package ch.nolix.common.commontypehelper;

//own import
import ch.nolix.common.constant.StringCatalogue;

//class
final class PascalCaseCreator {
	
	//method
	public String toPascalCase(final String string) {
		return (string.isEmpty() ? StringCatalogue.EMPTY_STRING : toPascalCaseWhenStringNotEmpty(string));
	}
	
	//ethod
	private String toPascalCaseWhenStringNotEmpty(final String string) {
		
		final var stringBuilder = new StringBuilder();
		
		final var firstCharacter = string.charAt(0);
		final var firstCharacterType = CharacterType.fromCharacter(firstCharacter);
		switch (firstCharacterType) {
			case LOWER_CASE_LETTER:
				stringBuilder.append(Character.toUpperCase(firstCharacter));
				break;
			default:
				stringBuilder.append(firstCharacter);
		}
		
		var previousCharacterType = firstCharacterType;
		for (var i = 1; i < string.length(); i++) {
			
			final var character = string.charAt(i);
			final var characterType = CharacterType.fromCharacter(character);
			
			switch (characterType) {
				case UPPER_CASE_LETTER:
					if (
						previousCharacterType == CharacterType.LOWER_CASE_LETTER
						|| previousCharacterType == CharacterType.UNDERSCORE
					) {
						stringBuilder.append(character);
					}
					else {
						stringBuilder.append(Character.toLowerCase(character));
					}
					break;
				case LOWER_CASE_LETTER:
					if (previousCharacterType == CharacterType.UNDERSCORE) {
						stringBuilder.append(Character.toUpperCase(character));
					}
					else {
						stringBuilder.append(character);
					}
					break;
				case UNDERSCORE:
					break;
				case OTHER:
					stringBuilder.append(character);
					break;
			}
			
			previousCharacterType = characterType;
		}
		
		return stringBuilder.toString();
	}
}
