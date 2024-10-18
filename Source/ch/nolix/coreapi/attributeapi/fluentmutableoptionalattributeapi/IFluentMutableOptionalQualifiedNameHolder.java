package ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalQualifiedNameHolder;

/**
 * A {@link IFluentMutableOptionalQualifiedNameHolder} is a
 * {@link IOptionalQualifiedNameHolder} whose name can be set and removed
 * programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2024-02-11
 * @param <FMOQNH> is the type of a
 *                 {@link IFluentMutableOptionalQualifiedNameHolder}.
 */
public interface IFluentMutableOptionalQualifiedNameHolder< //
FMOQNH extends IFluentMutableOptionalQualifiedNameHolder<FMOQNH> //
>
extends IFluentMutableOptionalNameHolder<FMOQNH>, IOptionalQualifiedNameHolder {
}
