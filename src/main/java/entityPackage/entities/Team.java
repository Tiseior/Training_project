package entityPackage.entities;

import java.util.List;

public class Team {
    public List<Player> listPlayer;

    public Team(List<Player> listPlayer) {
        this.listPlayer = listPlayer;
    }

    public float teamPower() {
        float pw = 0;
        for (int i = 0; i < listPlayer.size(); i++) {
            pw += listPlayer.get(i).playerPower();
        }
        return pw;
    }

    public void infoTeamId() {
        for (int i = 0; i < listPlayer.size(); i++) {
            System.out.print(listPlayer.get(i).id + " ");
        }
    }
}
