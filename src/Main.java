import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException();
        }
        Map<Integer, State> states = new HashMap<>();
        try (Scanner scanner = new Scanner( new FileReader( args[0] ) )) {
            String string = scanner.nextLine();
            String[] finitStates = string.split( " " );
            while (scanner.hasNext()) {
                String currentStateNum = scanner.next();
                State curState = states.get( Integer.parseInt( currentStateNum ) );
                String symbol = scanner.next();
                String nextStateNum = scanner.next();
                if (curState == null) {
                    curState = new State();
                    states.put( Integer.parseInt( currentStateNum ),curState );
                }
                State nextState = states.get( Integer.parseInt( nextStateNum ) );
                if (nextState == null) {
                    nextState = new State();
                    states.put( Integer.parseInt( nextStateNum ), nextState );
                }
                curState.addTransition( symbol.charAt( 0 ), nextState );
            }
            for (String finitStateNum : finitStates) {
                State finitState = states.get( Integer.parseInt( finitStateNum ) );
                finitState.setFinite();
            }
        }
        try (BufferedReader reader = new BufferedReader( new FileReader( args[1] ) )) {
            int code;
            State currentState = states.get( 0 );
            while ((code = reader.read()) >= 0) {
                char symbol = (char) code;
                if((symbol>='A' && symbol<='Z')||(symbol>='a'&& symbol<='z')) {
                    currentState = currentState.getNextState( symbol );
                }
            }
            System.out.println( currentState.isFinite() );
        }
    }
}
