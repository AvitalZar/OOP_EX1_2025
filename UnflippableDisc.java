
public class UnflippableDisc implements Disc{

    private Player owner;

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
    /**@Override
    public void flip(GameLogic game){
        System.out.println("Don't EVER try to flip me again.");
    }*/

    @Override
    public String getType() {
        return "⭕";
    }
}
