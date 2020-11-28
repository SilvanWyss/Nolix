//package declaration
package ch.nolix.element.textformat;

//own imports
import ch.nolix.common.commontypehelper.StringHelper;
import ch.nolix.common.constant.FontCodeCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.elementapi.IElementEnum;

//enum
/**
 * A {@link Font} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 80
 */
public enum Font implements IElementEnum {
	ARIAL,
	ARIAL_BLACK,
	COMIC_SANS_MS,
	IMPACT,
	LUCIDA_CONSOLE,
	PAPYRUS,
	TAHOMA,
	VERDANA;
	
	//constant
	public static final String TYPE_NAME = "Font";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link Font} from the given specification.
	 * @throws InvalidArgumentException if the given specification does not represent a {@link Font}.
	 */
	public static Font fromSpecification(final BaseNode specification) {
		return valueOf(StringHelper.toCapitalSnakeCase(specification.getOneAttributeHeader()));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		return new Node(StringHelper.toPascalCase(toString())).intoList();
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
				throw new InvalidArgumentException(this);
		}
	}
}
