package com.fansquad.ffdiatips.skinrewards.datazone;

import java.util.ArrayList;
import java.util.List;

public class QMatrixCore {

    public static List<QUnitNode> getQuestions(String category) {
        List<QUnitNode> list = new ArrayList<>();

        if (category.equals("Maths Quiz")) {
            list.add(new QUnitNode("What is 7+3?", new String[]{"7", "10", "9", "13"}, 1));
            list.add(new QUnitNode("What is 4×2?", new String[]{"6", "8", "10", "9"}, 1));
            list.add(new QUnitNode("What is 12−5?", new String[]{"8", "9", "7", "6"}, 2));
            list.add(new QUnitNode("What is 2×4?", new String[]{"6", "8", "10", "4"}, 1));
            list.add(new QUnitNode("What is the square of 4?", new String[]{"20", "22", "16", "12"}, 2));
            list.add(new QUnitNode("What is 25% of 40?", new String[]{"8", "10", "15", "20"}, 1));
            list.add(new QUnitNode("What is the value of π (pi) approximately?", new String[]{"3.16", "3.14", "4.00", "5.50"}, 1));
            list.add(new QUnitNode("If 5 kilojoules are equal to 4170 grams of TNT, how much is 10 kilojoules?", new String[]{"30g", "60g", "90g", "120g"}, 1));
            list.add(new QUnitNode("What is the smallest prime number?", new String[]{"1", "2", "3", "4"}, 1));
            list.add(new QUnitNode("What is 9×3?", new String[]{"30", "27", "28", "36"}, 1));
        } else if (category.equals("General Knowledge")) {
            list.add(new QUnitNode("Which is the largest ocean on Earth?", new String[]{"Atlantic", "Indian", "Pacific", "Arctic"}, 2));
            list.add(new QUnitNode("Which country has the most population?", new String[]{"India", "USA", "China", "Japan"}, 2));
            list.add(new QUnitNode("Which is the capital of India?", new String[]{"Rome", "Berlin", "Paris", "Delhi"}, 3));
            list.add(new QUnitNode("Which planet is known as the Red Planet?", new String[]{"Venus", "Jupiter", "Mars", "Earth"}, 2));
            list.add(new QUnitNode("Which is the longest river in the world?", new String[]{"Yangtze", "Nile", "Amazon", "Tapi"}, 2));
        } else if (category.equals("Color Puzzle")) {
            list.add(new QUnitNode("Select right answer", new String[]{"Red", "Yellow", "Pink", "White"}, 1));
            list.add(new QUnitNode("Select right answer", new String[]{"Red", "White", "Pink", "Yellow"}, 2));
            list.add(new QUnitNode("Select right answer", new String[]{"Yellow", "Pink", "White", "Red"}, 1));
            list.add(new QUnitNode("Select right answer", new String[]{"Yellow", "Red", "Pink", "White"}, 1));
            list.add(new QUnitNode("Select right answer", new String[]{"Yellow", "White", "Pink", "Green"}, 3));
        }

        return list;
    }
}
