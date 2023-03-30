package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.ce.measure.Component;
import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;


public class ComputeQiskit implements MeasureComputer {

    @Override
    public MeasureComputerDefinition define(MeasureComputerDefinitionContext def) {
        return def.newDefinitionBuilder()
            .setOutputMetrics(new String[] { QiskitMetrics.WIDTH.key(), QiskitMetrics.DEPTH.key(),  QiskitMetrics.LCG.key(),
                QiskitMetrics.MCG.key(),  QiskitMetrics.HCG.key(),  QiskitMetrics.IFINST.key(),  QiskitMetrics.QCC.key(),
                QiskitMetrics.MEASURE.key(),  QiskitMetrics.INITRESET.key(), QiskitMetrics.AUXQUBITS.key()
             } )
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
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.LCG.key())) {
            sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.LCG.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.MCG.key())) {
            sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.MCG.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.HCG.key())) {
            sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.HCG.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.IFINST.key())) {
            sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.IFINST.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.QCC.key())) {
            sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.QCC.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.MEASURE.key())) {
            sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.MEASURE.key(), sum);

            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.INITRESET.key())) {
            sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.INITRESET.key(), sum);
            
            sum = 0;
            for (Measure child : context.getChildrenMeasures(QiskitMetrics.AUXQUBITS.key())) {
            sum += child.getIntValue();
            }
            context.addMeasure(QiskitMetrics.AUXQUBITS.key(), sum);
        }
    }
}
