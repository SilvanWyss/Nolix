package ch.nolix.systemapi.webguiapi.controlstyleapi;

public interface IControlStyle<S extends IControlStyle<S>>
extends IBackgroundStyle<S>, IBorderStyle<S>, IControlHeadStyle<S>, ISizeStyle<S>, IPaddingStyle<S> {
}
