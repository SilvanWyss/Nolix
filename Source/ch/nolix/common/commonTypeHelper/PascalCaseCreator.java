//package declaration
package ch.nolix.common.commonTypeHelper;

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
		final var firstCharacterType = getCharacterType(firstCharacter);
		switch (firstCharacterType) {
			case LOWER_CASE_LETTER:
				stringBuilder.append(Character.toUpperCase(firstCharacter));
			default:
				stringBuilder.append(firstCharacter);
		}
		
		var previousCharacterType = firstCharacterType;
		for (var i = 1; i < string.length(); i++) {
			
			final var character = string.charAt(i);
			final var characterType = getCharacterType(character);
			
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
	
	//method
	private static CharacterType getCharacterType(final char character) {
		switch (character) {
			case 'A':
			case 'B':
			case 'C':
			case 'D':
			case 'E':
			case 'F':
			case 'G':
			case 'H':
			case 'I':
			case 'J':
			case 'K':
			case 'L':
			case 'M':
			case 'N':
			case 'O':
			case 'P':
			case 'Q':
			case 'R':
			case 'S':
			case 'T':
			case 'U':
			case 'V':
			case 'W':
			case 'X':
			case 'Z':
			case 'Ä':
			case 'Ö':
			case 'Ü':
				return CharacterType.UPPER_CASE_LETTER;
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'f':
			case 'g':
			case 'h':
			case 'i':
			case 'j':
			case 'k':
			case 'l':
			case 'm':
			case 'n':
			case 'o':
			case 'p':
			case 'q':
			case 'r':
			case 's':
			case 't':
			case 'u':
			case 'v':
			case 'w':
			case 'x':
			case 'y':
			case 'z':
			case 'ä':
			case 'ö':
			case 'ü':
				return CharacterType.LOWER_CASE_LETTER;
			case '_':
				return CharacterType.UNDERSCORE;
			default:
				return CharacterType.OTHER;
		}			
	}
}
