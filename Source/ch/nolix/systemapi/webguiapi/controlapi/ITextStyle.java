//package declaration
package ch.nolix.systemapi.webguiapi.controlapi;

//own imports
import ch.nolix.systemapi.webguiapi.controlstyleapi.IExtendedControlStyle;

//interface
public interface ITextStyle<TS extends ITextStyle<TS>> extends IExtendedControlStyle<TS> {}
