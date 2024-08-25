//package declaration
package ch.nolix.systemapitest.guiapitest.fontapitest;

//JUnit imports
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.guiapi.fontapi.Font;

//class
final class FontTest extends StandardTest {

  //method
  @MethodSource
  private static IContainer<Arguments> getSpecificationsAndTheirFonts() {
    return //
    ImmutableList.withElement(
      Arguments.of(Node.withChildNode("ARIAL"), Font.ARIAL),
      Arguments.of(Node.withChildNode("ARIAL_BLACK"), Font.ARIAL_BLACK),
      Arguments.of(Node.withChildNode("COMIC_SANS_MS"), Font.COMIC_SANS_MS),
      Arguments.of(Node.withChildNode("IMPACT"), Font.IMPACT),
      Arguments.of(Node.withChildNode("LUCIDA_CONSOLE"), Font.LUCIDA_CONSOLE),
      Arguments.of(Node.withChildNode("PAPYRUS"), Font.PAPYRUS),
      Arguments.of(Node.withChildNode("TAHOMA"), Font.TAHOMA),
      Arguments.of(Node.withChildNode("VERDANA"), Font.VERDANA));
  }

  //method
  @ParameterizedTest
  @MethodSource("getSpecificationsAndTheirFonts")
  void fromSpecification(final INode<?> specification, final Font expectedFont) {

    //execution
    final var result = Font.fromSpecification(specification);

    //verification
    expect(result).is(expectedFont);
  }
}
