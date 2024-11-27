
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

class PositionTest {
    @org.junit.jupiter.api.Test

    void nana(){//I just checked the constructor.
        p.col();
    }
    Position p = new Position(3,5);
    Position a = new Position(3,4);
    Position p2 = new Position(3,5);
    Player stamEchad = new GreedyAI(true);
    Player stamOdEchad = new HumanPlayer(false);
    Disc x = new SimpleDisc(stamEchad);
    @org.junit.jupiter.api.Test
    void getDisc(){
        p.getDiscAtPosition();
        assertNull(p.getDiscAtPosition());
    }
    @org.junit.jupiter.api.Test
    void constructor(){
        assertNotNull(Position.board[p.row()][p.col()]);
        p.locateDisc(x);
        Assert.assertNotNull(p2.getDiscAtPosition());
        assertEquals(x,p2.getDiscAtPosition());

    }
    @org.junit.jupiter.api.Test
    void locateAndGetDisc(){
        p.locateDisc(x);
        assertEquals(x,p.getDiscAtPosition());
    }


    @org.junit.jupiter.api.Test
    void flippedDisc(){
        GameLogic g = new GameLogic();
        g.setPlayers(stamEchad,stamOdEchad);
        Position.resetPositions(g);
        p = new Position(3,5);
        assertNotNull(Position.board[p.row()][p.col()]);
        assertEquals(p.flippedDisc(stamEchad).size(),1);
        assertEquals(p.flippedDisc(stamEchad).getFirst(),a.getDiscAtPosition());
    }
}