/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.unionarchitecturetest;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

import ch.nolix.base.testing.archunit.ArchUnitRuleCatalog;

/**
 * @author Silvan Wyss
 */
final class PureAbstractAndPureFinalClassesTest {
  private static final JavaClasses TEST_UNIT = //
  new ClassFileImporter()
    .importPackages("ch.nolix..")
    .that(
      new DescribedPredicate<JavaClass>("pure abstract or pure final classes") {
        @Override
        public boolean test(final JavaClass javaClass) {
          final var javaClassName = javaClass.getName();

          return !javaClassName.matches(".*Captor");
        }
      });

  @Test
  void testCase_nonAnonymousCaptorClassesAreAbstractOrFinal() {
    // execute & verify
    ArchUnitRuleCatalog.NON_ANONYMOUS_CLASSES_ARE_ABSTRACT_OR_FINAL.check(TEST_UNIT);
  }
}
