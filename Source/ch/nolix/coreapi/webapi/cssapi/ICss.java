package ch.nolix.coreapi.webapi.cssapi;

import ch.nolix.coreapi.container.base.IContainer;

public interface ICss {

  IContainer<? extends ICssRule> getRules();

  String toStringWithoutEnclosingBrackets();
}
