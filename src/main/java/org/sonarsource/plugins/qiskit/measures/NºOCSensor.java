package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import java.io.IOException;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºOC;

public class NºOCSensor implements Sensor {

    @Override
    public void describe(SensorDescriptor descriptor) {
        descriptor.name("Calcular el número de operaciones controladas");
    }

    @Override
    public void execute(SensorContext context) {
        FileSystem fs = context.fileSystem();
        Iterable<InputFile> files = fs.inputFiles(fs.predicates().hasType(InputFile.Type.MAIN));
        for (InputFile file : files) {
        try {
            context.<Integer>newMeasure()
                .forMetric(NºOC)
                .on(file)
                .withValue(calculate(file, context))
                .save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }

    public int calculate(InputFile f, SensorContext context) throws IOException {
        int totalOC = 0;
        if (f.isFile()) {

        try {
            String content = f.contents();
            String[] lines = content.split("\\R");
            for (String line : lines) {
            if (line.contains(".cx(") || line.contains(".cy(") || line.contains(".cz(") || line.contains(".ch(")
            || line.contains(".crz(") || line.contains(".cp(") || line.contains(".cx(") || line.contains(".cu3(")
            || line.contains(".swap(") || line.contains(".ccx(") || line.contains(".cswap(")) {
                totalOC++;
            }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        }
        return totalOC;
    }
}
