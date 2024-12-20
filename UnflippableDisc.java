
public class UnflippableDisc implements Disc{

    private final Player owner;
    private Position placedIn;

    public UnflippableDisc (Player currentPlayer){
        owner = currentPlayer;
    }
    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Player player) {
        System.out.println("Muwahahaha! you can't change my owner!");
    }
    @Override
    public void flip(GameLogic game){
        System.out.println("Unflippable disc declare: Don't EVER try to flip me again.");
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
        return "⭕";
    }
}
