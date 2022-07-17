//package declaration
package ch.nolix.systemapi.guiapi.textformatapi;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
/**
 * A {@link Font} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-08-19
 */
public enum Font {
	ARIAL,
	ARIAL_BLACK,
	COMIC_SANS_MS,
	IMPACT,
	LUCIDA_CONSOLE,
	PAPYRUS,
	TAHOMA,
	VERDANA;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link Font} from the given specification.
	 * @throws RuntimeException if the given specification does not represent a {@link Font}.
	 */
	public static Font fromSpecification(final INode<?> specification) {
		return valueOf(specification.getSingleChildNodeHeader());
	}
	
	//method
	/**
	 * @return the code of the current {@link Font}.
	 */
	public String getCode() {
		
		//Enumerates the current Font.
		switch (this) {
			case ARIAL:
				return FontCodeCatalogue.ARIAL;
			case ARIAL_BLACK:
				return FontCodeCatalogue.ARIAL_BLACK;
			case COMIC_SANS_MS:
				return FontCodeCatalogue.COMIC_SANS_MS;
			case IMPACT:
				return FontCodeCatalogue.IMPACT;
			case LUCIDA_CONSOLE:
				return FontCodeCatalogue.LUCIDA_CONSOLE;
			case PAPYRUS:
				return FontCodeCatalogue.PAPYRUS;
			case TAHOMA:
				return FontCodeCatalogue.TAHOMA;
			case VERDANA:
				return FontCodeCatalogue.VERDANA;
			default:
				throw new IllegalArgumentException("The current Font is not valid.");
		}
	}
}
