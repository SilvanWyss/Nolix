package ch.nolix.coreapi.webapi.cssapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface ICss {

  IContainer<? extends ICssRule> getRules();

  String toStringWithoutEnclosingBrackets();
}
