# 미션 - 자판기

## 🔍 구성

- Model : Coin
- View : InputView(입력 담당), PrintView(출력 담당)
- Controller : VendingMachine
- Utils
    - InputValidator : Input 검증
    - Constant : 상수 Group

<br>

## 🔧 구현 기능

### 기능 명세
- [X] 자판기의 보유 금액을 입력 받아, 랜덤으로 초기화를 진행한다.
- [X] Item을 Model로 구현한다. 데이터 저장 및 계산
- [X] 자판기가 상품명과 가격, 수량을 저장할 수 있는 Items Map 등을 가지도록 구현한다.
- [X] 구매 요청된 아이템이 존재하는 아이템인지 확인한다.
- [X] 구매 요청된 아이템을 구매할 수 있는지, 그리고 재고가 있는지 확인한다.
- [X] 최소 동전 개수로 반환 -> 큰 돈부터 순차적으로 반환하면 된다. 
- [X] 남은 금액은 자판기가 고이 먹는다 🤯

<br>

### 예외 처리 공통 사항
- 예외 상황 발생 시 에러 문구를 출력한다. 문구는 [ERROR]로 시작되어야 한다.
- 예외 발생 시 `IllegalArgumentException`을 발생시키고, 에러 메시지를 출력 후 그 부분부터 다시 입력받는다.
- 예외에 알맞은 문구를 출력한다.

<br>

### 예외 처리 check list
- [X] 상품 정보 입력을 검증한다.
- [X] 자판기 보유 금액, 사용자 투입 금액을 검증한다.(10으로 나누어 떨어져야 한다.)
- [X] 중복된 상품이름을 검증한다.

<br>

### 추가 처리
- [X] 매직 넘버, SystemMessage 처리한다.
- [X] public method는 Unit Test로 검증하도록 시도해본다.
