//package declaration
package ch.nolix.element.gui.textformat;

//own imports
import ch.nolix.common.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.common.constant.FontCodeCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.element.elementapi.IElement;

//enum
/**
 * A {@link Font} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 80
 */
public enum Font implements IElement<Font> {
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
		return valueOf(GlobalStringHelper.toCapitalSnakeCase(specification.getOneAttributeHeader()));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(Node.withHeader(GlobalStringHelper.toPascalCase(toString())));
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
