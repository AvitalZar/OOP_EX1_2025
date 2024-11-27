
import java.util.ArrayList;
import java.util.List;

public class Move {

    private Disc disc;
    private Position pose;
    private GameLogic gameLogic;
    private static List<Move> allMoves = new ArrayList<>();
    public Move(PlayableLogic game, Position position, Disc disc){
        this.disc = disc;
        this.pose = position;
        this.gameLogic = (GameLogic) game;
        allMoves.add(this);

    }

    public Position position() {
        return pose;
    }
    public Disc disc(){
        return disc;
    }


    /**
     * flip all the discs in "flipped disc" - the List of
     * the discs will be flip if the move would be done.

    public void flip() {
        List<Disc> toFlip = position().flippedDisc(gameLogic.currentPlayer());
        for (Disc value : toFlip) {
            value.flip(gameLogic);
        }
    }*/

    public void undoMove(){//המחלקה צריכה לשמור את כל המשחק! אפשר לחזור כמה שרוצים
        pose.removeDisc();
        allMoves.removeLast();
        List<Disc> toReflipp = Position.allastFlips.getLast();
        Position.allastFlips.removeLast();
        for(Disc value: toReflipp){
            value.setOwner(gameLogic.notCurrentPlayer());
        }
    }


}
