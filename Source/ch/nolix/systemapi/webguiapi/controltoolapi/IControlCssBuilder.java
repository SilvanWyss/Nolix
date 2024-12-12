package ch.nolix.systemapi.webguiapi.controltoolapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;
import ch.nolix.systemapi.webguiapi.controlstyleapi.IControlStyle;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public interface IControlCssBuilder<C extends IControl<C, S>, S extends IControlStyle<S>> {

  IContainer<ICssRule> createCssRulesForControl(C control);
}
