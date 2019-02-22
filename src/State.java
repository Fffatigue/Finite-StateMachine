import java.util.HashMap;
import java.util.Map;

public class State {
    private Boolean finite = false;
    private Map<Character, State> transitions = new HashMap<>(  );

    public void addTransition(Character symbol, State state){
        transitions.put( symbol,state );
    }

    public void setFinite(){
        finite = true;
    }

    public boolean isFinite(){
        return finite;
    }

    public State getNextState(Character symbol) {
        return transitions.get( symbol );
    }
}
