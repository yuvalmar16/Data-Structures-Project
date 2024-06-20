public class PlayersScore implements Comparable<PlayersScore>{
    private Player player;
    int Goals;
    PlayersScore next;
    PlayersScore prev;



    public PlayersScore(Player player, int goals) {
        this.player = player;
        this.Goals=goals;



    }

    public PlayersScore(PlayersScore newPlayerScore) {
        this.player = newPlayerScore.getPlayer();
        this.Goals=newPlayerScore.Goals;
    }


    public Player getPlayer() {
        return player;
    }

    @Override
    public int compareTo(PlayersScore o) {
        if(this.Goals==o.Goals && this.getPlayer().getId()==o.getPlayer().getId()) {
            return 0;
        }
        if (this.Goals == o.Goals){
            if(this.getPlayer().getId()<o.getPlayer().getId())
                return 1;
            else
                return -1;}
        if(this.Goals > o.Goals) {
            return 1;
        }

        return -1;

    }




}
