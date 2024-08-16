//package declaration
package ch.nolix.coretest.containertest.basetest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;

//class
public abstract class CopyableIteratorTest extends StandardTest {

  //method declaration
  protected abstract <E> CopyableIterator<E> createIteratorForEmptyContainerForType(Class<E> type);

  //method declaration
  protected abstract <E> CopyableIterator<E> createIteratorForContainerWithElements(
    E element,
    @SuppressWarnings("unchecked") final E... elements);

  //method
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
    GlobalSequencer.forCount(4).run(testUnit::next);

    //execution
    final var result = testUnit.getCopy();

    //verification
    expect(testUnit.next()).is(rhino);
    expect(testUnit.next()).is(zebra);
    expectNot(testUnit.hasNext());
    expect(result.next()).is(rhino);
    expect(result.next()).is(zebra);
    expectNot(result.hasNext());
  }
}
