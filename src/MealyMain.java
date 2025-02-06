import mealy_machine.Mealy;

import java.util.Scanner;

public class MealyMain {

    public static void main(String[] args) {

        // declare var to be used
        int states;
        String[] input_alpha, output_alpha;
        String[] init_states;
//        int[][] matrix;
        String input;

        // initialize classes to be used
        Scanner in = new Scanner(System.in);

        // >> Begin of Program
        System.out.print("Enter the number of states: ");
        states = in.nextInt();
        System.out.print("Enter the input alphabet (sep by comma): ");
        input_alpha = in.next().split(",");
        System.out.print("Enter the output alphabet (sep by comma): ");
        output_alpha = in.next().split(",");

//        for (String a: input_alpha) {
//            System.out.println(a);
//        }
        // Create matrix using states (row) and len of alpha (col)
//        matrix = new int[states][alpha.length];

        System.out.print("Enter the initial state/s (sep by comma): ");
        init_states = in.next().split(",");

        // Initialize Mealy Machine
        Mealy mealy = new Mealy(states, input_alpha, init_states, output_alpha);

        mealy.fill_matrix();

        mealy.display_matrix();

        boolean loopState = true;

        while (loopState) {
            System.out.print("Enter the input string: ");
            input = in.next();

            String output_string = mealy.get_output(input);

            System.out.println("Input " + input + " has an output of " + output_string);

            System.out.print("Do you want to try another string? <any key>/N: ");
            char choice = in.next().charAt(0);
            if (choice == 'n' || choice == 'N') {
                loopState = false;
            }
        }

        System.out.println("Program Finished!");

        // End of Program <<
    }
}
