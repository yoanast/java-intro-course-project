package fmi.courseProject;

import java.util.Random;
import java.util.Scanner;

public class Player {

    static Random random = new Random();

    protected String name;
    protected String symbol;
    protected int id;
    protected int position;
    protected int wallet;
    int evilPlanId;

    boolean hasPlayerRealizedEvilPlanInCycle = false;

    boolean isActive = true;
    double income;
    double potentialIncome;

    boolean taxAudit = false;
    boolean catlikeDivorce = false;
    boolean canPlayerPlaceTraps = true;
    boolean canPlayerRealizeEvilPlan = true;
    boolean isChanceOnlyNegative = false;

    Player(String name, String symbol, int id, int position, int wallet) {
        this.name = name;
        this.symbol = symbol;
        this.id = id;
        this.position = position;
        this.wallet = wallet;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    /** Метод, чрез който образно казано "хвърляме зарче". В зависимост от броя на страните, които дефинираме по-късно
     *  за различните цели(например преместването на играч), ни се пада случайно число, което определя съдбата на играча.
     *
     * @param numberOfSides Максималната стойност на възможните случайни числа
     * @return              Случайно число
     */

    public static int throwADice(int numberOfSides) {

        return random.nextInt(numberOfSides);
    }

    /** Метод, чрез който на случаен принцип се избира кой от играчите да започне първи, след което се правят проверки
     *  на какъв тип поле е стъпил.
     *
     * @param gameBoard Масив от полета.
     * @param scanner   Обект от Scanner.
     */

    public static void StartFirst(Field[] gameBoard, Scanner scanner) {

        int firstPlayerId = throwADice(2) + 1;

        if(firstPlayerId == 1) {
            System.out.println("Първи ще играете Вие: ");
            Application.activePlayerId = 1;
            Application.player.move(gameBoard);
            GameBoard.drawGameBoard(gameBoard);
            Field.hasPlayerStepOnTrap(gameBoard,scanner,Application.player);
            Field.hasPlayerStepOnParty(gameBoard, Application.player);
            Field.hasPlayerStepOnChance(gameBoard, Application.player);
            Field.hasPlayerStepOnSteal(gameBoard, Application.player);
            Field.hasPlayerStepOnInvest(gameBoard, Application.player);


        } else {
            System.out.println("Първи ще играе Ботът: ");
            Application.activePlayerId = 2;
            Application.bot.move(gameBoard);
            GameBoard.drawGameBoard(gameBoard);
            Field.hasPlayerStepOnTrap(gameBoard,scanner,Application.bot);
            Field.hasPlayerStepOnParty(gameBoard, Application.bot);
            Field.hasPlayerStepOnChance(gameBoard, Application.bot);
            Field.hasPlayerStepOnSteal(gameBoard, Application.bot);
            Field.hasPlayerStepOnInvest(gameBoard, Application.bot);

        }

    }

    /** Метод, чрез който завъртаме реда на играчите всеки път след като един от тях приключи своя ход.
     *
     * @param gameBoard Масив от полета.
     * @param scanner   Обект от тип Scanner.
     */

    public static void switchTurn(Field[] gameBoard, Scanner scanner) {

       Application.player.hasPlayerReachStart(gameBoard);
       Application.bot.hasBotReachStart(gameBoard);

        if(Application.bot.isActive) {
            if (Application.activePlayerId == 1) {
                Application.bot.move(gameBoard);
                GameBoard.drawGameBoard(gameBoard);
                System.out.println();
                System.out.println();
                Application.activePlayerId = 2;
                Field.hasPlayerStepOnTrap(gameBoard, scanner, Application.bot);
                Field.hasPlayerStepOnParty(gameBoard, Application.bot);
                Field.hasPlayerStepOnChance(gameBoard, Application.bot);
                Field.hasPlayerStepOnSteal(gameBoard, Application.bot);
                Field.activateEvilPlan(gameBoard, Application.bot);
                Field.hasPlayerStepOnInvest(gameBoard, Application.bot);

            }
        }
        if(Application.player.isActive) {
            if (Application.activePlayerId == 2) {
                Application.player.move(gameBoard);
                GameBoard.drawGameBoard(gameBoard);
                System.out.println();
                System.out.println();
                Application.activePlayerId = 1;
                Field.hasPlayerStepOnTrap(gameBoard, scanner, Application.player);
                Field.hasPlayerStepOnParty(gameBoard, Application.player);
                Field.hasPlayerStepOnChance(gameBoard, Application.player);
                Field.hasPlayerStepOnSteal(gameBoard, Application.player);
                Field.activateEvilPlan(gameBoard, Application.player);
                Field.hasPlayerStepOnInvest(gameBoard, Application.player);

            }
        }
        isCycleOver(gameBoard);
        Application.player.didPlayerLose();
        Application.bot.didPlayerLose();
    }

    /** Метод, чрез който проверяваме дали някой от играчите е останал без пари. Ако има такъв играч, играта приключва
     *  и другия играч става победител.
     */

    public void didPlayerLose() {

        if(this.getWallet() < 1) {
            System.out.println("Играч " + this.getName() + " остана без пари и загуби играта!");
            Application.isGameRunning = false;
            Application.isCycleOver = true;
        }
    }

    /** Метод, чрез който проверяваме дали играчът е стигнал поле 'Start'. Ако е, той трябва да спре и да изчака бота,
     *  за да може да започне нов цикъл.
     *  П.С. Имах проблем с този метод и затова се наложи да направя два метода с еднаква функционалност.
     *
     * @param gameBoard Масив от полета.
     * @return          Методът връща true ако играчът е достигнал до поле 'Start' или false - ако не е.
     */

    public boolean hasPlayerReachStart(Field[] gameBoard) {
        if(this.getPosition() == 19) {
            this.isActive = false;
            System.out.println("Ти стигна до Старт, сега изчаквш бота, за да започнете нов цикъл");
            Application.activePlayerId = 1;
            return true;
        } else return false;
    }

    /** Метод, чрез който проверяваме дали ботът е стигнал поле 'Start'. Ако е, той трябва да спре и да изчака другия,
     *  за да може да започне нов цикъл.
     *
     * @param gameBoard Масив от полета.
     * @return          Методът връща true ако бота е достигнал до поле 'Start' или false - ако не е.
     */

    public boolean hasBotReachStart(Field[] gameBoard) {
        if(this.getPosition() == 19) {
            this.isActive = false;
            System.out.println("Ботът стигна до Старт, сега те изчаква, за да започнете нов цикъл");
            Application.activePlayerId = 2;
            return true;
        } else return false;
    }

    /** Метод, чрез който на случаен принцип ни се получаваме 'зъл план', когато стъпим на поле 'Steal'.
     *
     * @param player Обект от тип Player.
     * @return       Номера на съответния зъл план.
     */

    public static int getEvilPlan(Player player) {
        int evilPlan = throwADice(3) + 1;

        if(evilPlan == 1) {
            player.evilPlanId = 1;
        }

        if(evilPlan == 2) {
            player.evilPlanId = 2;
        }

        if(evilPlan == 3) {
            player.evilPlanId = 3;
        }
        System.out.println();
        System.out.println("Падна се зъл план номер " + evilPlan);
        return evilPlan;

    }

    /** Метод, чрез който се извършва движението по игралното поле.
     *
     * @param gameBoard Масив от полета.
     */

    public void move(Field[] gameBoard) {

        System.out.println("Натиснете Enter, когато сте готови за следващия ход");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        int oldPos = this.getPosition();
        int newPos = oldPos + throwADice(2) + 1;

        if(oldPos == 18) {
            newPos = oldPos + throwADice(1) +1;
        }

        for(int j = 0; j <= 19; j++) {
            if(gameBoard[j].getPosition() == oldPos) {
                gameBoard[j].setSymbol(gameBoard[j].initSymbol);
            }
        }

        for (int i = 0; i <= 19; i++) {
            if (gameBoard[i].getPosition() == newPos) {
                gameBoard[i].setSymbol(gameBoard[i].getSymbol() + this.getSymbol());
                this.setPosition(newPos);
            }
        }
    }

    /** Метод, чрез който проверяваме дали цикълът е приключил. Това зависи от позицията, на която се намират играчите.
     *
     * @param gameBoard Масив от полета.
     */

    public static void isCycleOver(Field[] gameBoard) {
        if (Application.player.hasPlayerReachStart(gameBoard) && Application.bot.hasBotReachStart(gameBoard)) {
            Application.isCycleOver = true;
            System.out.println("Започва нов цикъл!");

        }
    }
}
