
public class SimpleDisc implements Disc{

    private Player owner;
    private Position placedIn;

    public SimpleDisc (Player current){
        owner=current;
    }
    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Player player) {
        owner = player;
    }

    @Override
    public void flip(GameLogic game){
        if(owner.isPlayerOne) setOwner(game.getSecondPlayer());
        else setOwner(game.getFirstPlayer());
    }

    @Override
    public void place(Position p) {
        placedIn = p;
    }

    @Override
    public Position position() {
        return placedIn;
    }

    @Override
    public String getType() {
        return "⬤";
    }
}
