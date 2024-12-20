package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalHeaderHolder;

/**
 * A {@link IFluentMutableOptionalHeaderHolder} is a
 * {@link IOptionalHeaderHolder} whose header can be set and removed
 * programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2019-02-24
 * @param <FMOHH> is the type of a {@link IFluentMutableOptionalHeaderHolder}.
 */
public interface IFluentMutableOptionalHeaderHolder<FMOHH extends IFluentMutableOptionalHeaderHolder<FMOHH>>
extends IOptionalHeaderHolder {

  /**
   * Removes the header of current {@link IFluentMutableOptionalHeaderHolder}.
   * 
   * @return the current {@link IFluentMutableOptionalHeaderHolder}.
   */
  FMOHH removeHeader();

  /**
   * Sets the header of the current {@link IFluentMutableOptionalHeaderHolder}.
   * 
   * @param header
   * @return the current {@link IFluentMutableOptionalHeaderHolder}.
   * @throws RuntimeException if the given header is null.
   * @throws RuntimeException if the given header is blank.
   */
  FMOHH setHeader(String header);
}
