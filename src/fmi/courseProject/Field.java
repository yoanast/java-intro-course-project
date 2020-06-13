package fmi.courseProject;

import java.util.Random;
import java.util.Scanner;

public class Field {
    static int playerId;
    static int fieldIndex;
    static boolean isEvilPlanActive = false;
    static byte pChoise;
    static int bChoise;
    static int investment;
    static String companyResult;

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    boolean isAvailable = true;
    String symbol;
    String name;
    int position;
    int id;
    String initSymbol;
    int type;

    public Field(String name, String symbol, int position, int id, String initSymbol) {
        this.name = name;
        this.symbol = symbol;
        this.position = position;
        this.id = id;
        this.initSymbol = initSymbol;
    }

    public Field(String name, String symbol, int position, int id, String initSymbol, boolean isAvailable) {
        this.name = name;
        this.symbol = symbol;
        this.position = position;
        this.id = id;
        this.initSymbol = initSymbol;
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public int setPosition(int position) {
        this.position = position;
        return position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInitSymbol() {
        return initSymbol;
    }

    public void setInitSymbol(String initSymbol) {
        this.initSymbol = initSymbol;
    }

    /** Метод, чрез който изпълняваме действието на капан 'Данъчна ревизия'. Ако някой от играчите е попаднал на него
     *  в края на цикъла той губи 10% от своите приходи за текушия цикъл.
     *
     * @param player Обект от тип Player.
     */

    public static void taxAuditTrap(Player player) {

        if(player.taxAudit) {
            player.income = player.income*0.9;
        }
    }

    /** Метод, чрез който изпълняваме действието на капан 'Развод по котешки'. Ако някой от играчите е попаднал на него
     *  в края на цикъла играта хвърля 10-стенен зар, ако стойността е 2 или 8, не играчът получава печалба от цикъла
     *  (ако има такава). Ако има късмет и стойността е различна от 2 или 8, той получава своята печалба.
     *
     * @param player Обект от тип Player.
     */

    public static void catlikeDivorceTrap(Player player) {

        if(player.catlikeDivorce) {
            int result = Player.throwADice(10) + 1;
            if (result == 8 || result == 2) {
                player.income = 0;
                System.out.println("Ти загуби всичките си приходи от този цикъл.");
            }
            else {
                player.income = player.potentialIncome;
                player.wallet += player.income;
                System.out.println("Налични шоколадови парички: " + player.getWallet());
            }
        }

    }

    /** Метод, чрез който изпълняваме действието на капан 'Пропаганда'. Ако някой от играчите е попаднал на него, той
     *  губи правото си да поставя капани в текущия цикъл.
     *
     * @param player Обект от тип Player.
     */

    public static void propagandaTrap(Player player) {

        player.canPlayerPlaceTraps = false;
    }

    /** Метод, чрез който изпълняваме действието на капан 'Пропаганда'. Ако някой от играчите е попаднал на него, той
     *  губи правото си да реализира зъл план в текущия цикъл.
     *
     * @param player  Обект от тип Player.
     */

    public static void beginToSeeTrap(Player player) {

        player.canPlayerRealizeEvilPlan = false;
    }

    /** Метод, чрез който изпълняваме действието на капан 'Проглеждане'. Ако някой от играчите е попаднал на него,
     *  при стъпване на поле 'Steal', играчът губи своето право да реализира зъл план.
     *
     * @param player Обект от тип Player.
     */

    public static void hazardBossTrap(Player player) {

        player.isChanceOnlyNegative = true;

    }

    /** Метод, чрез който връщаме първоначалните стойности на полетата на играча в началото на всеки нов цикъл.
     *
     * @param player     Обект от тип Player.
     * @param gameBoard  Масив от полета.
     */

    public static void resetStatsForNewCycle(Player player, Field[] gameBoard) {

        player.isActive = true;
        player.taxAudit = false;
        player.catlikeDivorce = false;
        player.canPlayerPlaceTraps = true;
        player.canPlayerRealizeEvilPlan = true;
        player.isChanceOnlyNegative = false;
        player.hasPlayerRealizedEvilPlanInCycle = false;
        player.potentialIncome = 0;
        player.income = 0;
        player.setPosition(0);
        Application.isCycleOver = false;
    }

    /** Метод, чрез който връшаме първоначалните стойности на полетата от тип 'Steal' в началото на всеки цикъл.
     *
     * @param gameBoard Масив от полета.
     */

    public static void resetAvailableFields(Field[] gameBoard) {
        Field.isEvilPlanActive = false;
        gameBoard[11].setSymbol(gameBoard[11].getInitSymbol());

        for (int i = 0; i < 19; i++) {
            if (gameBoard[i].getName().contains("Steal")) {
                gameBoard[i].isAvailable = true;
            }
        }

    }

    /** Метод, чрез който в края на всеки цикъл правим пресмятане на приходите и извеждаме състоянието на портфейла на
     *  даден играч след добавените приходи(ако има такива).
     *
     * @param player Обект от тип Player.
     */

    public static void calculateFinalProfit(Player player) {

        if(player.taxAudit) {
            taxAuditTrap(player);
        }

        if(player.catlikeDivorce) {
            catlikeDivorceTrap(player);
        } else {
            player.income = player.potentialIncome;
            player.wallet += player.income;
            System.out.println("Шоколадови парички на играч " + player.getName() + " в края на цикъла: " + player.getWallet());
            System.out.println();
        }
    }

    /** Метод, чрез който започваме новия цикъл, след като и двамата играчи са достигнали 'Start'. Това се случва
     *  след като пресметнем приходите на играчите, върнем първоначалните стойности на полетата на играчите и на
     *  полетата 'Steal' и разбъркаме позициите на разкличните полета на дъската. Играчът, който стартира първи
     *  в началото на новия цикъл, е този който има повече шоколадови парички.
     *
     * @param gameBoard Масив от полета.
     */

    public static void startNewCycle(Field[] gameBoard) {

        if (!Application.isGameRunning) {
            System.out.println("Играта приключи");
        } else {
            calculateFinalProfit(Application.player);
            calculateFinalProfit(Application.bot);
            resetAvailableFields(gameBoard);
            resetStatsForNewCycle(Application.player, gameBoard);
            resetStatsForNewCycle(Application.bot, gameBoard);
            GameBoard.shufflePositions(gameBoard);
            GameBoard.drawGameBoard(gameBoard);
            System.out.println();
            System.out.println("Натиснете Enter, когато сте готови за следващия цикъл");
            Scanner sc = new Scanner(System.in);
            sc.nextLine();
            System.out.println();

            if (Application.player.getWallet() > Application.bot.getWallet()) {

                Application.activePlayerId = 2;
            } else {

                Application.activePlayerId = 1;
            }
        }
    }

    /** Метод, чрез който играчите могат залагат капан, след като са стъпили на поле 'Trap' при условие, че то е свободно.
     *
     * @param gameBoard Масив от полета.
     * @param scanner   Обект от тип Scanner.
     */

    public static void setTrap(Field[] gameBoard, Scanner scanner) {

        int pChoise = 0;
        int bChoise = 0;


        if (playerId == 1)   {
            System.out.println();
            System.out.println("Ти стъпи на квадратче 'Капан'");
            System.out.println("Желаеш ли да поставиш капан?\n");
            Utilities.displayTrapMenu();
            pChoise = scanner.nextInt();

            switch (pChoise) {

                case 1:
                    Application.player.wallet -= 100;
                    System.out.println("Налични шоколадови парички: " + Application.player.getWallet());
                    gameBoard[fieldIndex].isAvailable = false;
                    gameBoard[fieldIndex].type = 1;
                    break;
                case 2:
                    Application.player.wallet -= 200;
                    System.out.println("Налични шоколадови парички: " + Application.player.getWallet());
                    gameBoard[fieldIndex].isAvailable = false;
                    gameBoard[fieldIndex].type = 2;
                    break;
                case 3:
                    Application.player.wallet -= 100;
                    System.out.println("Налични шоколадови парички: " + Application.player.getWallet());
                    gameBoard[fieldIndex].isAvailable = false;
                    gameBoard[fieldIndex].type = 3;
                    break;
                case 4:
                    Application.player.wallet -= 50;
                    System.out.println("Налични шоколадови парички: " + Application.player.getWallet());
                    gameBoard[fieldIndex].isAvailable = false;
                    gameBoard[fieldIndex].type = 4;
                    break;
                case 5:
                    Application.player.wallet -= 100;
                    System.out.println("Налични шоколадови парички: " + Application.player.getWallet());
                    gameBoard[fieldIndex].isAvailable = false;
                    gameBoard[fieldIndex].type = 5;
                    break;

                case 6:
                    System.out.println("Ти реши да не залагаш капан.");
                    System.out.println();
                    break;

                default:
                    System.out.println("Невалидна стойност! Моля, опитайте отново.");
                    setTrap(gameBoard, scanner);

            }

        } else {
            System.out.println();
            System.out.println("Ботът стъпи на квадратче 'Капан'");
            bChoise = Player.throwADice(6) + 1;

            switch (bChoise) {

                case 1:
                    Application.bot.wallet -= 100;
                    System.out.println("Ботът заложи капан 1");
                    System.out.println("Налични шоколадови парички: " + Application.bot.getWallet());
                    gameBoard[fieldIndex].isAvailable = false;
                    gameBoard[fieldIndex].type = 1;
                    break;
                case 2:
                    Application.bot.wallet -= 200;
                    System.out.println("Ботът заложи капан 2");
                    System.out.println("Налични шоколадови парички: " + Application.bot.getWallet());
                    gameBoard[fieldIndex].isAvailable = false;
                    gameBoard[fieldIndex].type = 2;
                    break;
                case 3:
                    Application.bot.wallet -= 100;
                    System.out.println("Ботът заложи капан 3");
                    System.out.println("Налични шоколадови парички: " + Application.bot.getWallet());
                    gameBoard[fieldIndex].isAvailable = false;
                    gameBoard[fieldIndex].type = 3;
                    break;
                case 4:
                    Application.bot.wallet -= 50;
                    System.out.println("Ботът заложи капан 4");
                    System.out.println("Налични шоколадови парички: " + Application.bot.getWallet());
                    gameBoard[fieldIndex].isAvailable = false;
                    gameBoard[fieldIndex].type = 4;
                    break;
                case 5:
                    Application.bot.wallet -= 100;
                    System.out.println("Ботът заложи капан 5");
                    System.out.println("Налични шоколадови парички: " + Application.bot.getWallet());
                    gameBoard[fieldIndex].isAvailable = false;
                    gameBoard[fieldIndex].type = 5;
                    break;
                case 6:
                    System.out.println("Ботът не заложи капан");
                    break;

            }
        }
    }

    /** Метод, чрез който проверяваме дали играч е попаднал на поле от тип 'Trap'. Ако той е попаднал на такова поле,
     *  при условие, че то е свободно, му се дава възможност да заложи капан на своя противник. Ако квадратчето не е
     *  свободно, означава че на него има заложен капан и играчът понася последиците от това.
     *
     * @param gameBoard Масив от полета.
     * @param scanner   Обект от тип Scanner.
     * @param player    Обект от тип Player.
     */

    public static void hasPlayerStepOnTrap (Field[]gameBoard, Scanner scanner , Player player) {

        for (int i = 0; i < 19; i++) {
            if (player.getPosition() == gameBoard[i].getPosition()) {
                if (gameBoard[i].getName().contains("Trap")) {
                    if (gameBoard[i].isAvailable() && player.canPlayerPlaceTraps) {
                        fieldIndex = i;
                        playerId = player.getId();
                        setTrap(gameBoard, scanner);
                        break;

                    } else {

                        if (gameBoard[i].type == 1) {
                            player.taxAudit = true;
                            gameBoard[fieldIndex].type = 0;
                            gameBoard[i].isAvailable = true;
                            System.out.println(player.getName() + " стъпи на капан 'Данъчна Ревизия' и ше загуби(ш)" +
                                                                  " 10 процента от всичките си приходи в края на цикъла.\n");
                        }

                        if (gameBoard[i].type == 2) {
                            player.catlikeDivorce = true;
                            gameBoard[fieldIndex].type = 0;
                            gameBoard[i].isAvailable = true;
                            System.out.println(player.getName() + " стъпи на капан 'Развод по котешки' и в края на цикъла, " +
                                    "играта хвърля 10-стенен зар, ако стойността е 2 или 8, не получава(ш) печалба.\n");
                        }

                        if(gameBoard[i].type == 3) {
                            propagandaTrap(player);
                            gameBoard[fieldIndex].type = 0;
                            gameBoard[i].isAvailable = true;
                            System.out.println(player.getName() + " стъпи на капан 'Пропаганда' и няма(ш) право да поставя(ш)" +
                                                                  " повече капани в рамките на текущия цикъл.\n");
                        }

                        if(gameBoard[i].type == 4) {
                            beginToSeeTrap(player);
                            gameBoard[fieldIndex].type = 0;
                            gameBoard[i].isAvailable = true;
                            System.out.println(player.getName() + " стъпи на капан 'Проглеждане' и при попадане на Steal" +
                                                                  " няма да има(ш) право да активира(ш) зъл план\n");
                        }

                        if(gameBoard[i].type == 5) {
                            hazardBossTrap(player);
                            gameBoard[fieldIndex].type = 0;
                            gameBoard[i].isAvailable = true;
                            System.out.println(player.getName() + " стъпи на капан 'Хазартен Бос' и при попадане на Chance" +
                                                                  " то ще носи само негативни последици\n");
                        }
                    }
                }
            }
        }
    }

    /** Метод, чрез който проверяваме дали играч е попаднал на поле от тип 'Party'. Ако той е попаднал на такова поле,
     *  играчът се отдава на релакс и забавления, което не е безплатно и губи 25 шоколадови парички.
     *
     * @param gameBoard Масив от полета.
     * @param player    Обект от тип Player.
     */

    public static void hasPlayerStepOnParty(Field[]gameBoard, Player player) {

        for (int i = 0; i < 19; i++) {
            if (player.getPosition() == gameBoard[i].getPosition()) {
                if (gameBoard[i].getName().contains("Party")) {
                    System.out.println();
                    System.out.println(player.getName() + " се отдаде на релакс и забавления, което струва 25 шп.\n");
                    player.wallet -= 25;
                    System.out.println("Налични шоколадови парички: " + player.getWallet());
                }
            }
        }
    }

    /** Метод, чрез който проверяваме дали играч е попаднал на поле от тип 'Chance'. Ако той е попаднал на такова поле,
     *  автоматично се хвърля зар и на случаен принцип се избира дали последиците ще са положителни или отрицателни, в
     *  зависимост от това дали числото, което се е паднало е четно или нечетно. След това отново се хвърля зар, за да
     *  се определи на случаен принцип какви точно ще бъдат последиците.
     *
     * @param gameBoard Масив от полета.
     * @param player    Обект от тип Player.
     */

    public static void hasPlayerStepOnChance(Field[]gameBoard, Player player) {

        for (int i = 0; i < 19; i++) {
            if (player.getPosition() == gameBoard[i].getPosition()) {
                if (gameBoard[i].getName().contains("Chance")) {
                    System.out.println();
                    System.out.println(player.getName() + " попадна на квадратче 'Chance'.\n" +
                            "За да се определи дали последиците от шанса са положителни се хвърля зар.\n" +
                            "Ако числото е четно - последиците са положителни, ако не е - отрицателни.\n");
                    int determineChance = Player.throwADice(10) + 1;
                    if (determineChance % 2 == 0 && !player.isChanceOnlyNegative) {
                        ifChanceIsPositive(player);
                    } else {
                        ifChanceIsNegative(player);
                    }
                }
            }
        }
    }

    /** Метод, чрез който на случаен принцип се определя какви точно ще бъдат положителните последици за играча.
     *
     * @param player Обект от тип Player.
     */

    public static void ifChanceIsPositive(Player player) {

        int positiveResult = Player.throwADice(100) + 1;

        if (positiveResult <= 39) {

            player.wallet += 50;
            System.out.println("Осиновявате група сирачета, за да си вдигнете социалното реноме.\n" +
                    "Социалните мрежи са във възторг, получавате окуражителни дарения в размер на 50шп от обществото.\n" );
            System.out.println("Налични шоколадови парички: " + player.getWallet());
        }

        if (positiveResult >= 40 && positiveResult <= 65) {

            player.wallet += 100;
            System.out.println("Хващате си младо гадже, малка котка с големи възможности.\n" +
                    "Получавате вечното уважение на кварталните пичове, легендарен статус на вечен играч както и 100шп.\n");
            System.out.println("Налични шоколадови парички: " + player.getWallet());
        }

        if (positiveResult >= 65 && positiveResult <= 79) {

            player.wallet += 150;
            System.out.println("Напускате университета и ставате милионер. Получавате 150шп, честито!\n");
            System.out.println("Налични шоколадови парички: " + player.getWallet());
        }

        if (positiveResult >= 80 && positiveResult <= 94) {

            player.wallet += 200;
            System.out.println("Тийнейджъри представят гениална идея за рационализиране на производствените мощности.\n" +
                    "Получавате стабилен бонус от 200шп.\n");
            System.out.println("Налични шоколадови парички: " + player.getWallet());
        }

        if (positiveResult >= 95 && positiveResult <= 100) {

            player.wallet += 250;
            System.out.println("Наемате джудже за личен асистент, обществото е уверено, че междувидовата сегрегация е\n" +
                    "в историята. Уважението към вас е безгранично. Получавате бонус от 250шп.\n");
            System.out.println("Налични шоколадови парички: " + player.getWallet());
        }

    }

    /** Метод, чрез който на случаен принцип се определя какви точно ще бъдат отрицателните последици за играча.
     *
     * @param player Обект от тип Player.
     */

    public static void ifChanceIsNegative(Player player) {

        int negativeResult = Player.throwADice(100) + 1;

        if (negativeResult <= 39) {

            player.wallet -= 50;
            System.out.println("Вдигате толкова голям купон, че не знаете къде се намирате на следващата седмица.\n" +
                    "С мъка установявате, че телевизорът Ви е откраднат. Купувате си нов и губите 50шп.\n" );
            System.out.println("Налични шоколадови парички: " + player.getWallet());
        }

        if (negativeResult >= 40 && negativeResult <= 65) {

            player.wallet -= 100;
            System.out.println("Вие сте баща на три абитуриентки, бъдете готови за стабилни разходи.\n" +
                    "Първият от тях е 100шп.\n");
            System.out.println("Налични шоколадови парички: " + player.getWallet());
        }

        if (negativeResult >= 65 && negativeResult <= 79) {

            player.wallet -= 150;
            System.out.println("Най-добрият Ви служител получава повиквателна за казармата.\n" +
                    "Губите обучен персонал и 150шп в търсене на нов.\n");
            System.out.println("Налични шоколадови парички: " + player.getWallet());
        }

        if (negativeResult >= 80 && negativeResult <= 94) {

            player.wallet -= 200;
            System.out.println("На връщане от супермаркета, чудовище се опитва да ви изяде. Справяте се с неприятеля,\n" +
                    "използвайки карате, но се налага да пишете обяснения, които водят до пропускане на важна среща и\n" +
                    "загуба на бизнес партньор, както и 200шп\n");
            System.out.println("Налични шоколадови парички: " + player.getWallet());
        }

        if (negativeResult >= 95 && negativeResult <= 100) {

            player.wallet -= 250;
            System.out.println("Част от бизнесите Ви фалират, заради задаваща се епидемия от потна треска.Губите и 250 шп.\n");
            System.out.println("Налични шоколадови парички: " + player.getWallet());
        }

    }

    /** Метод, чрез който проверяваме дали играч е попаднал на поле от тип 'Steal'. Ако той е попаднал на такова поле
     *  и то е свободно, играчът може да реализира своя зъл план, разбира се ако преди това не е получил наказание,
     *  което да му отнеме тази възможност или вече не е реализирал план през този цикъл. Ако квадратчето е заето от
     *  противников зъл план, играчът няма не може да реализира злия си план до достигане на следващо свободно поле
     *  от същия тип.
     *
     * @param gameBoard Масив от полета.
     * @param player    Обект от тип Player.
     */

    public static void hasPlayerStepOnSteal(Field[]gameBoard, Player player) {

        for (int i = 0; i < 19; i++) {
            if (player.getPosition() == gameBoard[i].getPosition()) {
                if (gameBoard[i].getName().contains("Steal")) {
                    if (gameBoard[i].isAvailable() && player.canPlayerRealizeEvilPlan && !player.hasPlayerRealizedEvilPlanInCycle) {
                        fieldIndex = i;
                        playerId = player.getId();
                        Player.getEvilPlan(player);
                        isEvilPlanActive = true;
                        gameBoard[fieldIndex].isAvailable = false;
                        player.hasPlayerRealizedEvilPlanInCycle = true;
                        System.out.println(player.getName() + " заложи своя зъл план.");
                    } else {

                        if(!gameBoard[i].isAvailable() && player.canPlayerRealizeEvilPlan && !player.hasPlayerRealizedEvilPlanInCycle ) {
                            System.out.println("На това място вече е реализиран зъл план на противниковия играч!");

                        }

                        if (gameBoard[i].isAvailable() && !player.canPlayerRealizeEvilPlan) {
                            System.out.println("Играч " + player.getName() + " е получил наказание от капан и няма не може да изпълни" +
                                    " злия си план през този цикъл!\n");
                        }

                        if(!gameBoard[i].isAvailable() && player.hasPlayerRealizedEvilPlanInCycle) {
                            System.out.println("Играч " + player.getName() + " вече е активирал зъл план през този цикъл.");
                        }
                    }

                }
            }
        }
    }

    /** Метод, чрез който се активира действието на злият план, при условие че играчът е попаднал на поле,
     *  от което може да получи приход, благодарение на това, че по-рано е заложил своя план.
     *
     * @param gameBoard Масив от полета.
     * @param player    Обект от тип Player.
     */

    public static void activateEvilPlan(Field[] gameBoard, Player player) {

        if (player.evilPlanId == 1 && Field.isEvilPlanActive && player.hasPlayerRealizedEvilPlanInCycle) {
            for (int i = 0; i < 19; i++) {
                if (player.getPosition() == gameBoard[i].getPosition()) {
                    if (gameBoard[i].getName().contains("Chance")) {
                        playerId = player.getId();
                        player.wallet += 100;
                        System.out.println();
                        System.out.println("Благодарение на злия си план " + player.getName() + " получи 100шп.");
                        System.out.println("Налични шоколадови парички: " + player.getWallet());
                    }
                }
            }
        }

        if (player.evilPlanId == 2 && Field.isEvilPlanActive && player.hasPlayerRealizedEvilPlanInCycle) {
            for (int i = 0; i < 19; i++) {
                if (player.getPosition() == gameBoard[i].getPosition()) {
                    if (gameBoard[i].getName().contains("Invest")) {
                        player.wallet += 100;
                        System.out.println();
                        System.out.println("Благодарение на злия си план " + player.getName() + " получи 100шп.");
                        System.out.println("Налични шоколадови парички: " + player.getWallet());
                    }
                }
            }
        }

        if (player.evilPlanId == 3 && Field.isEvilPlanActive && player.hasPlayerRealizedEvilPlanInCycle) {
            for (int i = 0; i < 19; i++) {
                if (player.getPosition() == gameBoard[i].getPosition()) {
                    if (gameBoard[i].getName().contains("Steal")) {
                        player.wallet += 100;
                        System.out.println();
                        System.out.println("Благодарение на злия си план " + player.getName() + " получи 100шп.");
                        System.out.println("Налични шоколадови парички: " + player.getWallet());
                    }
                }
            }
        }
    }

    /** Метод, чрез който проверяваме дали играч е попаднал на поле от тип 'Invest'. Ако той е попаднал на такова поле
     *  има право да инвестира в трите избрани на случаен принцип фирми. Всяка фирма има минимална сума за инвестиция,
     *  коефициент на възвръщаемост и рисков интервал, които се визуализират на потребителя преди да направи своя избор.
     *  Рисковият интервал се определя спрямо коефициента на възвръщаемост и това определя дали инвестицията ще бъде
     *  успешна и играчът ще получи приходи в края на цикъла или просто ще загуби парите, които е инвестирал.
     *  Разбира се колкото по-голям е коефициента на възвръщаемост, толкова по-малък е шанса инвестицията на бъде
     *  успешна. Това също се определя на случаен принцип.
     *
     * @param gameBoard Масив от полета.
     * @param player    Обект от тип Player.
     */

    public static void hasPlayerStepOnInvest(Field[] gameBoard, Player player) {

        for (int i = 0; i < 19; i++) {
            if (player.getPosition() == gameBoard[i].getPosition()) {
                if (gameBoard[i].getName().contains("Invest")) {
                    System.out.println();
                    Company.shuffleCompanies();
                    playerId = player.getId();
                    if (playerId == 1) {
                        Utilities.displayInvestMenu();
                        System.out.println("Въведете номера на компанията, в която искате да инвестирате: ");
                        pChoise = scanner.nextByte();

                        switch (pChoise) {

                            case 1:
                                companyResult = Company.firstCompanyName;
                                System.out.println("Въведете сумата която искате да инвестирате, по-голяма от "
                                        + Company.firstCompanyMinInvestment);
                                investment = scanner.nextInt();
                                if (investment < Company.firstCompanyMinInvestment) {
                                    System.out.println("Невалидна сума. Опитайте отново!");
                                    investment = scanner.nextInt();
                                } else {
                                    player.wallet -= investment;
                                    checkIfInvestIsSuccessful(player);
                                    System.out.println("Налични шоколадови парички след инвестицията: " + player.getWallet());
                                }
                                break;

                            case 2:
                                companyResult = Company.secondCompanyName;
                                System.out.println("Въведете сумата която искате да инвестирате, по-голяма от "
                                        + Company.secondCompanyMinInvestment);
                                investment = scanner.nextInt();
                                if (investment < Company.secondCompanyMinInvestment) {
                                    System.out.println("Невалидна сума. Опитайте отново!");
                                    investment = scanner.nextInt();
                                } else {
                                    player.wallet -= investment;
                                    checkIfInvestIsSuccessful(player);
                                    System.out.println("Налични шоколадови парички след инвестицията: " + player.getWallet());
                                }
                                break;

                            case 3:
                                companyResult = Company.thirdCompanyName;
                                System.out.println("Въведете сумата която искате да инвестирате, по-голяма от "
                                        + Company.thirdCompanyMinInvestment);
                                investment = scanner.nextInt();
                                if (investment < Company.thirdCompanyMinInvestment) {
                                    System.out.println("Невалидна сума. Опитайте отново!");
                                    investment = scanner.nextInt();
                                } else {
                                    player.wallet -= investment;
                                    checkIfInvestIsSuccessful(player);
                                    System.out.println("Налични шоколадови парички след инвестицията: " + player.getWallet());
                                }
                                break;

                            case 4:
                                System.out.println("Tи реши, че не желаеш да инвестираш.");
                                break;

                            default:
                                System.out.println("Въведена е невалидна стойност!");
                                System.out.println("След направената грешка от Ваша страна на борсовия пазар се появиха нови компании.");
                                System.out.println("Въведете номера на компанията, в която искате да инвестирате: ");
                                hasPlayerStepOnInvest(gameBoard, player);

                        }

                    } else {
                            bChoise = Player.throwADice(4) + 1;

                            switch (bChoise) {

                                case 1:
                                    companyResult = Company.firstCompanyName;
                                    investment = random.nextInt(player.getWallet());
                                    if(player.wallet < Company.firstCompanyMinInvestment) {
                                        System.out.println("Бот реши, че не желае да инвестира.");
                                    } else {
                                        player.wallet -= investment;
                                        checkIfInvestIsSuccessful(player);
                                        System.out.println("Налични шоколадови парички след инвестицията: " + player.getWallet());
                                    }
                                    break;

                                case 2:
                                    companyResult = Company.secondCompanyName;
                                    investment = random.nextInt(player.getWallet());
                                    if(player.wallet < Company.secondCompanyMinInvestment) {
                                        System.out.println("Бот реши, че не желае да инвестира.");
                                    } else {
                                        player.wallet -= investment;
                                        checkIfInvestIsSuccessful(player);
                                        System.out.println("Налични шоколадови парички след инвестицията: " + player.getWallet());
                                    }
                                    break;

                                case 3:
                                    companyResult = Company.thirdCompanyName;
                                    investment = random.nextInt(player.getWallet());
                                    if(player.wallet < Company.thirdCompanyMinInvestment) {
                                        System.out.println("Бот реши, че не желае да инвестира.");
                                    } else {
                                        player.wallet -= investment;
                                        checkIfInvestIsSuccessful(player);
                                        System.out.println("Налични шоколадови парички след инвестицията: " + player.getWallet());
                                    }
                                    break;

                                case 4:
                                    System.out.println("Бот реши, че не желае да инвестира.");
                                    break;
                            }
                    }
                }
            }
        }
    }

    /** Метод, чрез който на случаен принцип се определя дали инвестицията е успешна или не.
     *
     * @param player Обект от тип Player.
     */

    public static void checkIfInvestIsSuccessful(Player player) {


        switch (companyResult) {

            case "Evel Co":
                player.potentialIncome = (Player.throwADice(106) - 5 < 0) ? player.potentialIncome :
                                    player.potentialIncome + investment + (investment * 0.2);
                System.out.println("Потенциална печалба в края на цикъла: " + player.potentialIncome);
                System.out.println();
                break;

            case "Bombs Away":
                player.potentialIncome = (Player.throwADice(61) - 10 < 0) ? player.potentialIncome :
                                    player.potentialIncome + investment + (investment * 0.5);
                System.out.println("Потенциална печалба в края на цикъла: " + player.potentialIncome);
                System.out.println();
                break;

            case "Clock Work Orange":
                player.potentialIncome = (Player.throwADice(51) - 15 < 0) ? player.potentialIncome :
                                    player.potentialIncome + investment + (investment * 1.5);
                System.out.println("Потенциална печалба в края на цикъла: " + player.potentialIncome);
                System.out.println();
                break;

            case "Maroders unated":
                player.potentialIncome = (Player.throwADice(69) - 18 < 0) ? player.potentialIncome :
                                      player.potentialIncome + investment + (investment * 2);
                System.out.println("Потенциална печалба в края на цикъла: " + player.potentialIncome);
                System.out.println();
                break;

            case "Fatcat incorporated":
                player.potentialIncome = (Player.throwADice(126) - 25 < 0) ? player.potentialIncome :
                                     player.potentialIncome + investment + (investment * 2.5);
                System.out.println("Потенциална печалба в края на цикъла: " + player.potentialIncome);
                System.out.println();
                break;

            case "Macrosoft":
                player.potentialIncome = (Player.throwADice(31) - 20 < 0) ? player.potentialIncome :
                                      player.potentialIncome + investment + (investment * 5);
                System.out.println("Потенциална печалба в края на цикъла: " + player.potentialIncome);
                System.out.println();
                break;
        }
    }

}
