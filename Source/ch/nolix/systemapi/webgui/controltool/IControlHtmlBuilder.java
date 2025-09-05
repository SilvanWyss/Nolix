package ch.nolix.systemapi.webgui.controltool;

import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.webgui.main.IControl;

public interface IControlHtmlBuilder<C extends IControl<C, ?>> {
  IHtmlElement createHtmlElementForControl(C control);
}
