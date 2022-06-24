package entityPackage.entities;

import java.util.List;

public class TeamList {
    public List<Team> teams;

    public Team getExpectedWinner() {
        while (teams.size() > 1) {
            float pw1 = teams.get(0).teamPower();
            float pw2 = teams.get(1).teamPower();
            System.out.println("������� " + teams.get(0).id + " (���� " + pw1 + ") vs ������� "
                    + teams.get(1).id + " (���� " + pw2 + ")");
            if (pw1 > pw2) {
                System.out.println("�������� ������� " + teams.get(0).id);
                teams.add(teams.get(0));
            } else {
                System.out.println("�������� ������� " + teams.get(1).id);
                teams.add(teams.get(1));
            }
            teams.remove(0);
            teams.remove(0);
            System.out.println("");
        }
        System.out.println("���������� - ������� " + teams.get(0).id);
        return teams.get(0);
    }
}
