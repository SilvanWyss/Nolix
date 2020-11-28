//package declaration
package ch.nolix.element.textFormat;

//own imports
import ch.nolix.common.constant.FontCodeCatalogue;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.elementAPI.IElementEnum;

//enum
/**
 * A {@link Font} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 60
 */
public enum Font implements IElementEnum {
	Arial,
	ArialBlack,
	ComicSansMS,
	Impact,
	LucidaConsole,
	Papyrus,
	Tahoma,
	Verdana;
	
	//constant
	public static final String TYPE_NAME = "Font";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link Font} from the given specification.
	 * @throws InvalidArgumentException if the given specification does not represent a {@link Font}.
	 */
	public static Font fromSpecification(final BaseNode specification) {
		return Font.valueOf(specification.getOneAttributeHeader());
	}
	
	//method
	/**
	 * @return the code of the current {@link Font}.
	 */
	public String getCode() {
		
		//Enumerates the current Font.
		switch (this) {
			case Arial:
				return FontCodeCatalogue.ARIAL;
			case ArialBlack:
				return FontCodeCatalogue.ARIAL_BLACK;
			case ComicSansMS:
				return FontCodeCatalogue.COMIC_SANS_MS;
			case Impact:
				return FontCodeCatalogue.IMPACT;
			case LucidaConsole:
				return FontCodeCatalogue.LUCIDA_CONSOLE;
			case Papyrus:
				return FontCodeCatalogue.PAPYRUS;
			case Tahoma:
				return FontCodeCatalogue.TAHOMA;
			case Verdana:
				return FontCodeCatalogue.VERDANA;
			default:
				throw new InvalidArgumentException(this);
		}
	}
}
