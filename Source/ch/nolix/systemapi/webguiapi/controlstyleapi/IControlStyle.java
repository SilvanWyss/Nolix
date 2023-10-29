//package declaration
package ch.nolix.systemapi.webguiapi.controlstyleapi;

//interface
public interface IControlStyle<CS extends IControlStyle<CS>>
extends IBackgroundStyle<CS>, IBorderStyle<CS>, IControlHeadStyle<CS>, ISizeStyle<CS>, IPaddingStyle<CS> {
}
