package entityPackage.entities;

import java.util.List;

public class Team {
    public List<Player> listPlayer;

    public Team(List<Player> listPlayer){
        this.listPlayer = listPlayer;
    }
}
