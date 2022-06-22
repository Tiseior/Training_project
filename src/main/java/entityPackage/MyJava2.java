package entityPackage;

import entityPackage.entities.Player;
import entityPackage.entities.Team;
import entityPackage.entitiesCreate.CreatePlayers;

import java.util.*;

public class MyJava2 {

    // ����� ���������� ������� �� Jira

    public static void main(String[] args) {

        // ����� � ��� �����
        /*Scanner in = new Scanner(System.in);
        System.out.print("������� ���������� �������: ");
        int play = in.nextInt();
        in.close();*/
        int play = 10;

        List<Player> plsFor = new ArrayList<>();
        for (int i = 0; i < play; i++) {
            plsFor.add(new Player(0, 0, 0, 0));
        }
        System.out.println("������ ����� � For: " + plsFor.size());

        List<Player> plsRec = new ArrayList<>();
        plsRec = recFunc(plsRec, play);
        System.out.println("������ ����� � Rec: " + plsRec.size());

        // �������� ��, ��� � �����, �� �� ������� ���� � ����������������� ����������� ���������,
        // ������� ����� ������������ �������� ������� ������ (������ ���������� ������ :) )
        Player[] pl = new Player[play];
        List<Player> plsStr = new ArrayList<>();
        Arrays.stream(pl).filter(el -> el == null).forEach(obj -> plsStr.add(new Player(0, 0, 0, 0)));
        System.out.println("������ ����� � Str: " + plsStr.size());
        pl = null;

        Team t1 = new Team(plsFor);
        Team t2 = new Team(plsRec);
        Team t3 = new Team(plsStr);

        // ========================================================
        int pls = 20; // ���������� �������
        int tms = 4; // ���������� ������
        List<Player> playersList = new ArrayList<>();
        for (int i = 1; i <= pls; i++) {
            Random rand = new Random();
            playersList.add(new Player(i, rand.nextFloat(0.1f, 2), rand.nextFloat(40, 120), rand.nextFloat(0.6f, 1)));
        }

        // �������� ����, ��� � ��� �� ����������� ��� 2000 �������
        System.out.println("���������, ��� �����:\n");
        for (Player l : playersList) {
            l.infoPlayer();
        }

        // ��������� �� �������, ��������� subList, ��� j - ��� ���������� ������� � �������
        List<Team> teamsList = new ArrayList<>();
        Collections.shuffle(playersList);
        for (int i = 0, j = pls / tms; i < pls; i += j) {
            teamsList.add(new Team(playersList.subList(i, i + j)));
        }

        System.out.println("������� ������:");
        for (int i = 0; i < teamsList.size(); i++) {
            System.out.print((i + 1) + ". ");
            teamsList.get(i).infoTeamId();
            System.out.println();
        }

        // ���� ������ ������ � ��������� �������
        CreatePlayers lst = new CreatePlayers();
        lst.createPlayersFor(10);
        lst.createPlayersRec(10);
        lst.createPlayersStream(10);
        CreatePlayers plsRand = new CreatePlayers();
        plsRand.createPlayersRandom(10);
        //System.out.println("��������� ������:");
        //plsRand.infoPlayer();
        // =======================================

        System.out.println("\n���������� ���� ������� �� ������ ������ (� ������ ������������): ");
        for (Player l : playersList) {
            System.out.println("���� ������ " + l.id + " = " + l.playerPower());
        }

        System.out.println("\n���������� ���� ������ �� ������ ������ (� ������ ������������): ");
        for (int i = 0; i < teamsList.size(); i++) {
            System.out.print((i + 1) + ". " + teamsList.get(i).teamPower());
            System.out.println();
        }
    }

    public static List<Player> recFunc(List<Player> lst, int i) {
        if (i > 0) {
            lst.add(new Player(0, 0, 0, 0));
            return recFunc(lst, --i);
        } else {
            return lst;
        }
    }
}
