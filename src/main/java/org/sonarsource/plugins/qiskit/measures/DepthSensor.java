package org.sonarsource.plugins.qiskit.measures;

import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;

import static org.sonarsource.plugins.qiskit.measures.QiskitMetrics.DEPTH;

import java.io.IOException;

public class DepthSensor implements Sensor {
    
    @Override
    public void describe(SensorDescriptor descriptor) {
        descriptor.name("Calcular el número máximo de puertas sobre un cúbit");
    }

  @Override
    public void execute(SensorContext context) {
        
        FileSystem fs = context.fileSystem();
        Iterable<InputFile> files = fs.inputFiles(fs.predicates().hasType(InputFile.Type.MAIN));
        for (InputFile file : files) {
            try {
                context.<Integer>newMeasure()
                    .forMetric(DEPTH)
                    .on(file)
                    .withValue(calculate(file, context))
                    .save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int calculate(InputFile f, SensorContext context) throws IOException {
        int n = WidthSensor.calculate(f, context);
        int[] operaciones = new int[n];
        operaciones = contar(operaciones, f, context);
        int max = 0;
        for (int j=0; j<operaciones.length; j++) {
            if (operaciones[j] > max)
                max = operaciones[j];
        }
        return max;
    }

    public int[] contar(int[] operaciones, InputFile f, SensorContext context) {
        String linea, ope, part0, part1;
        String[] parts, parts2;
        int cubit;
        if (f.isFile()) {
            try {
                String content = f.contents();
                String[] lines = content.split("\\R");
                for (String line : lines) {
                    linea = line.replace(" ", "");
                    if ((linea.contains(".x(") || linea.contains(".y(") || linea.contains(".z(") ||
                    linea.contains(".id(") || linea.contains(".h(") || linea.contains(".s(") ||
                    linea.contains(".sdg(") || linea.contains(".t(") || linea.contains(".tdg("))
                    && !linea.contains(".c_if(")) {
                        parts = linea.split("\\(");
                        part0 = parts[1];
                        if(part0.contains("[") && part0.contains(",")) {
                            parts = part0.split("\\,");
                            for(int i=0; i<parts.length; i++) {
                                if(parts[i].contains("[")){
                                    parts2 = parts[i].split("\\[");
                                    if(parts2.length == 2){
                                        part1 = parts2[1];
                                    } else {
                                        part1 = parts2[2];
                                    }
                                    if(part1.contains("]")){
                                        parts2 = part1.split("\\]");
                                        ope = parts2[0];
                                    } else {
                                        ope = part1;
                                    }
                                } else if(parts[i].contains("]") && !parts[i].contains("[")) {
                                    parts2 = parts[i].split("\\]");
                                    ope = parts2[0];
                                } else {
                                    ope = parts[i];
                                }
                                cubit = Integer.valueOf(ope);
                                operaciones[cubit] = operaciones[cubit] + 1;
                            }
                        } else if(part0.contains("[") && !part0.contains(",")) {
                            parts2 = part0.split("\\[");
                            part1 = parts2[1];
                            parts2 = part1.split("\\]");
                            ope = parts2[0];
                            cubit = Integer.valueOf(ope);
                            operaciones[cubit] = operaciones[cubit] + 1;
                        } else if(linea.contains("range")) {
                            parts2 = linea.split("\\(");
                            parts2 = parts2[2].split("\\)");
                            ope = parts2[0];
                            if(ope.matches("[+-]?\\d*(\\.\\d+)?")){
                                cubit = Integer.valueOf(ope);
                            } else {
                                ope = buscar_cubits(lines, ope);
                                cubit = Integer.valueOf(ope);
                            }
                            for(int i=0; i<cubit; i++) {
                                operaciones[i] = operaciones[i] + 1;
                            }
                        } else {
                            parts2 = part0.split("\\)");
                            ope = parts2[0];
                            if(ope.matches("[+-]?\\d*(\\.\\d+)?")){
                                cubit = Integer.valueOf(ope);
                                operaciones[cubit] = operaciones[cubit] + 1;
                            } else {
                                ope = buscar_cubits(lines, ope);
                                cubit = Integer.valueOf(ope);
                                for(int i=0; i<cubit; i++){
                                    operaciones[i] = operaciones[i] + 1;
                                }
                            }
                        }
                    }
                    else if((linea.contains(".p(") || linea.contains(".rx(") || linea.contains(".ry(") ||
                    linea.contains(".rz(") || linea.contains(".cx(") || linea.contains(".cy(") ||
                    linea.contains(".cz(") || linea.contains(".ch(") || linea.contains(".cswap(")) &&
                    !linea.contains(".c_if(")) {
                        parts = linea.split("\\(");
                        part0 = parts[1];
                        parts = part0.split("\\,");
                        if(linea.contains("range")) {
                            parts2 = linea.split("\\(");
                            parts2 = parts2[2].split("\\)");
                            ope = parts2[0];
                            if(ope.matches("[+-]?\\d*(\\.\\d+)?")){
                                cubit = Integer.valueOf(ope);
                            } else {
                                ope = buscar_cubits(lines, ope);
                                cubit = Integer.valueOf(ope);
                            }
                            for(int i=0; i<cubit; i++) {
                                operaciones[i] = operaciones[i] + 1;
                            }
                        } else if(parts.length == 2){
                            if(parts[1].contains("[")){
                                parts2 = parts[1].split("\\[");
                                part1 = parts2[1];
                                parts2 = part1.split("\\]");
                                ope = parts2[0];
                            } else {
                                parts2 = parts[1].split("\\)");
                                ope = parts2[0];
                            }
                            if(ope.matches("[+-]?\\d*(\\.\\d+)?")){
                                cubit = Integer.valueOf(ope);
                                operaciones[cubit] = operaciones[cubit] + 1;
                            } else {
                                ope = buscar_cubits(lines, ope);
                                cubit = Integer.valueOf(ope);
                                for(int i=0; i<cubit; i++){
                                    operaciones[i] = operaciones[i] + 1;
                                }
                            }
                        } else {
                            for(int i=1; i<parts.length; i++) {
                                if(parts[i].contains("[")){
                                    parts2 = parts[i].split("\\[");
                                    if(parts2.length == 2){
                                        part1 = parts2[1];
                                    } else {
                                        part1 = parts2[2];
                                    }
                                    if(part1.contains("]")){
                                        parts2 = part1.split("\\]");
                                        ope = parts2[0];
                                    } else {
                                        ope = part1;
                                    }
                                } else if(parts[i].contains("]") && !parts[i].contains("[")) {
                                    parts2 = parts[i].split("\\]");
                                    ope = parts2[0];
                                } else {
                                    ope = parts[i];
                                }
                                cubit = Integer.valueOf(ope);
                                operaciones[cubit] = operaciones[cubit] + 1;
                            }
                        }
                    }
                    else if ((linea.contains(".crz(") || linea.contains(".ccx(") || linea.contains(".cp("))
                    && !linea.contains(".c_if(")) {
                        parts = linea.split("\\(");
                        part0 = parts[1];
                        parts = part0.split("\\,");
                        if(linea.contains("range")) {
                            parts2 = linea.split("\\(");
                            parts2 = parts2[2].split("\\)");
                            ope = parts2[0];
                            if(ope.matches("[+-]?\\d*(\\.\\d+)?")){
                                cubit = Integer.valueOf(ope);
                            } else {
                                ope = buscar_cubits(lines, ope);
                                cubit = Integer.valueOf(ope);
                            }
                            for(int i=0; i<cubit; i++) {
                                operaciones[i] = operaciones[i] + 1;
                            }
                        } else if(parts.length == 3){
                            if(parts[2].contains("[")){
                                parts2 = parts[2].split("\\[");
                                part1 = parts2[1];
                                parts2 = part1.split("\\]");
                                ope = parts2[0];
                            } else {
                                parts2 = parts[2].split("\\)");
                                ope = parts2[0];
                            }
                            if(ope.matches("[+-]?\\d*(\\.\\d+)?")){
                                cubit = Integer.valueOf(ope);
                                operaciones[cubit] = operaciones[cubit] + 1;
                            } else {
                                ope = buscar_cubits(lines, ope);
                                cubit = Integer.valueOf(ope);
                                for(int i=0; i<cubit; i++){
                                    operaciones[i] = operaciones[i] + 1;
                                }
                            }
                        } else {
                            for(int i=2; i<parts.length; i++) {
                                if(parts[i].contains("[")){
                                    parts2 = parts[i].split("\\[");
                                    if(parts2.length == 2){
                                        part1 = parts2[1];
                                    } else {
                                        part1 = parts2[2];
                                    }
                                    if(part1.contains("]")){
                                        parts2 = part1.split("\\]");
                                        ope = parts2[0];
                                    } else {
                                        ope = part1;
                                    }
                                } else if(parts[i].contains("]") && !parts[i].contains("[")) {
                                    parts2 = parts[i].split("\\]");
                                    ope = parts2[0];
                                } else {
                                    ope = parts[i];
                                }
                                cubit = Integer.valueOf(ope);
                                operaciones[cubit] = operaciones[cubit] + 1;
                            }
                        }
                    }
                    else if (linea.contains(".u(") && !linea.contains(".c_if(")) {
                        parts = linea.split("\\(");
                        part0 = parts[1];
                        parts = part0.split("\\,");
                        if(linea.contains("range")) {
                            parts2 = linea.split("\\(");
                            parts2 = parts2[2].split("\\)");
                            ope = parts2[0];
                            if(ope.matches("[+-]?\\d*(\\.\\d+)?")){
                                cubit = Integer.valueOf(ope);
                            } else {
                                ope = buscar_cubits(lines, ope);
                                cubit = Integer.valueOf(ope);
                            }
                            for(int i=0; i<cubit; i++) {
                                operaciones[i] = operaciones[i] + 1;
                            }
                        } else if(parts.length == 4){
                            if(parts[3].contains("[")){
                                parts2 = parts[3].split("\\[");
                                part1 = parts2[1];
                                parts2 = part1.split("\\]");
                                ope = parts2[0];
                            } else {
                                parts2 = parts[3].split("\\)");
                                ope = parts2[0];
                            }
                            if(ope.matches("[+-]?\\d*(\\.\\d+)?")){
                                cubit = Integer.valueOf(ope);
                                operaciones[cubit] = operaciones[cubit] + 1;
                            } else {
                                ope = buscar_cubits(lines, ope);
                                cubit = Integer.valueOf(ope);
                                for(int i=0; i<cubit; i++){
                                    operaciones[i] = operaciones[i] + 1;
                                }
                            }
                        } else {
                            for(int i=3; i<parts.length; i++) {
                                if(parts[i].contains("[")){
                                    parts2 = parts[i].split("\\[");
                                    if(parts2.length == 2){
                                        part1 = parts2[1];
                                    } else {
                                        part1 = parts2[2];
                                    }
                                    if(part1.contains("]")){
                                        parts2 = part1.split("\\]");
                                        ope = parts2[0];
                                    } else {
                                        ope = part1;
                                    }
                                } else if(parts[i].contains("]") && !parts[i].contains("[")) {
                                    parts2 = parts[i].split("\\]");
                                    ope = parts2[0];
                                } else {
                                    ope = parts[i];
                                }
                                cubit = Integer.valueOf(ope);
                                operaciones[cubit] = operaciones[cubit] + 1;
                            }
                        }
                    }
                    else if ((linea.contains(".cu(")) && !linea.contains(".c_if(")) {
                        parts = linea.split("\\(");
                        part0 = parts[1];
                        parts = part0.split("\\,");
                        if(linea.contains("range")) {
                            parts2 = linea.split("\\(");
                            parts2 = parts2[2].split("\\)");
                            ope = parts2[0];
                            if(ope.matches("[+-]?\\d*(\\.\\d+)?")){
                                cubit = Integer.valueOf(ope);
                            } else {
                                ope = buscar_cubits(lines, ope);
                                cubit = Integer.valueOf(ope);
                            }
                            for(int i=0; i<cubit; i++) {
                                operaciones[i] = operaciones[i] + 1;
                            }
                        } else if(parts.length == 6){
                            if(parts[5].contains("[")){
                                parts2 = parts[5].split("\\[");
                                part1 = parts2[1];
                                parts2 = part1.split("\\]");
                                ope = parts2[0];
                            } else {
                                parts2 = parts[5].split("\\)");
                                ope = parts2[0];
                            }
                            if(ope.matches("[+-]?\\d*(\\.\\d+)?")){
                                cubit = Integer.valueOf(ope);
                                operaciones[cubit] = operaciones[cubit] + 1;
                            } else {
                                ope = buscar_cubits(lines, ope);
                                cubit = Integer.valueOf(ope);
                                for(int i=0; i<cubit; i++){
                                    operaciones[i] = operaciones[i] + 1;
                                }
                            }
                        } else {
                            for(int i=5; i<parts.length-1; i++) {
                                if(parts[i].contains("[")){
                                    parts2 = parts[i].split("\\[");
                                    if(parts2.length == 2){
                                        part1 = parts2[1];
                                    } else {
                                        part1 = parts2[2];
                                    }
                                    if(part1.contains("]")){
                                        parts2 = part1.split("\\]");
                                        ope = parts2[0];
                                    } else {
                                        ope = part1;
                                    }
                                } else if(parts[i].contains("]") && !parts[i].contains("[")) {
                                    parts2 = parts[i].split("\\]");
                                    ope = parts2[0];
                                } else {
                                    ope = parts[i];
                                }
                                cubit = Integer.valueOf(ope);
                                operaciones[cubit] = operaciones[cubit] + 1;
                            }
                        }
                    }
                    else if ((linea.contains(".swap(")) && !linea.contains(".c_if(")) {
                        parts = linea.split("\\(");
                        part0 = parts[1];
                        if(linea.contains("range")) {
                            parts2 = linea.split("\\(");
                            parts2 = parts2[2].split("\\)");
                            ope = parts2[0];
                            if(ope.matches("[+-]?\\d*(\\.\\d+)?")){
                                cubit = Integer.valueOf(ope);
                            } else {
                                ope = buscar_cubits(lines, ope);
                                cubit = Integer.valueOf(ope);
                            }
                            for(int i=0; i<cubit; i++) {
                                operaciones[i] = operaciones[i] + 1;
                            }
                        } else{
                            parts = part0.split("\\,");
                            for(int i=0; i<parts.length; i++){
                                if(parts[i].contains("[")){
                                    parts2 = parts[i].split("\\[");
                                    if(parts2.length == 2){
                                        part1 = parts2[1];
                                    }else{
                                        part1 = parts2[2];
                                    }
                                    if(part1.contains("]")){
                                        parts2 = part1.split("\\]");
                                        ope = parts2[0];
                                    }else{
                                        ope = part1;
                                    }
                                    cubit = Integer.valueOf(ope);
                                    operaciones[cubit] = operaciones[cubit] + 1;
                                }
                                else if(parts[i].contains("]") && !parts[i].contains("[")){
                                    parts2 = parts[i].split("\\]");
                                    ope = parts2[0];
                                    cubit = Integer.valueOf(ope);
                                    operaciones[cubit] = operaciones[cubit] + 1;
                                } else{
                                    if(parts[i].contains(")")){
                                        parts2 = parts[i].split("\\)");
                                        ope = parts2[0];
                                    } else {
                                        ope = parts[i];
                                    }
                                    if(ope.matches("[+-]?\\d*(\\.\\d+)?")){
                                        cubit = Integer.valueOf(ope);
                                        operaciones[cubit] = operaciones[cubit] + 1;
                                    } else {
                                        ope = buscar_cubits(lines, ope);
                                        cubit = Integer.valueOf(ope);
                                        for(int j=0; j<cubit; j++){
                                            operaciones[j] = operaciones[j] + 1;
                                        }
                                    }
                                }
                            }
                        }                        
                    }
                    else if ((line.contains(".cu3(")) && !line.contains(".c_if(")) {
                        parts = linea.split("\\(");
                        part0 = parts[1];
                        parts = part0.split("\\,");
                        if(linea.contains("range")) {
                            parts2 = linea.split("\\(");
                            parts2 = parts2[2].split("\\)");
                            ope = parts2[0];
                            if(ope.matches("[+-]?\\d*(\\.\\d+)?")){
                                cubit = Integer.valueOf(ope);
                            } else {
                                ope = buscar_cubits(lines, ope);
                                cubit = Integer.valueOf(ope);
                            }
                            for(int i=0; i<cubit; i++) {
                                operaciones[i] = operaciones[i] + 1;
                            }
                        } else if(!part0.contains(",")){
                            parts2 = part0.split("\\)");
                            if(!parts2[0].matches("[+-]?\\d*(\\.\\d+)?")){
                                ope = buscar_cubits(lines, parts2[0]);
                                cubit = Integer.valueOf(ope);
                                for(int i=0; i<cubit; i++){
                                    operaciones[i] = operaciones[i] + 1;
                                }
                            }
                        } else if(parts.length == 5){
                            if(parts[4].contains("[")){
                                parts2 = parts[4].split("\\[");
                                part1 = parts2[1];
                                parts2 = part1.split("\\]");
                                ope = parts2[0];
                            } else {
                                parts2 = parts[4].split("\\)");
                                ope = parts2[0];
                            }
                            cubit = Integer.valueOf(ope);
                            operaciones[cubit] = operaciones[cubit] + 1;
                        } else {
                            for(int i=4; i<parts.length; i++) {
                                if(parts[i].contains("[")){
                                    parts2 = parts[i].split("\\[");
                                    if(parts2.length == 2){
                                        part1 = parts2[1];
                                    } else {
                                        part1 = parts2[2];
                                    }
                                    if(part1.contains("]")){
                                        parts2 = part1.split("\\]");
                                        ope = parts2[0];
                                    } else {
                                        ope = part1;
                                    }
                                } else if(parts[i].contains("]") && !parts[i].contains("[")) {
                                    parts2 = parts[i].split("\\]");
                                    ope = parts2[0];
                                } else {
                                    ope = parts[i];
                                }
                                cubit = Integer.valueOf(ope);
                                operaciones[cubit] = operaciones[cubit] + 1;
                            }
                        }
                    }
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
              }
        }
        return operaciones;
    }
    public static String buscar_cubits(String[] lines, String cubits) {
        for (String line : lines) {
            if (line.contains(cubits) && line.contains("=")) {
                String build_cubits = line.replace(" ", "");
                if(build_cubits.contains("QuantumRegister")) {
                    String[] parts = build_cubits.split("\\(");
                    String part1 = parts[1];
                    if (part1.contains(",")) {
                        parts = part1.split("\\,");
                        cubits = parts[0];                        
                    } else {
                        parts = part1.split("\\)");
                        cubits = parts[0];
                    }
                } else {
                    String[] parts = build_cubits.split("\\=");
                    cubits = parts[1];
                }
                break;
            }
        }
        return cubits;
    }
}
  