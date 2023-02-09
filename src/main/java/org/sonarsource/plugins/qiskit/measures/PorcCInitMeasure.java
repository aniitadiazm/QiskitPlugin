package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.PorcCInit;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.WIDTH;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCInit;

public class PorcCInitMeasure implements MeasureComputer{

    @Override
    public MeasureComputerDefinition define(MeasureComputerDefinitionContext def) {
        return def.newDefinitionBuilder()
            .setInputMetrics(WIDTH.key(), NºCInit.key())
            .setOutputMetrics(PorcCInit.key())
            .build();
    }
  
    public void compute(MeasureComputerContext context) {
        Measure CInit = context.getMeasure(NºCInit.key());
        Measure WIDTH_ = context.getMeasure(WIDTH.key());
        
        double porcCInit = 0;
        if(WIDTH_ != null){
            porcCInit = (double)CInit.getIntValue() / (double)WIDTH_.getIntValue() * 100;
        }
        
        context.addMeasure(PorcCInit.key(), porcCInit);
    }
}
