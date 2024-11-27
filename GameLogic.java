
import java.util.ArrayList;
import java.util.List;

public class GameLogic implements PlayableLogic{
    private int whoseTurn = 0;
    private Move lastMove;
    @Override
    public boolean locate_disc(Position a, Disc disc) {
        if(!ValidMoves().contains(a)) return false;
        boolean ans = a.locateDisc(disc);
        if(ans){
            lastMove = new Move(this,a,disc);
            changeTurn();
            //lastMove.flip();
        }

        return ans;
    }

    @Override
    public Disc getDiscAtPosition(Position position) {
        return position.getDiscAtPosition();
    }

    @Override
    public int getBoardSize() {
        return 8;
    }

    @Override
    public List<Position> ValidMoves() {//for now, it's the naive algorithm עובר על כל המקומות, ובודק את כל השכנים שלהם וכו'.י
        ArrayList<Position> ans = new ArrayList<>();
        for(int i = 0;i<Position.notLocated.size();i++){//עובר על כל המיקומים הריקים בלוח
                Position optional = Position.notLocated.get(i);
                if(!optional.flippedDisc(currentPlayer()).isEmpty()){
                    ans.add(optional);
                }

        }
        return ans;
    }

    @Override
    public int countFlips(Position a) {//The same naive algorithm
        return a.flippedDisc(currentPlayer()).size();
    }

    private Player first;
    private Player second;

    @Override
    public Player getFirstPlayer() {
        return first;
    }

    @Override
    public Player getSecondPlayer() {
        return second;
    }

    @Override
    public void setPlayers(Player player1, Player player2) {
        this.first = player1;
        this.second = player2;
    }

    @Override
    public boolean isFirstPlayerTurn() {

        if(whoseTurn==0) return true;
        return false;
    }

    @Override
    public boolean isGameFinished() {
        boolean ans = ValidMoves().isEmpty();
        if(ans) notCurrentPlayer().addWin();
        return ans;
    }

    @Override
    public void reset() {
        Position.resetPositions(this);
        whoseTurn=0;
    }

    @Override
    public void undoLastMove() {
        if(first.isHuman()&&second.isHuman()){
            Move.undoMove(this);
            changeTurn();
        }
    }

    private void changeTurn(){
        whoseTurn=1-whoseTurn;
    }
    public Player currentPlayer(){
        if(whoseTurn==0)return getFirstPlayer();
        return getSecondPlayer();
    }

    public Player notCurrentPlayer(){
        if(whoseTurn==0) return getSecondPlayer();
        return getFirstPlayer();
    }

}
