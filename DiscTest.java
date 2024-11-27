
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiscTest {
    private Player p = new HumanPlayer(true);
    private Player l = new RandomAI(false);
    GameLogic game = new GameLogic();

    Disc dp = new SimpleDisc(p);
    Disc dl = new UnflippableDisc(l);
    Disc dl1 = new BombDisc(l);


    @Test
    public void getType() {
        System.out.println(dp.getType());
        assertEquals(dp.getType(),"⬤");
        System.out.println(dl.getType());
        assertEquals(dl.getType(),"⭕");
        System.out.println(dl1.getType());
        assertEquals(dl1.getType(),"\uD83D\uDCA3");
    }

    @Test
    public void setOwner(){
        dp.setOwner(l);
        assertEquals(dp.getOwner(),l);
        dl.setOwner(p);
        assertEquals(dl.getOwner(),l);
    }

    @Test
    public void getOwner(){
        assertEquals(dp.getOwner(),p);
    }

    /**@Test
    public void flip(){
        game.setPlayers(p,l);
        dp.flip(game);
        assertEquals(dp.getOwner(),l);
    }*/



}