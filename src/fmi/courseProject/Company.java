package fmi.courseProject;

import java.util.ArrayList;
import java.util.Collections;

public class Company {

    private int id;
    private String name;
    private int minInvestment;
    private double returnCoefficient;

    static String firstCompanyName;
    static int firstCompanyMinInvestment;
    static double firstCompanyReturnCoefficient;
    static int firstCompanyId;

    static String secondCompanyName;
    static int secondCompanyMinInvestment;
    static double secondCompanyReturnCoefficient;
    static int secondCompanyId;

    static String thirdCompanyName;
    static int thirdCompanyMinInvestment;
    static double thirdCompanyReturnCoefficient;
    static int thirdCompanyId;

    static ArrayList<Company> companies = new ArrayList<>();

    public Company(int id, String name, int minInvestment, double returnCoefficient) {
        this.id = id;
        this.name = name;
        this.minInvestment = minInvestment;
        this.returnCoefficient = returnCoefficient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinInvestment() {
        return minInvestment;
    }

    public void setMinInvestment(int minInvestment) {
        this.minInvestment = minInvestment;
    }

    public double getReturnCoefficient() {
        return returnCoefficient;
    }

    public void setReturnCoefficient(double returnCoefficient) {
        this.returnCoefficient = returnCoefficient;
    }

    /** Метод, чрез който инициализираме компации и ги добавяме в списък.
     *
     */

    public static void initCompanies() {

        Company company0 = new Company(0,"Evel Co", 500, 0.2);
        Company company1 = new Company(1,"Bombs Away", 400, 0.5);
        Company company2 = new Company(2,"Clock Work Orange", 300, 1.5);
        Company company3 = new Company(3,"Maroders unated", 200, 2);
        Company company4 = new Company(4,"Fatcat incorporated", 100, 2.5);
        Company company5 = new Company(5,"Macrosoft", 50, 5);
        companies.add(company0);
        companies.add(company1);
        companies.add(company2);
        companies.add(company3);
        companies.add(company4);
        companies.add(company5);
    }

    /** Метод, чрез който разбъркваме списъка и на случаен принцип взимаме 3 компании, които да визуализираме и в които
     *  да дадем възможност на играч да инвестира, след като попадне на поле 'Invest'.
     *
     */

    public static void shuffleCompanies() {
        Collections.shuffle(companies);

        firstCompanyId = companies.get(0).getId();
        firstCompanyName = companies.get(0).getName();
        firstCompanyMinInvestment = companies.get(0).getMinInvestment();
        firstCompanyReturnCoefficient = companies.get(0).getReturnCoefficient();

        secondCompanyId = companies.get(1).getId();
        secondCompanyName = companies.get(1).getName();
        secondCompanyMinInvestment = companies.get(1).getMinInvestment();
        secondCompanyReturnCoefficient = companies.get(1).getReturnCoefficient();

        thirdCompanyId = companies.get(2).getId();
        thirdCompanyName = companies.get(2).getName();
        thirdCompanyMinInvestment = companies.get(2).getMinInvestment();
        thirdCompanyReturnCoefficient = companies.get(2).getReturnCoefficient();
    }

}
