import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CPULauncher {

    public void init() throws IOException {
        ArrayList<String> program = readAssemblyProgram("src/program.txt");
        createInstructions(program);
        CPU.getCPU().run();
    }

    public void createInstructions(ArrayList<String> program){
        for (String s : program) {
            String[] instruction = s.split(" ");

            int opcode = getOPCode(instruction[0]);
            int reg1 = Integer.parseInt(instruction[1].substring(1));
            int field3 = instruction[2].charAt(0) == 'R' ? Integer.parseInt(instruction[2].substring(1)) : Integer.parseInt(instruction[2]);

            StringBuilder binaryInstruction = new StringBuilder(), sb1 = new StringBuilder(), sb2 = new StringBuilder(), sb3 = new StringBuilder();

            sb1.append(Integer.toBinaryString(opcode));
            sb1.reverse();
            while (sb1.length() < 4)
                sb1.append("0");
            sb1.reverse();

            sb2.append(Integer.toBinaryString(reg1));
            sb2.reverse();
            while (sb2.length() < 6)
                sb2.append("0");
            sb2.reverse();

            sb3.append(Integer.toBinaryString(field3));
            if(sb3.length() == 32)
                sb3 = new StringBuilder(sb3.substring(26));
            else{
                sb3.reverse();
                while (sb3.length() < 6)
                    sb3.append("0");
                sb3.reverse();
            }

            binaryInstruction.append(sb1).append(sb2).append(sb3);

            Instruction i = new Instruction(binaryInstruction.toString());
            InstructionMemory.getInstructionMemory().add(i);
        }
    }

    public int getOPCode(String insName){
        switch (insName){
            case "ADD": return 0;
            case "SUB": return 1;
            case "MUL": return 2;
            case "AND": return 5;
            case "OR": return 6;
            case "JR": return 7;
            case "LDI": return 3;
            case "BEQZ": return 4;
            case "SLC": return 8;
            case "SRC": return 9;
            case "LB": return 10;
            case "SB": return 11;
            default: return -1;
        }
    }

    public ArrayList<String> readAssemblyProgram(String path) throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String s;
        ArrayList<String> program = new ArrayList<>();
        while((s = br.readLine()) != null)
            program.add(s);
        return program;
    }

    public static void main(String[] args) throws IOException {
        CPULauncher launcher = new CPULauncher();
        launcher.init();
    }
}
