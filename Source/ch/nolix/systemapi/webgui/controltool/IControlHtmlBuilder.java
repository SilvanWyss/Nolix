package ch.nolix.systemapi.webgui.controltool;

import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 * @param <C> is the type of the {@link IControl}s a {@link IControlHtmlBuilder}
 *            is for.
 */
public interface IControlHtmlBuilder<C extends IControl<C, ?>> {
  IHtmlElement createHtmlElementForControl(C control);
}
