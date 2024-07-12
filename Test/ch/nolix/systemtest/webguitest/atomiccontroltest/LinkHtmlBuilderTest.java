//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

//own imports
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.webgui.atomiccontrol.Link;
import ch.nolix.system.webgui.atomiccontrol.LinkHtmlBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILink;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlHtmlBuilderTest;

//class
final class LinkHtmlBuilderTest extends ControlHtmlBuilderTest<LinkHtmlBuilder, ILink> {

  //method
  @Override
  protected ILink createControl() {
    return new Link();
  }

  //method
  @Override
  protected LinkHtmlBuilder createTestUnit() {
    return new LinkHtmlBuilder();
  }

  //method
  @Override
  protected void expectSpecificPropertiesOnHtmlElementCreatedOfNewControl(final IHtmlElement htmlElement) {
    expect(htmlElement.getType()).isEqualTo(HtmlElementTypeCatalogue.A);
    expect(htmlElement.getAttributes().containsAny(a -> a.hasName("target")));
    expectNot(htmlElement.containsChildElements());
    expect(htmlElement.getInnerText()).isEqualTo(Link.DEFAULT_DISPLAY_TEXT);
  }
}
