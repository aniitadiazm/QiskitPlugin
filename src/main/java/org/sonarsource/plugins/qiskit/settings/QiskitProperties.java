/*
 * Qiskit Plugin for SonarQube
 * Copyright (C) 2009-2020 SonarSource SA
 * mailto:contact AT sonarsource DOT com
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
package org.sonarsource.plugins.qiskit.settings;

import java.util.List;
import org.sonar.api.config.PropertyDefinition;

import static java.util.Arrays.asList;

public class QiskitProperties {

  public static final String QISKIT_KEY = "sonar.qiskit";
  public static final String CATEGORY = "Properties Qiskit";

  private QiskitProperties() {
    // only statics
  }

  public static List<PropertyDefinition> getProperties() {
    return asList(
      PropertyDefinition.builder(QISKIT_KEY)
        .name("Qiskit Plugin")
        .description("Qiskit Plugin for SonarQube: Python + Qiskit Language")
        .defaultValue(String.valueOf(false))
        .category(CATEGORY)
        .build());
  }

}
