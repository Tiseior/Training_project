package entityPackage;

import entityPackage.entities.Player;
import entityPackage.entities.Team;
import entityPackage.entitiesCreate.PlayoffCalculating;

import java.util.ArrayList;
import java.util.List;

public class MyJava4 {

    // ��������� ������ ����� ������

    public static void main(String[] args) {

        int players = 32;
        PlayoffCalculating game = new PlayoffCalculating();
        List<Player> playersList = game.createPlayersRandom(players);
        game.infoPlayers(playersList);
        System.out.println("��������� ������������\n");
        game.reduceStability(playersList);
        game.infoPlayers(playersList);
        System.out.println("��������� ������������\n");
        game.increaseStability(playersList);
        game.infoPlayers(playersList);
        List<Team> teamsList = game.createTeamsByStandardCount(playersList);
        game.infoTeamsIdPlayers(teamsList);

        System.out.println("�������������� ������� ������� 1\n");
        game.infoPlayers(teamsList.get(0).listPlayer);
        System.out.println("��������� ������������\n");
        game.reduceStability(teamsList.get(0).listPlayer);
        game.infoPlayers(teamsList.get(0).listPlayer);

        System.out.println("�������������� ������� ������� 2\n");
        game.infoPlayers(teamsList.get(1).listPlayer);
        System.out.println("��������� ������������\n");
        game.increaseStability(teamsList.get(1).listPlayer);
        game.infoPlayers(teamsList.get(1).listPlayer);

        System.out.println("������ ������ ������:\n");
        game.infoTeamsIdPlayers(teamsList);
        System.out.println("\n����� ������ ������:\n");
        teamsList = game.redistributionPlayers(teamsList);
        game.infoTeamsIdPlayers(teamsList);

        System.out.println("����� ����\n");
        teamsList = game.redistributionPlayers2(teamsList);
        game.infoTeamsIdPlayers(teamsList);

        playersList.clear();
        teamsList.clear();
        players = 80; // ���������� �������
        int years = 2; // ���������� ���
        System.out.println("\n����� ���������� ����\n");
        List<Team> winners = new ArrayList<>();
        playersList = game.createPlayersRandom(players);
        teamsList = game.createTeamsByStandardCount(playersList);
        for (int i = 0; i < years; i++) {
            System.out.println("\n\t��� " + (i+1) + "\n");
            System.out.println("������� ������:\n");
            game.infoTeamsIdPlayers(teamsList);
            System.out.println("\n����� ����-�����-����\n");
            winners.add(game.getExpectedWinnerHard(teamsList));
            System.out.println("\n�������� ������� ������:\n");
            teamsList = game.redistributionPlayers(teamsList);
            game.infoTeamsIdPlayers(teamsList);
            System.out.println("\n����� ����-�����-����\n");
            winners.add(game.getExpectedWinnerHard(teamsList));
            teamsList = game.redistributionPlayers2(teamsList);
        }
        System.out.println("\n������ ������-�����������:");
        for (Team team : winners) {
            System.out.println("\t������� " + team.id);
        }
    }
}
