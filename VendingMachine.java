import java.util.Scanner;

public class VendingMachine {

	// 자판기의 상품 갯수 그리고 인덱스 0 = 콜라 , 1 = 물 , 2 = 커피
	public static int[] goodsStock = new int[3];
	public static int[] goodsPrice = new int[3];
	public static String[] goodsName = { "콜라", "물", "커피" };

	// 자판기가 가지고 있는 잔돈
	public static int vendingMoney = 25000;

	// 구매자의 체크카드
	public static int checkCard = 500;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in); // 입력을 위해 스캐너 객체 생성

		// 자판기 재고 채우기
		goodsStock[0] = 10; // 콜라 10개
		goodsStock[1] = 7; // 물 6개
		goodsStock[2] = 5; // 커피 5개

		// 상품 가격 설정
		goodsPrice[0] = 1100;
		goodsPrice[1] = 600;
		goodsPrice[2] = 700;

		Loop1: while (true) {
			//자판기 상품 리스트 출력 
			System.out.println("<<음료수 자판기 상품 목록>>");
			System.out.println("[0] 콜라 (1,100원) - " + goodsStock[0] + "개");
			System.out.println("[1] 물 (600원) - " + goodsStock[1] + "개");
			System.out.println("[2] 커피 (700원) - " + goodsStock[2] + "개");
			System.out.println("------------------------------------");
			System.out.print("걸제 방법 선택 (0)현금 (1)카드 : "); 
			int input = sc.nextInt(); 

			if (input == 0) {// 결제 방법이 현금이면
				int inputTotal = 0; //투입 금액을 저장하는 변수

				while (true) { // 금액을 여러 번 투일 할 수 있도록 반복문을 사용
					System.out.println(
							"\n현금을 투입해 주세요. 투입 금액은 100원 ,500원 ,1,000원 ,5,000원 ,10,000원권만 사용 가능합니다. (0)을 입력하시면 금액 투입이 종료됩니다.");

					System.out.print("금액 투입 : ");
					int inputCash = sc.nextInt();

					if (inputCash == 100 || inputCash == 500 || inputCash == 1000 || inputCash == 5000 
							|| inputCash == 10000) { // 100원 ,500원 ,1,000원 ,5,000원 ,10,000원권이 들어올 경우 
						inputTotal += inputCash;
						System.out.println("현재 투입 금액 : " + inputTotal + "원");
						continue;

					} else if (inputCash == 0) { // 금액 투입이 완료되어 금액 투입을 종료했을때
						System.out.println("금액 투입 종료");

					} else {
						//  100원 ,500원 ,1,000원 ,5,000원 ,10,000원권 이외의 금액이 투입될 경우 다시 금액을 투입하도록 유도
						System.out.println("\n(투입 금액 오류!!!) 투입 금액은 100원 ,500원 ,1,000원 ,5,000원 ,10,000원권만 사용 가능합니다.");
						System.out.println("투입금액 봔환 : " + inputCash + "");
						continue;

					}

					while (true) {
						System.out.print("\n원하시는 상품을 선택해주세요");
						System.out.println("<<음료수 자판기 상품 목록>>");
						System.out.println("[0] 콜라 (1,100원) - " + goodsStock[0] + "개");
						System.out.println("[1] 물 (600원) - " + goodsStock[1] + "개");
						System.out.println("[2] 커피 (700원) - " + goodsStock[2] + "개");
						System.out.println("[3] 구매취소 후 투입금액 반환");
						System.out.print("\n입력 : ");
						int goodsNum = sc.nextInt();

						if (goodsNum == 3) { // 상품 구매를 취소후 자판기 로직의 맨 처음으로 돌아감
							System.out.println("상품 구매가 취소되었습니다. 투입 금액 " + inputTotal + "원이 반환됩니다.\n");
							continue Loop1;

						} else {
							if (goodsStock[goodsNum] <= 0) { // 재고가 부족하면 다시 메뉴를 선택 할 수 있도록 유도
								System.out.println(goodsName[goodsNum] + "의 재고가 부족합니다.");
								continue;
							}

							if (inputTotal < goodsPrice[goodsNum]) { // 투입 금액 보다 재고 가격이 비쌀때 금액을 투입 할 수 있도록 유도
								System.out.println("금액이 부족합니다. 금액을 추가로 투입해 주세요!\n");
								break;
							}

							if ((inputTotal - goodsPrice[goodsNum]) > vendingMoney) { // 자판기에 잔돈을 거슬러줄 금액이 부족할때 다른 상품을 선택하거나 결제를 취소 할 수 있도록 유도
								System.out.println(
										"현재 자판기가 가지고 있는 잔돈이 부족하므로 고객님의 잔돈을 반환할 수 없습니다. 다른 상품을 구입하거나 결제를 취소해 주세요!");
								continue;
							}

						}

						goodsStock[goodsNum]--; // 재고 - 1
						inputTotal -= goodsPrice[goodsNum]; // 투입금액 - 상품 가격
						System.out.println("-----------------------------------------------");
						System.out.println(goodsName[goodsNum] + " 구매가 완료되었습니다.");
						System.out.println("잔액 : " + inputTotal);
						System.out.println("-----------------------------------------------");
						System.out.print("잔돈을 반환하시겠습니까? ( [1]YES/[2]NO ) : "); //잔돈을 반환할지 아니면 상품을 계속 구매할지

						int exchangeChoice = sc.nextInt();

						if (exchangeChoice == 1) { // 1번을 입력하면 잔돈을 반환하고 프로그램의 맨 처음으로 돌아감
							vendingMoney -= inputTotal;
							System.out.println("잔돈 " + inputTotal + "을 반환합니다. 이용해 주셔서 감사합니다!\n");
							continue Loop1;
						}

					}

				}

			} else { //결제방법이 카드
				System.out.print("\n카드를 삽입해주세요. [1]체크카드 [2]신용카드 : ");
				int inputCard = sc.nextInt(); //체크카드 or 신용카드 삽입

				while (true) {
					System.out.print("\n원하시는 상품을 선택해주세요");
					System.out.println("<<음료수 자판기 상품 목록>>");
					System.out.println("[0] 콜라 (1,100원) - " + goodsStock[0] + "개");
					System.out.println("[1] 물 (600원) - " + goodsStock[1] + "개");
					System.out.println("[2] 커피 (700원) - " + goodsStock[2] + "개");
					System.out.println("[3] 구매취소");
					System.out.print("\n입력 : ");
					int goodsNum = sc.nextInt();
					
					if(goodsNum==3) { //결제를 취소
						System.out.println("결제가 취소되었습니다. 카드를 뽑아주세요!\n");
						break;
						
					}else {
						if(goodsStock[goodsNum]<=0) { // 재고가 없을때
							System.out.println(goodsName[goodsNum] + "의 재고가 부족합니다.");
							continue;
						}
					}
					
					if(inputCard==1) { // 체크카드의 경우 카드 잔액이 상품 가격보다 큰지 확인
						if(goodsPrice[goodsNum]>checkCard) {
							System.out.println("카드의 잔액이 부족합니다. 다른 상품을 선택하거나 결제를 취소해주세요.");
							continue;
						}
												
						checkCard -= goodsPrice[goodsNum]; //체크카드에서 상품 가격만큼 차감
						goodsStock[goodsNum]--; // 상품 제고 차감
						System.out.println(goodsName[goodsNum] + " 구매가 완료되었습니다. 이용해주셔서 감사합니다!");
						System.out.println("체크카드 잔액 : "+checkCard);
						System.out.println("카드를 뽑아주세요.\n");
						break;
					}else {
						
						goodsStock[goodsNum]--; //신용가드의 경우 제고만 차감 
						System.out.println(goodsName[goodsNum] + " 구매가 완료되었습니다. 이용해주셔서 감사합니다!");
						System.out.println("카드를 뽑아주세요.\n");
						break;
					}
					
					
					
				}

			}

		}

	}

}
