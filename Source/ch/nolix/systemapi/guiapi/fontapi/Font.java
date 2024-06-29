//package declaration
package ch.nolix.systemapi.guiapi.fontapi;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
/**
 * A {@link Font} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2017-08-19
 */
public enum Font {
  ARIAL(FontCodeCatalogue.ARIAL),
  ARIAL_BLACK(FontCodeCatalogue.ARIAL_BLACK),
  COMIC_SANS_MS(FontCodeCatalogue.COMIC_SANS_MS),
  IMPACT(FontCodeCatalogue.IMPACT),
  LUCIDA_CONSOLE(FontCodeCatalogue.LUCIDA_CONSOLE),
  PAPYRUS(FontCodeCatalogue.PAPYRUS),
  TAHOMA(FontCodeCatalogue.TAHOMA),
  VERDANA(FontCodeCatalogue.VERDANA);

  //attribute
  private final String code;

  //constructor
  /**
   * Creates a new {@link Font} with the given code.
   * 
   * @param code
   */
  Font(final String code) {
    this.code = code;
  }

  //static method
  /**
   * @param specification
   * @return a new {@link Font} from the given specification.
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link Font}.
   */
  public static Font fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }

  //method
  /**
   * @return the code of the current {@link Font}.
   */
  public String getCode() {
    return code;
  }
}
