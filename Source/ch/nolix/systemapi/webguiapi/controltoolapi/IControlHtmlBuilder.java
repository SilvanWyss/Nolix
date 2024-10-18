package ch.nolix.systemapi.webguiapi.controltoolapi;

import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public interface IControlHtmlBuilder<C extends IControl<C, ?>> {

  IHtmlElement createHtmlElementForControl(C control);
}
