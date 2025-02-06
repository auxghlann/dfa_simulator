import dfa.DFA;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        // declare var to be used
        int states;
        String[] alpha;
        String[] init_states, fin_states;
//        int[][] matrix;
        String input;

        // initialize classes to be used
        Scanner in = new Scanner(System.in);

        // >> Begin of Program
        System.out.print("Enter the number of states: ");
        states = in.nextInt();
        System.out.print("Enter the input alphabet (sep by comma): ");
        alpha = in.next().split(",");

        for (String a: alpha) {
            System.out.println(a);
        }
        // Create matrix using states (row) and len of alpha (col)
//        matrix = new int[states][alpha.length];

        System.out.print("Enter the initial state/s (sep by comma): ");
        init_states = in.next().split(",");
        System.out.print("Enter the final state/s (sep by comma): ");
        fin_states = in.next().split(",");

        DFA dfa_obj = new DFA(states, alpha, init_states, fin_states);

        dfa_obj.fill_matrix();

        dfa_obj.display_matrix();

        boolean loopState = true;

        while (loopState) {
            System.out.print("Enter the input string: ");
            input = in.next();

            boolean is_accepted = dfa_obj.validate_string(input);

            if (is_accepted) {
                System.out.println("Remarks: Accepted");
            }
            else {
                System.out.println("Remarks: Rejected");
            }

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