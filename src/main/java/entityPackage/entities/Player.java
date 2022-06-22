package entityPackage.entities;

import java.util.Random;

public class Player {
    public int id; //������������� ������
    public float kd; //��������� ������ � ������� (�� 0,1 �� 2)
    public float adr; //���������� ����� �� ����� (�� 40 �� 120)
    public float stability; //�� 0,6 �� 1 (�� ��� �� ��������)

    final float AVG_KD = 0.95f;
    final float AVG_ADR = 74;

    public Player(int id, float kd, float adr, float stability) {
        this.id = id;
        this.kd = kd;
        this.adr = adr;
        this.stability = stability;
    }

    public float playerPower() {
        Random st = new Random();
        float pw = (kd / AVG_KD * 50) + (adr / AVG_ADR * 50);
        if (st.nextFloat(0, 1) > stability) {
            pw /= 2;
        }
        return pw;
    }

    // ���������� �� ������
    public void infoPlayer() {
        System.out.println("\t�������������: " + id);
        System.out.println("\t����� � �������: " + kd);
        System.out.println("\t���-�� ����� �� �����: " + adr);
        System.out.println("\t������������: " + stability + "\n");
    }
}
