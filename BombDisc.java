public class BombDisc implements Disc{
    private Player owner;
    private Position placedIn;

    public BombDisc(Player current){
        owner = current;
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
        if(!owner.isPlayerOne) setOwner(game.getFirstPlayer());
    }
    @Override
    public void place(Position p){
        placedIn = p;
    }

    @Override
    public Position position() {
        return placedIn;
    }

    @Override
    public String getType() {
        return "\uD83D\uDCA3";
    }
}
