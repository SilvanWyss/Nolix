package ch.nolix.coretest.container.linkedlist.linkedlist;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.testing.standardtest.StandardTest;

final class GetCopyMethodTest extends StandardTest {

  @Test
  void testCase_getCopy_whenIsEmpty() {

    //setup
    final var testUnit = LinkedList.createEmpty();

    //execution
    final var result = testUnit.getCopy();

    //verification
    expect(result.isEmpty()).isTrue();
  }

  @Test
  void testCase_getCopy_whenContainsAny() {

    //setup
    final var antelope = "antelope";
    final var baboon = "baboon";
    final var elephant = "elephant";
    final var lion = "lion";
    final var rhino = "rhino";
    final var zebra = "zebra";
    final var testUnit = LinkedList.withElement(antelope, baboon, elephant, lion, rhino, zebra);

    //execution
    final var result = testUnit.getCopy();

    //verification
    expect(result).containsExactlyInSameOrder(antelope, baboon, elephant, lion, rhino, zebra);
  }
}
