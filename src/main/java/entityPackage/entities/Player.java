package entityPackage.entities;

public class Player {
    public int id; //������������� ������
    public float kd; //��������� ������ � ������� (�� 0,1 �� 2)
    public float adr; //���������� ����� �� ����� (�� 40 �� 120)
    public float stability; //�� 0,6 �� 1 (�� ��� �� ��������)

    public Player(int id, float kd, float adr, float stability) {
        this.id = id;
        this.kd = kd;
        this.adr = adr;
        this.stability = stability;
    }
}
