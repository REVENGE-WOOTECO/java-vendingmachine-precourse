# 미션 - 자판기

## 🔍 구성

- Client : Application
- Model : Coin, CoinRepository, item
- View : InputView(입력 담당), PrintView(출력 담당)
- Controller(역할) : VendingMachine
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

<br>

### Feedback
- [X] ``CoinRepository``의 show~ naming 변경 : 상위 클래스에서 자신을 사용하는 하위 클래스를 의식하는 네이밍을 실제 행위로 변경하기
- [X] ``VendingMachine``의 items : 일급 컬렉션 또는 ItemRepository 생성 및 컬렉션 조작 메서드 옮기기
- [ ] ``VendingMachine``의 makeOrderList 메서드 : ``InputValidator``의 검증 부분을 각 객체로 옮기고 장단점 고려. split 부분을 도메인으로 옮기기
- [ ] 출력 부분은 모두 ``PrintView``로 옮겨서 통일하기
- [ ] ``CoinReposiroty``의 변수명 변경(30번째 줄)
- [ ] ``CoinReposiroty``의 showSmallChange 메서드 리팩토링
- [ ] 보일러 플레이트 코드 관리(비즈니스 로직을 상위로 노출)
- [ ] Constant 남용 fix
- [ ] 정규식 패턴 naming 변경
- [ ] public 메서드 상위로 이동

<br>


### 설계 상세 사항 

#### 1. Application(Client)
- VendingMachine에 대해 다음 순서로 메서드를 호출한다.
1. ``VendingMachine.from(new ArrayList<>())`` : 초기화 진행
2. ``vendingMachine.showCoinStatus()`` : 처음 상태 출력
3. ``vendingMachine.makeOrderList();`` : 주문 목록 만들기
4. ``vendingMachine.requestOrder();`` : 주문 요청

<br>

#### 2. VendingMachine
- 동전을 관리해주는 ``CoinRepository``, 가지고 있는 아이템 목록인 ``items``, 잔돈 ``smallChange``를 변수로 가진다.
- 사용자로부터 입력받은 동전 액수, 아이템 리스트를 기반으로 주문을 수행하는 역할을 수행한다.

<br>

#### 3. Coin
- 얼마짜리인지 나타내는 ``amount``를 가지고 있으며, 외부에서 초기화를 위한 amount를 반환해주는 역할을 한다.

<br>

#### 4. CoinRepository
- ``savedCoin``이라는 Map을 가지고 있으며, key로는 Coin의 ``amount``, value로는 개수를 가진다.
- 사용자로부터 입력된 입력 동전으로부터 랜덤으로 동전을 생성해낸다.
- 거슬러 주는 시점에 Coin amount - Count 쌍으로 된 Map을 만들며, Greedy한 방식으로 큰 amount의 동전부터 차례로 거스름돈으로 만든다.

<br>

#### 5. Item
- VendingMachine에 등록하기 위한 itemName, price, quantity를 가지는 객체이다.
- 해당 Item이 주문 가능한지 판단해주는 일부 메서드를 가진다. 

<br>

#### 6. InputView
- 사용자 입력을 전적으로 담당한다.
- 검증은 ``InputValidator``로 위임한다.

<br>

#### 7. PrintView
- 출력을 전적으로 담당한다.

<br>

#### 8. InputValidator
- 사용자 입력을 검증하는 역할을 담당한다.

<br>

#### 9. Constant
- 공통으로 사용되는 문자, 숫자에 의미를 부여하여 관리한다.
