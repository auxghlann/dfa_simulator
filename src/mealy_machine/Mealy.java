package mealy_machine;
import dfa.DFA;

import java.util.Scanner;

public class Mealy extends DFA {

    private final String[] output_alpha;
    private String[][] output_matrix;
//    private ArrayList<ArrayList<String>> list_of_outputs;

    public Mealy(int states, String[] input_alpha,
                    String[] output_alpha) {
        super(states, input_alpha);
        this.output_alpha = output_alpha;
    }

    @Override
    public void fill_matrix() {
//        this.list_of_outputs = new ArrayList<>();
//        boolean is_output = false;

        //Matrices
        super.matrix = new int[super.states][super.input_alpha.length];
        this.output_matrix = new String[super.states][this.output_alpha.length];

        Scanner in = new Scanner(System.in);

        System.out.println("Row: "+ super.matrix.length);
        System.out.println("Col: " + super.matrix[0].length);


        System.out.println("-- Enter the transition and its respective output -- ");
        for (int i = 0; i < super.matrix.length; i++) {
//            ArrayList<String> outputs = new ArrayList<>();

            for (int j = 0; j < super.matrix[1].length; j++) {
                System.out.print("Transition (q" +  i + ","+ super.input_alpha[j] + "): ");
                super.matrix[i][j] = in.nextInt();
                System.out.print("Output: ");
                String output = in.next();
//                for (int k = 0; k < output_alpha.length; k++) {
//                    if (output.equals(output_alpha[k])) {
//                        is_output = true;
//                    }
//                }
                this.output_matrix[i][j] = output;
            }
//            this.list_of_outputs.add(outputs);
        }
        // >> Debug
//        System.out.println(this.matrix.length);
//        System.out.println(this.matrix[0].length);
//
//
//        for (int i = 0; i < this.output_matrix.length; i++) {
//            for (int j = 0; j < this.output_matrix[0].length; j++) {
//                System.out.print(output_matrix[i][j] + " ");
//            }
//            System.out.println();
//        }

        // <<


    }

    public String get_output(String input) {


        int row_pointer = 0;
        StringBuilder output_builder = new StringBuilder();
        int[] converted_input = super.convert_input_to_numeric(input);

        // >> Debug
//        for (int i = 0; i < converted_input.length; i++) {
//            System.out.print(converted_input[i] + " ");
//        }
//
//        System.out.println();

        // <<

        for (int i = 0; i < converted_input.length; i++) {
            int curr_row_pointer = row_pointer;
            row_pointer = super.matrix[row_pointer][converted_input[i]];
            output_builder.append(this.output_matrix[curr_row_pointer][converted_input[i]]);
        }

        return output_builder.toString();
    }

    @Override
    public void display_matrix() {
        if (matrix == null) {
            throw new NullPointerException("Mealy Machine matrix should not be empty!");
        }

        System.out.print("states ");
        for (int i = 0; i < this.input_alpha.length; i++) {
            String a = this.input_alpha[i];
            System.out.print("   " + a + "   ");
        }
        System.out.println();


        int counter = 0;
        for (int i = 0; i < this.matrix.length; i++) {
            int[] transitions = this.matrix[i];
            String[] outputs = this.output_matrix[i];

            // Check for initial states for displaying
            if (super.check_if_initial_state(String.valueOf(counter))) {
                System.out.print(" -q" + counter + "  |");
            }  else {
                System.out.print("  q" + counter + "  |");
            }

            for (int j = 0; j < this.matrix[0].length; j++) { // Use matrix[0].length for consistency
                System.out.print(" q" + transitions[j] + "," + outputs[j] + " |");
            }
            System.out.println();
            counter++;
        }
    }



}
