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
package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.ce.measure.Component;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;


public class ComputeQiskit implements MeasureComputer {

    @Override
    public MeasureComputerDefinition define(MeasureComputerDefinitionContext def) {
        return def.newDefinitionBuilder()
            .setOutputMetrics(new String[] { QiskitMetrics.WIDTH.key(), QiskitMetrics.DEPTH.key(),
            QiskitMetrics.NºNOT.key(), QiskitMetrics.NºY.key(), QiskitMetrics.NºZ.key(), QiskitMetrics.NºI.key(),
            QiskitMetrics.NºU.key(), QiskitMetrics.NºP.key(), QiskitMetrics.NºH.key(), QiskitMetrics.NºS.key(),
            QiskitMetrics.NºIS.key(), QiskitMetrics.NºT.key(), QiskitMetrics.NºIT.key(), QiskitMetrics.NºRX.key(),
            QiskitMetrics.NºRY.key(), QiskitMetrics.NºRZ.key(), QiskitMetrics.NºCNOT.key(), QiskitMetrics.NºCY.key(),
            QiskitMetrics.NºCZ.key(), QiskitMetrics.NºCU.key(), QiskitMetrics.NºCH.key(), QiskitMetrics.NºCRZ.key(),
            QiskitMetrics.NºCP.key(), QiskitMetrics.NºCU3.key(), QiskitMetrics.NºSWAP.key(), QiskitMetrics.NºToff.key(),
            QiskitMetrics.NºFred.key(), QiskitMetrics.NºCM.key(), QiskitMetrics.NºCReset.key(), QiskitMetrics.NºCInit.key(),
            QiskitMetrics.NºOif.key(), QiskitMetrics.NºOC.key(), QiskitMetrics.NºSif.key() } )
            .build();
    }

    @Override
    public void compute(MeasureComputerContext context) {
      // measure is already defined on files by {@link SetSizeOnFilesSensor}
      // in scanner stack
        if (context.getComponent().getType() != Component.Type.FILE){
                
            int sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.WIDTH.key())) {
            sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.WIDTH.key(), sum);
            
            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.DEPTH.key())) {
            sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.DEPTH.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºNOT.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºNOT.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºY.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºY.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºZ.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºZ.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºI.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºI.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºU.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºU.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºP.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºP.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºH.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºH.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºS.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºS.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºIS.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºIS.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºT.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºT.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºIT.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºIT.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºRX.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºRX.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºRY.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºRY.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºRZ.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºRZ.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºCNOT.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºCNOT.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºCY.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºCY.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºCZ.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºCZ.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºCU.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºCU.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºCH.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºCH.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºCRZ.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºCRZ.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºCP.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºCP.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºCU3.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºCU3.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºSWAP.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºSWAP.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºToff.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºToff.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºFred.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºFred.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºCM.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºCM.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºCReset.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºCReset.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºCInit.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºCInit.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºOif.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºOif.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºOC.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºOC.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.NºSif.key())) {
                sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.NºSif.key(), sum);
        }
    }
}
