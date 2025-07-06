package ch.nolix.core.container.base;

import org.junit.jupiter.api.Test;

import ch.nolix.core.programcontrol.flowcontrol.FlowController;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;

public abstract class CopyableIteratorTest extends StandardTest {

  protected abstract <E> CopyableIterator<E> createIteratorForEmptyContainerForType(Class<E> type);

  protected abstract <E> CopyableIterator<E> createIteratorForContainerWithElements(
    E element,
    @SuppressWarnings("unchecked") final E... elements);

  @Test
  void testCase_getCopy() {

    //setup
    final var antelope = "antelope";
    final var baboon = "baboon";
    final var elephant = "elephant";
    final var lion = "lion";
    final var rhino = "rhino";
    final var zebra = "zebra";
    final var testUnit = //
    createIteratorForContainerWithElements(antelope, baboon, elephant, lion, rhino, zebra);
    FlowController.forCount(4).run(testUnit::next);

    //execution
    final var result = testUnit.getCopy();

    //verification
    expect(testUnit.next()).is(rhino);
    expect(testUnit.next()).is(zebra);
    expect(testUnit.hasNext()).isFalse();
    expect(result.next()).is(rhino);
    expect(result.next()).is(zebra);
    expect(result.hasNext()).isFalse();
  }
}
