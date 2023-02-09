package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.PorcCReset;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.WIDTH;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCReset;;

public class PorcCResetMeasure implements MeasureComputer{

    @Override
    public MeasureComputerDefinition define(MeasureComputerDefinitionContext def) {
        return def.newDefinitionBuilder()
            .setInputMetrics(WIDTH.key(), NºCReset.key())
            .setOutputMetrics(PorcCReset.key())
            .build();
    }
  
    public void compute(MeasureComputerContext context) {
        Measure CReset = context.getMeasure(NºCReset.key());
        Measure WIDTH_ = context.getMeasure(WIDTH.key());
       
        double porcCReset = 0;
        if(WIDTH_ != null){
            porcCReset = ((double)CReset.getIntValue() / (double)WIDTH_.getIntValue()) * 100;
        }
        
        context.addMeasure(PorcCReset.key(), porcCReset);
    }
}
