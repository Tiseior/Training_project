package entityPackage;

import entityPackage.entities.Player;
import entityPackage.entities.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyJava2 {

    // ����� ���������� ������� �� Jira

    public static void main(String[] args) {

        // ����� � ��� �����
        Scanner in = new Scanner(System.in);
        System.out.print("������� ���������� �������: ");
        int play = in.nextInt();
        in.close();

        List<Player> plsFor = new ArrayList<>();
        for (int i = 0; i < play; i++) {
            plsFor.add(new Player(0, 0, 0, 0));
        }
        System.out.println("������ ����� � For: " + plsFor.size());

        List<Player> plsRec = new ArrayList<>();
        plsRec = recFunc(plsRec, play);
        System.out.println("������ ����� � Rec: " + plsRec.size());

        List<Player> plsStr = new ArrayList<>();

        System.out.println("������ ����� � Str: " + plsStr.size());

        Team t1 = new Team(plsFor);
        Team t2 = new Team(plsRec);
        Team t3 = new Team(plsStr);
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
