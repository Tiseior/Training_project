package entityPackage.entitiesCreate;

import entityPackage.entities.Config;
import entityPackage.entities.Player;
import entityPackage.entities.Team;

import java.util.*;

public class PlayoffCalculating {

    // �������� ������� ����� ���� For � �������� ����������������
    public List<Player> createEmptyPlayersFor(int playersCount) {
        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < playersCount; i++) {
            playerList.add(new Player(0, 0, 0, 0));
        }
        return playerList;
    }

    // �������� ������� ����� �������� � �������� ����������������
    public void createEmptyPlayersRec(List<Player> playersList, int playersCount) {
        if (playersCount > 0) {
            playersList.add(new Player(0, 0, 0, 0));
            createEmptyPlayersRec(playersList, --playersCount);
        }
    }

    // �������� ������� ����� stream � �������� ����������������
    public List<Player> createEmptyPlayersStream(int players) {
        Player[] pl = new Player[players];
        List<Player> playersList = new ArrayList<>();
        Arrays.stream(pl).filter(el -> el == null).forEach(obj -> playersList.add(new Player(0, 0, 0, 0)));
        pl = null;
        return playersList;
    }

    // ���������� ������������� � ������� ������������ ���������� ����������
    public void fillPlayersStandardCharacteristics(List<Player> playerList) {
        Random rand = new Random();
        int id = 1;
        for (Player player : playerList) {
            player.id = id;
            player.kd = rand.nextFloat(0.1f, 2);
            player.adr = rand.nextFloat(40, 120);
            player.stability = rand.nextFloat(0.6f, 1);
            id++;
        }
    }

    // �������� ������� �� ������������ ���������� ����������������
    public List<Player> createPlayersRandom(int playersCount) {
        List<Player> playersList = new ArrayList<>();
        Random rand = new Random();
        for (int id = 1; id <= playersCount; id++) {
            playersList.add(new Player(id, rand.nextFloat(0.1f, 2), rand.nextFloat(40, 120),
                    rand.nextFloat(0.6f, 1)));
        }
        return playersList;
    }

    // ���������� ����� ������� �� ������������ ���������� ����������������
    public void addRandomPlayersToList(List<Player> playerList, int playersCount) {
        Random rand = new Random();
        int size = playerList.size();
        for (int id = size + 1; id <= size + playersCount; id++) {
            playerList.add(new Player(id, rand.nextFloat(0.1f, 2), rand.nextFloat(40, 120),
                    rand.nextFloat(0.6f, 1)));
        }
    }

    // �������� ������ �� id
    public void deletePlayer(List<Player> playerList, int id) {
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).id == id) {
                playerList.remove(i);
                break;
            }
        }
    }

    // ��������� �� �������, ��������� subList, ��� j - ��� ���������� ������� � �������
    public List<Team> createTeamsByStandardCount(List<Player> playersList) {
        List<Team> teamsList = new ArrayList<>();
        Collections.shuffle(playersList);
        try {
            for (int i = 0, ind = 1; i < playersList.size(); i += Config.standardSizeOfTeam, ind++) {
                teamsList.add(new Team(playersList.subList(i, i + Config.standardSizeOfTeam), ind));
            }
        } catch (IndexOutOfBoundsException e) {
            //nothing
        }
        return teamsList;
    }

    // ���������� ����� ������� ������� ��� ����� ������������ �������
    public void maxPowerTeamNoStability(List<Team> teamsList) {
        int mx = 0;
        for (int i = 0; i < teamsList.size(); i++) {
            if (teamsList.get(i).teamPowerNoStability() > teamsList.get(mx).teamPowerNoStability()) {
                mx = i;
            }
        }
        System.out.println("\n����� ������� ����, ��� ����� ������������, � ������� " + teamsList.get(mx).id);
        System.out.println("");
    }

    // ���� ��� ��������� ���������� �������
    public void getExpectedWinner(List<Team> teams) {
        Collections.shuffle(teams);
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
    }

    // ��������� ������������ �������
    public void reduceStability(List<Player> playersList) {
        for (Player player : playersList) {
            player.stability -= 0.05f;
            if (player.stability < 0.6f) {
                player.stability = 0.6f;
            }
        }
    }

    // ��������� ������������ �������
    public void increaseStability(List<Player> playersList) {
        for (Player player : playersList) {
            player.stability += 0.02f;
            if (player.stability > 1f) {
                player.stability = 1f;
            }
        }
    }

    // ����������������� ������� � ����� ������
    // ����� �� �������, ��� ��� �������� ��������, ��� ����� ��������� ������ �� ������ �������
    // ��� �� � �� ���� ������� id ������� � ��������, ��� ����� ����������
    public void redistributionPlayers(List<Team> teamsList) {
        Random id = new Random();
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < Config.redistributePlayersCount; i++) {
            int teamId = id.nextInt(0, teamsList.size() - 1);
            System.out.println("�������: " + teamId);
            int playerId = id.nextInt(0, teamsList.get(teamId).listPlayer.size() - 1);
            System.out.println("�����: " + playerId);
            System.out.println("��� ����� ����� " + teamsList.get(teamId).listPlayer.get(playerId).id);
            players.add(teamsList.get(teamId).listPlayer.get(playerId));
            teamsList.get(teamId).listPlayer.remove(playerId);
        }
        //System.out.println("������� ��� �������\n");
        //infoTeamsIdPlayers(teamsList);
        System.out.println("����������� ������\n");
        infoPlayers(players);
    }

    // ����� ������������� ���� �������
    public void infoPlayers(List<Player> playersList) {
        for (int i = 0; i < playersList.size(); i++) {
            System.out.println("\t�������������: " + playersList.get(i).id);
            System.out.println("\t����� � �������: " + playersList.get(i).kd);
            System.out.println("\t���-�� ����� �� �����: " + playersList.get(i).adr);
            System.out.println("\t������������: " + playersList.get(i).stability + "\n");
        }
    }

    // ����� id ������� � ������, � ������� ��� �������
    public void infoTeamsIdPlayers(List<Team> teamsList) {
        for (int i = 0; i < teamsList.size(); i++) {
            teamsList.get(i).infoTeamId();
            System.out.println();
        }
    }
}
