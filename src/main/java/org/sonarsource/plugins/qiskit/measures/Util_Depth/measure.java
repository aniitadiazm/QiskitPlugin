package main.java.org.sonarsource.plugins.qiskit.measures.Util_Depth;


public class measure {

    public static int[] calculate(int[] depths, String linea, String[] lines) {

        String part0, part1;
        String[] parts, parts2;
        int qubits = 0;

        parts = linea.split("\\(");
        part0 = parts[1];
        if(part0.contains("[")) {
            parts = part0.split("\\]");
            parts2 = parts[0].split("\\[");
            qubits = Integer.valueOf(parts2[1]);
            depths[qubits] = depths[qubits] + 1;
                                      
        }
        else {
            parts = part0.split("\\)");
            part1 = parts[0];
            parts = part1.split("\\,");
            if(parts[0].matches("[+-]?\\d*(\\.\\d+)?")){
                qubits = Integer.valueOf(parts[0]);
                depths[qubits] = depths[qubits] + 1;
            }
            else{
                qubits = Integer.valueOf(buscar_qubits.qubits(lines, parts[0]));
                for(int i=0; i<qubits; i++){
                    depths[i] = depths[i] + 1;
                }
            }   
        }
        return depths;
    }
}