import java.util.Scanner;

public class VendingMachine {

	// ���Ǳ��� ��ǰ ���� �׸��� �ε��� 0 = �ݶ� , 1 = �� , 2 = Ŀ��
	public static int[] goodsStock = new int[3];
	public static int[] goodsPrice = new int[3];
	public static String[] goodsName = { "�ݶ�", "��", "Ŀ��" };

	// ���ǱⰡ ������ �ִ� �ܵ�
	public static int vendingMoney = 25000;

	// �������� üũī��
	public static int checkCard = 500;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in); // �Է��� ���� ��ĳ�� ��ü ����

		// ���Ǳ� ��� ä���
		goodsStock[0] = 10; // �ݶ� 10��
		goodsStock[1] = 7; // �� 6��
		goodsStock[2] = 5; // Ŀ�� 5��

		// ��ǰ ���� ����
		goodsPrice[0] = 1100;
		goodsPrice[1] = 600;
		goodsPrice[2] = 700;

		Loop1: while (true) {

			System.out.println("<<����� ���Ǳ� ��ǰ ���>>");
			System.out.println("[0] �ݶ� (1,100��) - " + goodsStock[0] + "��");
			System.out.println("[1] �� (600��) - " + goodsStock[1] + "��");
			System.out.println("[2] Ŀ�� (700��) - " + goodsStock[2] + "��");
			System.out.println("------------------------------------");
			System.out.print("���� ��� ���� (0)���� (1)ī�� : ");
			int input = sc.nextInt(); //

			if (input == 0) {// ���� ����� �����̸�
				int inputTotal = 0;

				while (true) {
					System.out.println(
							"\n������ ������ �ּ���. ���� �ݾ��� 100�� ,500�� ,1,000�� ,5,000�� ,10,000���Ǹ� ��� �����մϴ�. (0)�� �Է��Ͻø� �ݾ� ������ ����˴ϴ�.");

					System.out.print("�ݾ� ���� : ");
					int inputCash = sc.nextInt();

					if (inputCash == 100 || inputCash == 500 || inputCash == 1000 || inputCash == 5000
							|| inputCash == 10000) {
						inputTotal += inputCash;
						System.out.println("���� ���� �ݾ� : " + inputTotal + "��");
						continue;

					} else if (inputCash == 0) {
						System.out.println("�ݾ� ���� ����");

					} else {

						System.out.println("\n(���� �ݾ� ����!!!) ���� �ݾ��� 100�� ,500�� ,1,000�� ,5,000�� ,10,000���Ǹ� ��� �����մϴ�.");
						System.out.println("���Աݾ� ��ȯ : " + inputCash + "");
						continue;

					}

					while (true) {
						System.out.print("\n���Ͻô� ��ǰ�� �������ּ���");
						System.out.println("<<����� ���Ǳ� ��ǰ ���>>");
						System.out.println("[0] �ݶ� (1,100��) - " + goodsStock[0] + "��");
						System.out.println("[1] �� (600��) - " + goodsStock[1] + "��");
						System.out.println("[2] Ŀ�� (700��) - " + goodsStock[2] + "��");
						System.out.println("[3] ������� �� ���Աݾ� ��ȯ");
						System.out.print("\n�Է� : ");
						int goodsNum = sc.nextInt();

						if (goodsNum == 3) {
							System.out.println("��ǰ ���Ű� ��ҵǾ����ϴ�. ���� �ݾ� " + inputTotal + "���� ��ȯ�˴ϴ�.\n");
							continue Loop1;

						} else {
							if (goodsStock[goodsNum] <= 0) {
								System.out.println(goodsName[goodsNum] + "�� ��� �����մϴ�.");
								continue;
							}

							if (inputTotal < goodsPrice[goodsNum]) {
								System.out.println("�ݾ��� �����մϴ�. �ݾ��� �߰��� ������ �ּ���!\n");
								break;
							}

							if ((inputTotal - goodsPrice[goodsNum]) > vendingMoney) {
								System.out.println(
										"���� ���ǱⰡ ������ �ִ� �ܵ��� �����ϹǷ� ������ �ܵ��� ��ȯ�� �� �����ϴ�. �ٸ� ��ǰ�� �����ϰų� ������ ����� �ּ���!");
								continue;
							}

						}

						goodsStock[goodsNum]--;
						inputTotal -= goodsPrice[goodsNum];
						System.out.println("-----------------------------------------------");
						System.out.println(goodsName[goodsNum] + " ���Ű� �Ϸ�Ǿ����ϴ�.");
						System.out.println("�ܾ� : " + inputTotal);
						System.out.println("-----------------------------------------------");
						System.out.print("�ܵ��� ��ȯ�Ͻðڽ��ϱ�? ( [1]YES/[2]NO ) : ");

						int exchangeChoice = sc.nextInt();

						if (exchangeChoice == 1) {
							vendingMoney -= inputTotal;
							System.out.println("�ܵ� " + inputTotal + "�� ��ȯ�մϴ�. �̿��� �ּż� �����մϴ�!\n");
							continue Loop1;
						}

					}

				}

			} else if (input == 1) {
				System.out.print("\nī�带 �������ּ���. [1]üũī�� [2]�ſ�ī�� : ");
				int inputCard = sc.nextInt();

				while (true) {
					System.out.print("\n���Ͻô� ��ǰ�� �������ּ���");
					System.out.println("<<����� ���Ǳ� ��ǰ ���>>");
					System.out.println("[0] �ݶ� (1,100��) - " + goodsStock[0] + "��");
					System.out.println("[1] �� (600��) - " + goodsStock[1] + "��");
					System.out.println("[2] Ŀ�� (700��) - " + goodsStock[2] + "��");
					System.out.println("[3] �������");
					System.out.print("\n�Է� : ");
					int goodsNum = sc.nextInt();
					
					if(goodsNum==3) {
						System.out.println("������ ��ҵǾ����ϴ�. ī�带 �̾��ּ���!\n");
						break;
						
					}else {
						if(goodsStock[goodsNum]<=0) {
							System.out.println(goodsName[goodsNum] + "�� ��� �����մϴ�.");
							continue;
						}
					}
					
					if(inputCard==1) {
						if(goodsPrice[goodsNum]>checkCard) {
							System.out.println("ī���� �ܾ��� �����մϴ�. �ٸ� ��ǰ�� �����ϰų� ������ ������ּ���.");
							continue;
						}
												
						checkCard -= goodsPrice[goodsNum];
						goodsStock[goodsNum]--;
						System.out.println(goodsName[goodsNum] + " ���Ű� �Ϸ�Ǿ����ϴ�. �̿����ּż� �����մϴ�!");
						System.out.println("üũī�� �ܾ� : "+checkCard);
						System.out.println("ī�带 �̾��ּ���.\n");
						break;
					}else {
						
						goodsStock[goodsNum]--;
						System.out.println(goodsName[goodsNum] + " ���Ű� �Ϸ�Ǿ����ϴ�. �̿����ּż� �����մϴ�!");
						System.out.println("ī�带 �̾��ּ���.\n");
						break;
					}
					
					
					
				}

			}

		}

	}

}
