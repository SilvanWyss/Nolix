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
final class ExaminerClassesTest {
  private static final JavaClasses TEST_UNIT = //
  new ClassFileImporter()
    .importPackages("ch.nolix..")
    .that(
      new DescribedPredicate<JavaClass>("examiner classes") {
        @Override
        public boolean test(final JavaClass javaClass) {
          return javaClass.getName().matches(".*Examiner");
        }
      });

  @Test
  void testCase_publicMemberMethodsOfExaminerClassesReturnAPrimitiveBoolean() {
    // execute & verify
    ArchUnitRuleCatalog.PUBLIC_MEMBER_METHODS_RETURN_A_PRIMITIVE_BOOLEAN.check(TEST_UNIT);
  }
}
