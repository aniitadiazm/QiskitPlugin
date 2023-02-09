package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.ce.measure.Measure;
import org.sonar.api.ce.measure.MeasureComputer;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.PorcOC;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºOif;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºOC;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºTG;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCM;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCReset;
import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºCInit;

public class PorcOCmeasure implements MeasureComputer{

    @Override
    public MeasureComputerDefinition define(MeasureComputerDefinitionContext def) {
        return def.newDefinitionBuilder()
            .setInputMetrics(NºOif.key(), NºOC.key(), NºTG.key(), NºCM.key(), NºCReset.key(), NºCInit.key())
            .setOutputMetrics(PorcOC.key())
            .build();
    }
  
    public void compute(MeasureComputerContext context) {
        Measure Oif = context.getMeasure(NºOif.key());
        Measure OC = context.getMeasure(NºOC.key());
        Measure TG = context.getMeasure(NºTG.key());
        Measure CM = context.getMeasure(NºCM.key());
        Measure CReset = context.getMeasure(NºCReset.key());
        Measure CInit = context.getMeasure(NºCInit.key());
        
        double totalPorcOC = 0;
        if(TG != null || CM != null || CReset != null || CInit != null) {   
            totalPorcOC = ((double)Oif.getIntValue() + (double)OC.getIntValue()) / ((double)TG.getIntValue() +
            (double)CM.getIntValue() + (double)CReset.getIntValue() + (double)CInit.getIntValue() +
            (double)Oif.getIntValue()) * 100;
        }
        context.addMeasure(PorcOC.key(), totalPorcOC);
    }
}
