import java.util.Scanner;

public class UniversalProgram {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String firstline = sc.nextLine();
        String[] instructionInputs = firstline.split(" ");

        Instruction[] instructions = new Instruction[instructionInputs.length];
        for (int i = 0 ; i < instructionInputs.length ; i++) {
            String input = instructionInputs[i];
            Instruction instruction = Decoder.findInstruction(Integer.parseInt(input));
//            Decoder.printInstruction(instruction);
            instructions[i] = instruction;
        }

        String secondLine = sc.nextLine();
        String[] variableInputs = secondLine.split(" ");

        Program program = new Program(instructions);
        program.execute(variableInputs);
    }
}
