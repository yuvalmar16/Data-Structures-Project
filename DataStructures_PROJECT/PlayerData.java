public class PlayerData  implements Comparable<PlayerData> {


    private Player player;
    int Goals=0;


    public PlayerData(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
    public void SetGoals(int goals) {
        this.Goals=Goals;
    }


    @Override
    public int compareTo(PlayerData o) {
        if (this.player.getId()<o.player.getId())
            return -1;
        else if(this.player.getId()>o.player.getId())
            return 1;
        return 0;
    }
    @Override
    public int hashCode(){
        return getPlayer().getId();
    }
}
