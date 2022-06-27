package entityPackage;

import entityPackage.entities.Player;
import entityPackage.entities.Team;
import entityPackage.entities.TeamList;
import entityPackage.entitiesCreate.PlayoffCalculating;

import java.util.*;

public class MyJava2 {

    // ����� ���������� ������� �� Jira

    public static void main(String[] args) {

        // ���� � ����������
        /*Scanner in = new Scanner(System.in);
        System.out.print("������� ���������� �������: ");
        int play = in.nextInt();
        in.close();*/
        int players = 10;

        System.out.println("\n������������ ������ ������\n");
        long start, finish;
        players = 8000;
        PlayoffCalculating game = new PlayoffCalculating();
        // �������� ������ �������
        start = System.nanoTime();
        List<Player> playFor = game.createEmptyPlayersFor(players);
        finish = System.nanoTime();
        System.out.println("For ������ " + playFor.size() + " ������� �� " + (finish - start) + " ��");
        List<Player> playRec = new ArrayList<>();
        start = System.nanoTime();
        game.createEmptyPlayersRec(playRec, players); // �������� ������� �� 9000 �������
        finish = System.nanoTime();
        System.out.println("�������� ������� " + playRec.size() + " ������� �� " + (finish - start) + " ��");
        start = System.nanoTime();
        List<Player> playStream = game.createEmptyPlayersStream(players);
        finish = System.nanoTime();
        System.out.println("Stream ������ " + playStream.size() + " ������� �� " + (finish - start) + " ��");

        players = 5;
        // ������ � ��������
        playFor.clear();
        System.out.println("\n������ ������:\n");
        playFor = game.createEmptyPlayersFor(players);
        game.infoPlayers(playFor);
        System.out.println("����������� ������:\n");
        game.fillPlayersStandardCharacteristics(playFor);
        game.infoPlayers(playFor);
        System.out.println("������� 5 �������:\n");
        game.addRandomPlayersToList(playFor, 5);
        game.infoPlayers(playFor);
        System.out.println("������ ������� � id 1, 3, 7:\n");
        game.deletePlayer(playFor, 1);
        game.deletePlayer(playFor, 3);
        game.deletePlayer(playFor, 7);
        game.infoPlayers(playFor);

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

        // �������� ������� � ������
        players = 20;
        List<Player> playersList = game.createPlayersRandom(players);
        System.out.println("������� �������: " + playersList.size());
        List<Team> teamsList = game.createTeamsByStandardCount(playersList);
        System.out.println("������� ������:\n");
        game.infoTeamsIdPlayers(teamsList);
        game.maxPowerTeamNoStability(teamsList);
        System.out.println("���� ����������\n");
        game.getExpectedWinner(teamsList);
    }
}
