package entityPackage.entitiesCreate;

import entityPackage.entities.Config;
import entityPackage.entities.Player;
import entityPackage.entities.Team;

import java.util.*;
import java.util.stream.Collectors;

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
        teamsList.stream().forEach(e -> {
            e.listPlayer.sort(Comparator.comparingInt(i -> i.id));
        });
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
    // ���� �� �������� �����������������. �� ������ ���, ��� � ��������������� ��� ������� � ��������� �������
    // � ���� ������. ����� �������� �������� ������� � ������ ������, � � �������� ������� ��.
    // ���������� �������� ������ � ����� �� ���������, � ������� ������� �������, ������� �� �� ������ � �������� 0 ��
    // ��������� ������, � ����� ������ ��� �� ���������. � ����� ������� ����� �������, ��������� ����� ��� ����������
    // ������ ��� �������������� ������ ������� (�������� ���� ����� ���������).
    // �������� ��� ���� ������, ��� �������� ���������� ����� ������� � ������ ������ � ���� �������. ����� �����������
    // � �������� ������ � ��������� �� �������. ����� �������� ������ �������������, � ������ ����� ����������� ��������
    // �� ����. �� �������� �������� � ��������� ������� �� ������ � ���������, ������� ������ ��, ��� ������.
    public void redistributionPlayers(List<Team> teamsList) {
        List<Player> players = teamsList.stream().flatMap(e -> e.listPlayer.stream()).collect(Collectors.toList());
        Random id = new Random();
        List<Player> redistributedPlayers = new ArrayList<>();
        int playerId = 0;//id.nextInt(0, players.size() - 1);
        Player zero = new Player(0, 0, 0, 0);
        for (int i = 0; i < Config.redistributePlayersCount; i++) {
            while (players.get(playerId) == zero) {
                playerId = id.nextInt(0, players.size() - 1);
            }
            redistributedPlayers.add(players.get(playerId));
            players.set(playerId, zero);
        }
        Collections.shuffle(redistributedPlayers);
        for (int i = 0; i < Config.redistributePlayersCount; i++) {
            playerId = players.indexOf(zero);
            players.set(playerId, redistributedPlayers.get(0));
            redistributedPlayers.remove(0);
        }
        teamsList = createTeamsByStandardCountNoRandom(players);
        /*teamsList.stream().forEach(e -> {
            e.listPlayer.sort(Comparator.comparingInt(i -> i.id));
        });*/
        //return teamsList; // �� ����, ����� �� �������� ���� ������
    }

    // �������� ������ ��� ������������� ������ � ��������
    private List<Team> createTeamsByStandardCountNoRandom(List<Player> playersList) {
        List<Team> teamsList = new ArrayList<>();
        try {
            for (int i = 0, ind = 1; i < playersList.size(); i += Config.standardSizeOfTeam, ind++) {
                teamsList.add(new Team(playersList.subList(i, i + Config.standardSizeOfTeam), ind));
            }
        } catch (IndexOutOfBoundsException e) {
            //nothing
        }
        return teamsList;
    }

    // ������� ���� ��� ��������� ���������� �������
    public Team getExpectedWinnerHard(List<Team> teams) {
        List<Team> teamsThisSeason = teams;
        Collections.shuffle(teamsThisSeason);
        while (teamsThisSeason.size() > 1) {
            float pw1 = teamsThisSeason.get(0).teamPowerHard();
            float pw2 = teamsThisSeason.get(1).teamPowerHard();
            System.out.println("������� " + teamsThisSeason.get(0).id + " (���� " + pw1 + ") vs ������� "
                    + teamsThisSeason.get(1).id + " (���� " + pw2 + ")");
            if (pw1 > pw2) {
                System.out.println("�������� ������� " + teamsThisSeason.get(0).id);
                teamsThisSeason.add(teamsThisSeason.get(0));
                if (teamsThisSeason.get(0).teamPowerNoStability() < teamsThisSeason.get(1).teamPowerNoStability()) {
                    System.out.println("������� " + teamsThisSeason.get(1).id + " ����������");
                    System.out.println("���� 1 " + teamsThisSeason.get(0).teamPowerNoStability() + " ���� 2 " + teamsThisSeason.get(1).teamPowerNoStability());
                    reduceStability(teamsThisSeason.get(1).listPlayer);
                    increaseStability(teamsThisSeason.get(0).listPlayer);
                }
            } else {
                System.out.println("�������� ������� " + teamsThisSeason.get(1).id);
                teamsThisSeason.add(teamsThisSeason.get(1));
                if (teamsThisSeason.get(0).teamPowerNoStability() > teamsThisSeason.get(1).teamPowerNoStability()) {
                    System.out.println("������� " + teamsThisSeason.get(0).id + " ����������");
                    System.out.println("���� 1 " + teamsThisSeason.get(0).teamPowerNoStability() + " ���� 2 " + teamsThisSeason.get(1).teamPowerNoStability());
                    reduceStability(teamsThisSeason.get(0).listPlayer);
                    increaseStability(teamsThisSeason.get(1).listPlayer);
                }
            }
            teamsThisSeason.remove(0);
            teamsThisSeason.remove(0);
            System.out.println("");
        }
        System.out.println("���������� ����� ������ - ������� " + teamsThisSeason.get(0).id);
        return teamsThisSeason.get(0);
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
