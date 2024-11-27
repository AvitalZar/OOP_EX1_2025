
import java.util.List;

public class RandomAI extends AIPlayer{
    public RandomAI(boolean isPlayerOne) {
        super(isPlayerOne);
    }

    @Override
    public Move makeMove(PlayableLogic gameStatus) {

        List<Position> options = gameStatus.ValidMoves();
        int randomOptionInd = (int) (Math.random()*options.size());

        //להוסיף סוג דסקית.

        return new Move(gameStatus,options.get(randomOptionInd),new SimpleDisc(this));
    }
}
