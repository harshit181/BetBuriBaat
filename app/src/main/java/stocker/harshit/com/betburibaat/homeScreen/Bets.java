package stocker.harshit.com.betburibaat.homeScreen;

import java.util.List;

public class Bets {


    private String matchID;
    private String BetDesc;
    private List<String> forUser;

    public String getMatchID() {
        return matchID;
    }

    public void setMatchID(String matchID) {
        this.matchID = matchID;
    }

    public String getBetDesc() {
        return BetDesc;
    }

    public void setBetDesc(String betDesc) {
        BetDesc = betDesc;
    }

    public List<String> getForUser() {
        return forUser;
    }

    public void setForUser(List<String> forUser) {
        this.forUser = forUser;
    }

    public List<String> getAgainstUser() {
        return againstUser;
    }

    public void setAgainstUser(List<String> againstUser) {
        this.againstUser = againstUser;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private List<String> againstUser;
    private int price;
}
