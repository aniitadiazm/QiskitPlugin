package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.PorcCM;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.WIDTH;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCM;

public class PorcCMmeasure implements MeasureComputer{

    @Override
    public MeasureComputerDefinition define(MeasureComputerDefinitionContext def) {
        return def.newDefinitionBuilder()
            .setInputMetrics(WIDTH.key(), NºCM.key())
            .setOutputMetrics(PorcCM.key())
            .build();
    }
  
    public void compute(MeasureComputerContext context) {
        Measure CM = context.getMeasure(NºCM.key());
        Measure WIDTH_ = context.getMeasure(WIDTH.key());
        
        double porcMCGates = 0;
        if(WIDTH_ != null){
            porcMCGates = (double)CM.getIntValue() / (double)WIDTH_.getIntValue() * 100;
        }

        context.addMeasure(PorcCM.key(), porcMCGates);
    }
}
