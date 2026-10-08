/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.unionarchitecturetest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import ch.nolix.base.environment.filesystem.FolderAccessor;
import ch.nolix.base.errorcontrol.generalexception.GeneralException;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class SourceFilesTest extends StandardTest {
  @ParameterizedTest
  @ValueSource(strings = { "Source", "Test", "ArchitectureTest" })
  void testCase_sourceFilesHaveHeader( //NOSONAR: An assertion is done by a custom validation.
    final String rootFolderPath) {
    final var sourceFiles = FolderAccessor.forFolderPath(rootFolderPath).getFileAccessorsRecursively();

    for (final var f : sourceFiles) {
      final var sourceFileName = f.getName();

      if (sourceFileName.endsWith(".java") && !sourceFileName.endsWith("package-info.java")) {
        final var lines = f.readFileToLines();

        if (lines.getCount() < 3
        || !lines.getStoredAtOneBasedIndex(1).equals("/*")
        || !lines.getStoredAtOneBasedIndex(2).equals(" * Copyright © by Silvan Wyss. All rights reserved.")
        || !lines.getStoredAtOneBasedIndex(3).equals(" */")) {
          throw GeneralException.withErrorMessage("The file '" + sourceFileName + "' does not have a valid header.");
        }
      }
    }
  }
}
