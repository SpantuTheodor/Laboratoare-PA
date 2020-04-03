import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements Runnable {

    private String name;
    private List<Token> tokenList;
    private Board board;

    public Player(String name) {
        this.name = name;
        this.tokenList = new ArrayList<>();
        this.tokenList = Collections.synchronizedList(tokenList);
    }

    public Player(String name, Board board) {
        this(name);
        this.board = board;
    }

    public synchronized Token extractToken() {
        List<Token> helperList = board.getTokenList();
        if (!helperList.isEmpty()) {
            Token tmp = (Token) helperList.get(0);
            helperList.remove(0);
            board.setTokenList(helperList);
            return tmp;
        }
        return null;
    }


    public String getName() {
        return name;
    }

    public List<Token> getTokenList() {
        return tokenList;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public synchronized void run() {
        Token newToken = extractToken();
        while (newToken != null) {
            System.out.println(this.name + " extracts: " + newToken.getValue());
            this.tokenList.add(newToken);
            newToken = extractToken();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
