//package declaration
package ch.nolix.common.commontypehelper;

//enum
enum CharacterType {
	UPPER_CASE_LETTER,
	LOWER_CASE_LETTER,
	UNDERSCORE,
	OTHER;
	
	//static method
	public static CharacterType fromCharacter(final char character) {
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
