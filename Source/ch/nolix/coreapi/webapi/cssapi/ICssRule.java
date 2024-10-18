package ch.nolix.coreapi.webapi.cssapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface ICssRule {

  IContainer<? extends ICssProperty> getProperties();

  String getSelector();

  ICssRule withPrefixedSelector(String selectorPrefix);
}
