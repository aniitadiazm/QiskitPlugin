package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import java.io.IOException;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.NºS;

public class NºSsensor implements Sensor {

    @Override
    public void describe(SensorDescriptor descriptor) {
        descriptor.name("Calcular el número de puertas S");
    }

    @Override
    public void execute(SensorContext context) {
        FileSystem fs = context.fileSystem();
        Iterable<InputFile> files = fs.inputFiles(fs.predicates().hasType(InputFile.Type.MAIN));
        for (InputFile file : files) {
        try {
            context.<Integer>newMeasure()
                .forMetric(NºS)
                .on(file)
                .withValue(calculate(file, context))
                .save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }

    public int calculate(InputFile f, SensorContext context) throws IOException {
        int totalS = 0;
        if (f.isFile()) {

        try {
            String content = f.contents();
            String[] lines = content.split("\\R");
            for (String line : lines) {
            if (line.contains(".s(")) {
                totalS++;
            }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        }
        return totalS;
    }
}
