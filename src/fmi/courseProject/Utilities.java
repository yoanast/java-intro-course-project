package fmi.courseProject;

public class Utilities {

    /** Метод, чрез който визуализираме менюто с видовете капани, от които играчът може да избира след като
     *  попадне на поле 'Trap'.
     *
     */

    public static void displayTrapMenu() {

        System.out.println("[1] Данъчна ревизия: -10% от всички приходи в края на цикъла, цена: 100шп\n" +
                           "[2] Развод по котешки: В края на цикъла , играта хвърля 10-стенен зар, " +
                           "ако стойността е 2 или 8, не получавате печалба от цикъла, цена: 200шп\n" +
                           "[3] Пропаганда: Не може да се поставят повече капани в рамките на текущия цикъл, цена 100шп\n" +
                           "[4] Проглеждане: При попадане на квадратче Steal, играчът губи право да реализира зъл план, цена: 50шп\n" +
                           "[5] Хазартен бос: Следващото квадратче Шанс носи само негативни последици, цена: 100шп\n" +
                           "[6] Не, благодаря. Не вярвам в злото.");


    }

    /** Метод, чрез който визуализираме менюто с генерираните на случаен принцип три компании, от които играчът
     *  има право да избира след като попадне на поле 'Invest'.
     *
     */

    public static void displayInvestMenu() {
        System.out.println("Изберете в коя компания ще инвестирате: ");
        System.out.println("[1] " + Company.firstCompanyName + " | min: " + Company.firstCompanyMinInvestment +
                " | risk/reward: " + Company.firstCompanyReturnCoefficient);
        System.out.println("[2] " + Company.secondCompanyName + " | min: " + Company.secondCompanyMinInvestment +
                " | risk/reward: " + Company.secondCompanyReturnCoefficient);
        System.out.println("[3] " + Company.thirdCompanyName + " | min: " + Company.thirdCompanyMinInvestment +
                " | risk/reward: " + Company.thirdCompanyReturnCoefficient);
        System.out.println("[4] Не желая да инвестирам.");
    }

}
