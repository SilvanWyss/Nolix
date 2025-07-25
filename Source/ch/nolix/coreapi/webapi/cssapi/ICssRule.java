package ch.nolix.coreapi.webapi.cssapi;

import ch.nolix.coreapi.container.base.IContainer;

public interface ICssRule {

  IContainer<? extends ICssProperty> getProperties();

  String getSelector();

  ICssRule withPrefixedSelector(String selectorPrefix);
}
