
import java.util.ArrayList;
import java.util.List;

public class GameLogic implements PlayableLogic{
    private int whoseTurn = 0;

    @Override
    public boolean locate_disc(Position a, Disc disc) {
        if(!ValidMoves().contains(a)) return false;

        List<Disc> flippes = a.flippedDisc(currentPlayer());

        boolean ans = a.locateDisc(disc);
        if(ans){
            new Move(this,a,disc);//בשביל לעדכן את allMoves ולמקם את הדסקית
            System.out.println("Player "+(whoseTurn+1)+" placed a "+disc.getType()+" in "+a);
            a.printPositions(flippes);
            changeTurn();

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
        if(ans){
            Player p = winner();
            if(p!=null)
                p.addWin();
        }
        return ans;
    }

    @Override
    public void reset() {
        Position.resetPositions(this);
        Move.resetAllMoves();
        whoseTurn=0;
    }

    @Override
    public void undoLastMove() {
        if(first.isHuman()&&second.isHuman()){
            System.out.println("Undoing last move:");
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

    private Player winner(){
        int player1Disc = 0;
        int player2Disc = 0;
        for(int i = 0;i<getBoardSize();i++){
            for(int j = 0;j<getBoardSize();j++){
                Position here = new Position(i,j);
                Disc disc = getDiscAtPosition(here);
                if(disc!=null){
                    if(disc.getOwner().isPlayerOne()) player1Disc++;
                    else player2Disc++;
                }
            }
        }
        if(player1Disc<player2Disc){
            System.out.println("Player 2 wins with "+player2Disc+" discs! Player 1 had "+player1Disc+" discs.");
            return getSecondPlayer();
        }
        if(player2Disc<player1Disc){
            System.out.println("Player 1 wins with "+player1Disc+" discs! Player 2 had "+player2Disc+" discs.");
            return getFirstPlayer();
        }

        return null;
    }

}
