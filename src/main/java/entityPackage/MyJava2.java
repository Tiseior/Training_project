package entityPackage;

import entityPackage.entities.AbstractObject;
import entityPackage.entities.Player;
import entityPackage.entities.Team;
import entityPackage.entitiesCreate.CreateAbstractObject;
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

        Team t1 = new Team(plsFor, 1);
        Team t2 = new Team(plsRec, 2);
        Team t3 = new Team(plsStr, 3);

        // ========================================================
        int pls = 2000; // ���������� �������
        int tms = 400; // ���������� ������                                     // !!!!!

        List<Player> playersList = new ArrayList<>();
        for (int i = 1; i <= pls; i++) {
            Random rand = new Random();
            playersList.add(new Player(i, rand.nextFloat(0.1f, 2), rand.nextFloat(40, 120), rand.nextFloat(0.6f, 1)));
        }

        // �������� ����, ��� � ��� �� ����������� ��� 2000 �������
        /*System.out.println("�������������� �������:\n");
        for (Player l : playersList) {
            l.infoPlayer();
        }*/

        // ��������� �� �������, ��������� subList, ��� j - ��� ���������� ������� � �������
        List<Team> teamsList = new ArrayList<>();
        Collections.shuffle(playersList);
        for (int i = 0, j = pls / tms, ind = 1; i < pls; i += j, ind++) {
            teamsList.add(new Team(playersList.subList(i, i + j), ind));
        }

        /*System.out.println("������� ������:");
        for (int i = 0; i < teamsList.size(); i++) {
            teamsList.get(i).infoTeamId();
            System.out.println();
        }*/

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

        /*System.out.println("\n���������� ���� ������� �� ������ ������ (� ������ ������������): ");
        for (Player l : playersList) {
            System.out.println("���� ������ " + l.id + " = " + l.playerPower());
        }

        System.out.println("\n���������� ���� ������ �� ������ ������ (� ������ ������������): ");
        for (int i = 0; i < teamsList.size(); i++) {
            System.out.print(teamsList.get(i).id + ". " + teamsList.get(i).teamPower());
            System.out.println();
        }*/

        // =============================================================================
        Collections.shuffle(teamsList);
        System.out.print("\n������ ������: ");
        int mx = 0;
        for (int i = 0; i < teamsList.size(); i++) {
            System.out.print(teamsList.get(i).id + " ");
            if (teamsList.get(i).teamPowerNoStability() > teamsList.get(mx).teamPowerNoStability()) {
                mx = i;
            }
        }
        System.out.println("\n�������������� ���������� - ������� " + teamsList.get(mx).id);
        System.out.println("\n\t�������� ����\n");
        while (teamsList.size() > 1) {
            float pw1 = teamsList.get(0).teamPower();
            float pw2 = teamsList.get(1).teamPower();
            System.out.println("������� " + teamsList.get(0).id + " (���� " + pw1 + ") vs ������� "
                               + teamsList.get(1).id + " (���� " + pw2 + ")");
            if (pw1 > pw2) {
                System.out.println("�������� ������� " + teamsList.get(0).id);
                teamsList.add(teamsList.get(0));
            } else {
                System.out.println("�������� ������� " + teamsList.get(1).id);
                teamsList.add(teamsList.get(1));
            }
            teamsList.remove(0);
            teamsList.remove(0);
            System.out.println("");
        }
        System.out.println("���������� - ������� " + teamsList.get(0).id);

        CreateAbstractObject createAbstractObject = new CreateAbstractObject();
        List<AbstractObject> list = createAbstractObject.createListOfAbstractObjects();
        // ������� ������������ ������� �� ���� �������
        /*System.out.println("����������� ������� ����������");
        for (int i = 1; i <= 20; i++) {
            System.out.println("���� " + i + ", ����: " + teamsList.get(0).teamPower());
        }*/
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
