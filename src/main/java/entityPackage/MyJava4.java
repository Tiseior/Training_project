package entityPackage;

import entityPackage.entities.Config;
import entityPackage.entities.Player;
import entityPackage.entities.Team;
import entityPackage.entitiesCreate.PlayoffCalculating;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyJava4 {

    // ��������� ������ ����� ������

    public static void main(String[] args) {

        int players = 20;
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
        game.redistributionPlayers(teamsList);
        game.infoTeamsIdPlayers(teamsList);

        playersList.clear();
        teamsList.clear();
        players = 20;
        System.out.println("\n����� ���������� ����\n");
        List<Team> winners = new ArrayList<>();
        playersList = game.createPlayersRandom(players);
        teamsList = game.createTeamsByStandardCount(playersList);
        System.out.println("������ ������:\n");
        game.infoTeamsIdPlayers(teamsList);
        System.out.println("\n������ �����\n");
        winners.add(game.getExpectedWinnerHard(teamsList));
        System.out.println("�������� ������ ������\n");
        game.redistributionPlayers(teamsList);  // ��� ��������� ���������������
        game.infoTeamsIdPlayers(teamsList);
        System.out.println("\n������ �����\n");
        winners.add(game.getExpectedWinnerHard(teamsList));
    }
}
