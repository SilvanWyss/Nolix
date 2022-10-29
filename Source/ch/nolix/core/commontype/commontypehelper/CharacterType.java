//package declaration
package ch.nolix.core.commontype.commontypehelper;

//enum
enum CharacterType {
	UPPER_CASE_LETTER,
	LOWER_CASE_LETTER,
	UNDERSCORE,
	OTHER;
	
	//static method
	public static CharacterType fromCharacter(final char character) {
		switch (character) {
			case
			'A',
			'B',
			'C',
			'D',
			'E',
			'F',
			'G',
			'H',
			'I',
			'J',
			'K',
			'L',
			'M',
			'N',
			'O',
			'P',
			'Q',
			'R',
			'S',
			'T',
			'U',
			'V',
			'W',
			'X',
			'Y',
			'Z',
			'Ä',
			'Ö',
			'Ü':
				return CharacterType.UPPER_CASE_LETTER;
			case
			'a',
			'b',
			'c',
			'd',
			'e',
			'f',
			'g',
			'h',
			'i',
			'j',
			'k',
			'l',
			'm',
			'n',
			'o',
			'p',
			'q',
			'r',
			's',
			't',
			'u',
			'v',
			'w',
			'x',
			'y',
			'z',
			'ä',
			'ö',
			'ü':
				return CharacterType.LOWER_CASE_LETTER;
			case '_':
				return CharacterType.UNDERSCORE;
			default:
				return CharacterType.OTHER;
		}
	}
}
