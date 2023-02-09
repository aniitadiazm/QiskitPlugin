/*
 * Example Plugin for SonarQube

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
package org.sonarsource.plugins.qiskit;

import org.sonar.api.Plugin;

import org.sonarsource.plugins.qiskit.measures.ComputeQiskit;
import org.sonarsource.plugins.qiskit.measures.DepthSensor;
import org.sonarsource.plugins.qiskit.measures.Nº2CGmeasure;
import org.sonarsource.plugins.qiskit.measures.Nº3CGmeasure;
import org.sonarsource.plugins.qiskit.measures.NºC3Gmeasure;
import org.sonarsource.plugins.qiskit.measures.NºCHsensor;
import org.sonarsource.plugins.qiskit.measures.NºCInitSensor;
import org.sonarsource.plugins.qiskit.measures.NºCMsensor;
import org.sonarsource.plugins.qiskit.measures.NºCNOTsensor;
import org.sonarsource.plugins.qiskit.measures.NºCPGmeasure;
import org.sonarsource.plugins.qiskit.measures.NºCPsensor;
import org.sonarsource.plugins.qiskit.measures.NºCRZsensor;
import org.sonarsource.plugins.qiskit.measures.NºCResetSensor;
import org.sonarsource.plugins.qiskit.measures.NºCU3sensor;
import org.sonarsource.plugins.qiskit.measures.NºCUsensor;
import org.sonarsource.plugins.qiskit.measures.NºCYsensor;
import org.sonarsource.plugins.qiskit.measures.NºCZsensor;
import org.sonarsource.plugins.qiskit.measures.NºCliffGmeasure;
import org.sonarsource.plugins.qiskit.measures.NºFredSensor;
import org.sonarsource.plugins.qiskit.measures.NºHsensor;
import org.sonarsource.plugins.qiskit.measures.NºISsensor;
import org.sonarsource.plugins.qiskit.measures.NºITsensor;
import org.sonarsource.plugins.qiskit.measures.NºIsensor;
import org.sonarsource.plugins.qiskit.measures.NºMCGmeasure;
import org.sonarsource.plugins.qiskit.measures.NºNOTsensor;
import org.sonarsource.plugins.qiskit.measures.NºOCSensor;
import org.sonarsource.plugins.qiskit.measures.NºOifSensor;
import org.sonarsource.plugins.qiskit.measures.NºPGmeasure;
import org.sonarsource.plugins.qiskit.measures.NºPsensor;
import org.sonarsource.plugins.qiskit.measures.NºRXsensor;
import org.sonarsource.plugins.qiskit.measures.NºRYsensor;
import org.sonarsource.plugins.qiskit.measures.NºRZsensor;
import org.sonarsource.plugins.qiskit.measures.NºSCGmeasure;
import org.sonarsource.plugins.qiskit.measures.NºSRGmeasure;
import org.sonarsource.plugins.qiskit.measures.NºSWAPsensor;
import org.sonarsource.plugins.qiskit.measures.NºSifSensor;
import org.sonarsource.plugins.qiskit.measures.NºSsensor;
import org.sonarsource.plugins.qiskit.measures.NºTGmeasure;
import org.sonarsource.plugins.qiskit.measures.NºToffSensor;
import org.sonarsource.plugins.qiskit.measures.NºTsensor;
import org.sonarsource.plugins.qiskit.measures.NºUsensor;
import org.sonarsource.plugins.qiskit.measures.NºYsensor;
import org.sonarsource.plugins.qiskit.measures.NºZsensor;
import org.sonarsource.plugins.qiskit.measures.PorcCInitMeasure;
import org.sonarsource.plugins.qiskit.measures.PorcCMmeasure;
import org.sonarsource.plugins.qiskit.measures.PorcCResetMeasure;
import org.sonarsource.plugins.qiskit.measures.PorcMCGmeasure;
import org.sonarsource.plugins.qiskit.measures.PorcOCmeasure;
import org.sonarsource.plugins.qiskit.measures.PorcSCGmeasure;
import org.sonarsource.plugins.qiskit.measures.QiskitMetrics;
import org.sonarsource.plugins.qiskit.measures.WidthSensor;
import org.sonarsource.plugins.qiskit.settings.PythonLanguageProperties;
import org.sonarsource.plugins.qiskit.settings.QiskitProperties;
/**
 * This class is the entry point for all extensions. It is referenced in pom.xml.
 */
public class QiskitPlugin implements Plugin {

  @Override
  public void define(Context context) {

    // tutorial on languages
    // https://docs.sonarqube.org/9.4/extend/new-languages/
    //context.addExtensions(PythonLanguage.class, FooQualityProfile.class);
    context.addExtensions(PythonLanguageProperties.getProperties());

    // tutorial on measures
    context
      .addExtensions(QiskitMetrics.class, ComputeQiskit.class, WidthSensor.class,
      DepthSensor.class, NºNOTsensor.class, NºYsensor.class, NºZsensor.class, NºIsensor.class, NºUsensor.class,
      NºPsensor.class, NºHsensor.class, NºSsensor.class, NºISsensor.class, NºTsensor.class, NºITsensor.class,
      NºRXsensor.class, NºRYsensor.class, NºRZsensor.class, NºCNOTsensor.class, NºCYsensor.class,
      NºCZsensor.class, NºCUsensor.class, NºCHsensor.class, NºCRZsensor.class, NºCPsensor.class, NºCU3sensor.class,
      NºSWAPsensor.class, NºToffSensor.class, NºFredSensor.class, NºTGmeasure.class, NºPGmeasure.class,
      NºCliffGmeasure.class, NºC3Gmeasure.class, NºSRGmeasure.class, NºSCGmeasure.class, PorcSCGmeasure.class,
      NºCPGmeasure.class, Nº2CGmeasure.class, Nº3CGmeasure.class, NºMCGmeasure.class, PorcMCGmeasure.class,
      NºCMsensor.class, PorcCMmeasure.class, NºCResetSensor.class, NºCInitSensor.class, PorcCResetMeasure.class,
      PorcCInitMeasure.class, NºOifSensor.class, NºOCSensor.class, NºSifSensor.class, PorcOCmeasure.class);

    // tutorial on settings
    context
      .addExtensions(QiskitProperties.getProperties());
  }
}
