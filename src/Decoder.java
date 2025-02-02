import java.util.Scanner;

public class Decoder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        String[] inputs = line.split(" ");

        for (String input : inputs) {
            Instruction instruction = findInstruction(Integer.parseInt(input));
            printInstruction(instruction);
        }
    }

    public static Instruction findInstruction(int godelNumber) {
        int[] decoded = findLeftAndRight(godelNumber);
        String label = findLabel(decoded[0]);
        int[] rightDecoded = findLeftAndRight(decoded[1]);
        String variable = findCommandVariable(rightDecoded[1]);
        return new Instruction(rightDecoded[0], variable, label);
    }

    public static void printInstruction(Instruction instruction) {
        String output = "";
        if (!instruction.label.equals("")) {
            output += "[" + instruction.label + "] ";
        }
        output += findInstructionString(instruction);
        System.out.println(output);
    }

    public static String findInstructionString(Instruction instruction) {
        if (instruction.command == 0) {
            return instruction.variable + " <- " + instruction.variable;
        } else if (instruction.command == 1) {
            return instruction.variable + " <- " + instruction.variable + " + " + 1;
        } else if (instruction.command == 2) {
            return instruction.variable + " <- " + instruction.variable + " - " + 1;
        } else {
            return "IF " + instruction.variable + " != 0 GOTO " + instruction.gotoLabel;
        }
    }

    public static String findCommandVariable(int number) {
        if (number == 0) {
            return "Y";
        } else if (number % 2 == 1) {
            return "X" + (number / 2 + 1);
        } else if (number % 2 == 0) {
            return "Z" + (number / 2);
        }
        return "";
    }

    public static String findLabel(int number) {
        if (number == 0) {
            return "";
        }
        switch (number % 5) {
            case 1 -> {
                return "A" + ((number / 5) + 1);
            }
            case 2 -> {
                return "B" + ((number / 5) + 1);
            }
            case 3 -> {
                return "C" + ((number / 5) + 1);
            }
            case 4 -> {
                return "D" + ((number / 5) + 1);
            }
            case 0 -> {
                return "E" + ((number / 5));
            }
        }
        return "";
    }

    public static int[] findLeftAndRight(int input) {
        int main = input + 1;
        int x = 0;
        while (main % 2 == 0) {
            x++;
            main = main / 2;
        }
        int y = (main - 1) / 2;
        int[] response = new int[2];
        response[0] = x;
        response[1] = y;
        return response;
    }
}