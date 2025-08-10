package ch.nolix.system.webgui.main;

import ch.nolix.core.web.htmlelementmodel.HtmlAttribute;
import ch.nolix.coreapi.web.html.HtmlAttributeNameCatalog;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlAttribute;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * Of the {@link ControlHelper} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2025-08-10
 */
public final class ControlHelper {

  /**
   * Prevents that an instance of the {@link ControlHelper} can be created.
   */
  private ControlHelper() {
  }

  /**
   * @param control
   * @return a new id Html attribute for the given control.
   * @throws RuntimeException if the given control is null.
   */
  public static IHtmlAttribute createIdHtmlAttributeForControl(final IControl<?, ?> control) {
    return HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalog.ID, control.getInternalId());
  }
}
