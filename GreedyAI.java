
import java.util.List;

public class GreedyAI extends AIPlayer{
    public GreedyAI(boolean isPlayerOne) {
        super(isPlayerOne);
    }

    @Override
    public Move makeMove(PlayableLogic gameStatus) {

        List<Position> options = gameStatus.ValidMoves();
        int greedyOptionInd = 0;
        for(int i = 0;i<options.size();i++){
            int flips=gameStatus.countFlips(options.get(i));
            if(flips>greedyOptionInd){
                greedyOptionInd=i;
            }
        }

        return new Move(gameStatus,options.get(greedyOptionInd),new SimpleDisc(this));
    }
}
