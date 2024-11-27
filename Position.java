
import java.util.ArrayList;
import java.util.List;

public class Position {
    /**
     * המחלקה למעשה מנהלת את המיקומים בלוח.
     * היא שומרת 64 מיקומים וזוכרת אם יש בהם דיסק או לא.
     * בכל יצירה של מיקום המחלקה מעדכנת את ה"לוח".
     */
    private final int row;
    private final int column;
    public static Position[][] board = new Position[8][8];
    private Disc discAtPosition;
    public static List<Position> notLocated = new ArrayList<>();//List of all the not-disced positions.
    public static List<List<Disc>> allastFlips = new ArrayList<>();


    public Position (int row,int column){
            this.row = row;
            this.column = column;
            if(board[row][column]==null){
                board[row][column] = this;
            } else {
                discAtPosition = board[row][column].discAtPosition;
            }

    }

    public int row(){
        return row;
    }

    public int col(){
        return column;
    }
    public boolean locateDisc(Disc newDisc){
        if(getDiscAtPosition()==null){
            flip(newDisc.getOwner());
            board[row][column].discAtPosition = newDisc;
            discAtPosition=newDisc;
            notLocated.remove(board[row()][col()]);
            return true;
        }
        return false;
    }

    public Disc getDiscAtPosition() {
        return board[row()][col()].discAtPosition;
    }
    public static void resetPositions(GameLogic game){
        int boardSize = game.getBoardSize();
        board = new Position[boardSize][boardSize];
        resetNotLocated();
        int m = boardSize/2-1;
        board[m][m] = new Position(m,m);
        board[m][m].locateDisc(new SimpleDisc(game.getFirstPlayer()));

        board[m+1][m+1] = new Position(m+1,m+1);
        board[m+1][m+1].locateDisc(new SimpleDisc(game.getFirstPlayer()));

        board[m][m+1] = new Position(m,m+1);
        board[m][m+1].locateDisc(new SimpleDisc(game.getSecondPlayer()));

        board[m+1][m] = new Position(m+1,m);
        board[m+1][m].locateDisc(new SimpleDisc(game.getSecondPlayer()));

    }

    private static void resetNotLocated(){
        for(int i = 0;i<board.length;i++){
            for(int j = 0; j<board.length;j++){
                notLocated.add(new Position(i,j));
            }
        }
    }


    public List<Disc> flippedDisc(Player p){
        ArrayList<Disc> ans = new ArrayList<>();
        if(this.getDiscAtPosition()==null) {

            List<Position> around = aroundEnemyPositions(p);
            for(int i = 0; i<around.size();i++) {
                Position cur = around.get(i);
                ans.addAll(discSequence(p,cur.row()-row(),cur.col()-col()));
            }
        }
        System.out.println(ans.size());
        return ans;
    }

    private void flip (Player flipForMe){
        List<Disc> toFlip = flippedDisc(flipForMe);
        for(Disc disc: toFlip){
            disc.setOwner(flipForMe);
        }
    }

    public void removeDisc(){
        board[row()][col()].discAtPosition = null;
    }


    @Override
    public boolean equals(Object o)
    {
        if(o==null || !(o instanceof Position)) {return false;}
        Position p = (Position) o;
        return p.row()==this.row()&&p.col()==this.col()&&p.getDiscAtPosition()==this.getDiscAtPosition();

    }
    private List<Position> aroundEnemyPositions(Player p) {
        ArrayList<Position> ans = new ArrayList<>();
        for (int r = row() - 1; r <= row() + 1; r++) {
            if (r < 0 || r >= board.length) continue;
            for (int c = col() - 1; c <= col() + 1; c++) {
                if (c < 0 || c >= board.length) continue;
                if(r==row()&&c==col()) continue;
                Position option = new Position(r,c);
                if(option.getDiscAtPosition()!=null&&option.getDiscAtPosition().getOwner().isPlayerOne() !=p.isPlayerOne) {
                    ans.add(option);
                }
            }
        }

        return ans;
    }

    /**
     * calculates enemy-discs sequence and return it if it finished with player's disc
     * @param rowDir row direction - 1 for down, -1 for up
     * @param colDir column direction - 1 for right, -1 for left
     * @return list of sequence's discs.
     */
    private List<Disc> discSequence(Player p, int rowDir, int colDir){
        List<Disc> ans = new ArrayList<>();

        int r = row()+rowDir;
        int c = col()+colDir;
        int numOfDiscs = 0;
        while (r > 0 && r < board.length && c > 0 && c < board.length) {//כל עוד אנחנו בתחום הלוח
            Position check = new Position(r, c);
            if (check.getDiscAtPosition() == null) break;//אם הגענו למקום ריק נעבור הלאה
            if (check.getDiscAtPosition().getOwner().isPlayerOne == p.isPlayerOne) {//רק אם הגענו לדסקית שלנו

                for (; numOfDiscs > 0; numOfDiscs--) {//תחזור אחורה ברצף ותוסיף לרשימה
                    r = r - rowDir;
                    c = c - colDir;
                    ans.add(new Position(r, c).getDiscAtPosition());//אם הגענו לדסקית שלנו נוסיף את הדסקיות
                }
                break;
            }
            r = r + rowDir;//נקדם את K וL
            c = c + colDir;
            numOfDiscs++;
        }

        return ans;
    }

    @Override
    public String toString(){
        return row()+","+col();
    }

}
