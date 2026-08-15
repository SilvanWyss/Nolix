/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.document.node.abstractnode;

import org.junit.jupiter.api.Test;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.environment.filesystem.FileSystemAccessor;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class ToFormattedStringTest extends StandardTest {
  @Test
  void testCase_toFormattedString() {
    // setup
    final var testUnit = ImmutableNode.fromString("Cat(Name(Garfield),Sex(Male),AgeInYears(5),WeightInGrams(6500))");

    // execute
    final var result = testUnit.toFormattedString();

    // verify
    final var expectedResult = FileSystemAccessor.readFile("./././././TestResource/sample_node/garfield.node");
    expect(result).isEqualTo(expectedResult);
  }
}
