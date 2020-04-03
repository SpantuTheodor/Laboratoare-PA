import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Board {

    private int tokenIndex;
    private int numberOfTokens;
    private List<Token> tokenList;
    private int maxTokenValue;

    public Board(int numberOfTokens, int maxTokenValue) {
        this.tokenIndex = tokenIndex;
        this.numberOfTokens = numberOfTokens;
        this.tokenList = new ArrayList<>(numberOfTokens);
        this.maxTokenValue = maxTokenValue;
    }

    public int getTokenIndex() {
        return tokenIndex;
    }

    public int getNumberOfTokens() {
        return numberOfTokens;
    }

    public List getTokenList() {
        return tokenList;
    }

    public void setTokenList(List tokenList) {
        this.tokenList = tokenList;
    }

    public int getMaxTokenValue() {
        return maxTokenValue;
    }

    void generateTokenList() {
        for (int index = 1; index <= numberOfTokens; index++) {
            tokenList.add(new Token(new Random().nextInt(maxTokenValue)));
        }
        this.tokenList = Collections.synchronizedList(tokenList);
    }

}
