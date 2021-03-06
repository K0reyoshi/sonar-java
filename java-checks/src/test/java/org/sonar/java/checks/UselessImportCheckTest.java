/*
 * SonarQube Java
 * Copyright (C) 2012-2020 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.java.checks;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class UselessImportCheckTest {

  @Test
  public void detected_with_package() {
    JavaCheckVerifier.newVerifier()
      .onFile("src/test/files/checks/UselessImportCheck/WithinPackage.java")
      .withCheck(new UselessImportCheck())
      .verifyIssues();
  }

  @Test
  public void detected_within_package_info() {
    JavaCheckVerifier.newVerifier()
      .onFile("src/test/files/checks/UselessImportCheck/package-info.java")
      .withCheck(new UselessImportCheck())
      .verifyIssues();
  }

  @Test
  public void no_semantic() {
    JavaCheckVerifier.newVerifier()
      .onFile("src/test/files/checks/UselessImportCheck/NoSemanticWithPackage.java")
      .withCheck(new UselessImportCheck())
      .withoutSemantic()
      .verifyNoIssues();
  }

  @Test
  public void detected_without_package() {
    JavaCheckVerifier.newVerifier()
      .onFile("src/test/files/checks/UselessImportCheck/WithoutPackage.java")
      .withCheck(new UselessImportCheck())
      .verifyIssues();
  }

  @Test
  public void with_module() {
    JavaCheckVerifier.newVerifier()
      .onFile("src/test/files/checks/module/module-info.java")
      .withCheck(new UselessImportCheck())
      .verifyNoIssues();
  }

  @Test
  public void intersection_type() {
    JavaCheckVerifier.newVerifier()
      .onFile("src/test/files/checks/UselessImportCheck/IntersectionCase.java")
      .withCheck(new UselessImportCheck())
      .verifyIssues();
  }
}
