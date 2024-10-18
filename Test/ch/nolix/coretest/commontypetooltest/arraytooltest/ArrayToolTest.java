package ch.nolix.coretest.commontypetooltest.arraytooltest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.commontypetool.arraytool.ArrayTool;
import ch.nolix.core.testing.standardtest.StandardTest;

final class ArrayToolTest extends StandardTest {

  @Test
  void testCase_onArray_fromIndex_write() {

    //setup
    final var array = new byte[10];
    final var testUnit = new ArrayTool();

    //execution
    final var result = testUnit.onArray(array).fromIndex(2).write(new byte[] { 30, 40, 50 });

    //verification part 1
    expect(array[0]).isEqualTo(0);
    expect(array[1]).isEqualTo(0);
    expect(array[2]).isEqualTo(30);
    expect(array[3]).isEqualTo(40);
    expect(array[4]).isEqualTo(50);
    expect(array[5]).isEqualTo(0);
    expect(array[6]).isEqualTo(0);
    expect(array[7]).isEqualTo(0);
    expect(array[8]).isEqualTo(0);
    expect(array[9]).isEqualTo(0);

    //verification part 2
    expect(result.andGetNextIndex()).isEqualTo(5);
  }
}
