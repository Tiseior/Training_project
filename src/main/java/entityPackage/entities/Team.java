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
        float power = 0;
        for (int i = 0; i < listPlayer.size(); i++) {
            power += listPlayer.get(i).playerPower();
        }
        return power;
    }

    public float teamPowerHard() {
        float power = 0;
        for (int i = 0; i < listPlayer.size(); i++) {
            power += listPlayer.get(i).playerPowerHard();
        }
        return power;
    }

    public float teamPowerNoStability() {
        float power = 0;
        for (int i = 0; i < listPlayer.size(); i++) {
            power += (listPlayer.get(i).kd / Config.avgKd * 50) + (listPlayer.get(i).adr / Config.avgAdr * 50);
        }
        return power;
    }

    public void infoTeamId() {
        System.out.print("Команда " + id + "\n\t");
        for (int i = 0; i < listPlayer.size(); i++) {
            System.out.print(listPlayer.get(i).id + " ");
        }
    }
}
