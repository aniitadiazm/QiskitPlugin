package main.java.org.sonarsource.plugins.qiskit.measures.Util_Depth;


public class a_b_c_q_q {

    public static int[] calculate(int[] depths, String linea, String[] lines) {

        String part0, part1;
        String[] parts, parts2;
        int max=0;

        parts = linea.split("\\(");
        part0 = parts[1];
        if(part0.contains("[")) {
            parts = part0.split("\\,");
            for(int i=3; i<parts.length; i++) {
                parts2 = parts[i].split("\\[");
                parts2 = parts2[1].split("\\]");
                if(max <= depths[Integer.valueOf(parts2[0])]){
                    max = depths[Integer.valueOf(parts2[0])];
                }
            }
            for(int i=3; i<parts.length; i++) {
                parts2 = parts[i].split("\\[");
                parts2 = parts2[1].split("\\]");
                depths[Integer.valueOf(parts2[0])] = max + 1;
            }                             
        } else {
            parts = part0.split("\\)");
            part1 = parts[0];
            parts = part1.split("\\,");
            if(depths[Integer.valueOf(parts[3])] >= depths[Integer.valueOf(parts[4])]){
                max = depths[Integer.valueOf(parts[3])];
            } else{
                max = depths[Integer.valueOf(parts[4])];
            }
            depths[Integer.valueOf(parts[3])] = max + 1;
            depths[Integer.valueOf(parts[4])] = max + 1;
        }
        return depths;
    }
}