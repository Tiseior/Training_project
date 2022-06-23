package entityPackage.entities;

import java.util.List;

public class Team {
    public List<Player> listPlayer;
    public int id;

    public Team(List<Player> listPlayer, int id) {
        this.listPlayer = listPlayer;
        this.id = id;
    }

    public float teamPower() {
        float pw = 0;
        for (int i = 0; i < listPlayer.size(); i++) {
            pw += listPlayer.get(i).playerPower();
        }
        return pw;
    }

    public float teamPowerNoStability() {
        float pw = 0;
        final float AVG_KD = 0.95f;
        final float AVG_ADR = 74;
        for (int i = 0; i < listPlayer.size(); i++) {
            pw += (listPlayer.get(i).kd / AVG_KD * 50) + (listPlayer.get(i).adr / AVG_ADR * 50);
        }
        return pw;
    }

    public void infoTeamId() {
        System.out.print("Команда " + id + "\n\t");
        for (int i = 0; i < listPlayer.size(); i++) {
            System.out.print(listPlayer.get(i).id + " ");
        }
    }
}
