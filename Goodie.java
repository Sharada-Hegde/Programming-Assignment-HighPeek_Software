package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Goodie {

    static int minDiff(int array[], int N, int M) {
        int result = Integer.MAX_VALUE;
        Arrays.sort(array);
        for (int i = 0; i <= N - M; i++)
            result = Math.min(result, array[i + M - 1] - array[i]);
        return result;
    }

    static int findelements(int res, int array[], int N, int M) {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i <= N; i++) {
            result = Math.min(result, array[i + M - 1] - array[i]);
            if (res == result)
                return i;
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {


        //Declare variables
        String inputFilePath = "C:/Users/Anita Hegde/IdeaProjects/SharadaHegde/src/main/resources/input.txt";
        String outputFilePath = "C:/Users/Anita Hegde/IdeaProjects/SharadaHegde/src/main/resources/output.txt";
        List<String> inputFileLines = readFileLines(inputFilePath);
        List<String> outputFileLines = new ArrayList<>();


        //Constants
        String splitDelimeter = ":";

        //Prepare input vars from file
        int priceArray[] = new int[inputFileLines.size()];
        String items[] = new String[inputFileLines.size()];

        for (int i = 0; i < inputFileLines.size(); i++) {

            items[i] = inputFileLines.get(i).trim();
            priceArray[i] = Integer.parseInt(inputFileLines.get(i).trim().split(splitDelimeter)[1].trim());

        }

        //Process user input using file data
        int N = priceArray.length;

        //Get Input from User
        String noOfEmployeesQ = "Enter the number of employees";
        System.out.println(noOfEmployeesQ);
        Scanner s = new Scanner(System.in);
        int M = s.nextInt();

        //add QnA to o/p file
        outputFileLines.add(noOfEmployeesQ);
        outputFileLines.add(String.valueOf(M));

        int result = minDiff(priceArray, N, M);

        //Processing o/p - 1
        String noOfEmployeesOP = "\nNumber of the employees:" + M;
        System.out.println(noOfEmployeesOP);

        //add o/p - 1 to o/p file
        outputFileLines.add(noOfEmployeesOP);

        //Processing o/p - 2
        int startindex = findelements(result, priceArray, N, M);

        String resultOP = "\nHere the goodies that are selected for distribution are:";
        System.out.println(resultOP);

        //add o/p - 2 to o/p file
        outputFileLines.add(resultOP);

        for (int i = startindex; i < startindex + M; i++) {
            System.out.println(items[i]);
            outputFileLines.add(items[i]);
        }

        //Processing o/p - 3
        String finalOP = "\nAnd the difference between the chosen goodies with highest price and the lowest price is:" + result;
        System.out.println(finalOP);
        //add o/p - 3 to o/p file
        outputFileLines.add(finalOP);

        //Write Output array to output file
        writeToOutputTxtFile(outputFilePath, outputFileLines);
    }

    private static ArrayList<String> readFileLines(String filepath) throws FileNotFoundException, IOException {
        File fp = new File(filepath);
        FileReader fr = new FileReader(fp);
        BufferedReader br = new BufferedReader(fr);

        ArrayList<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }

        fr.close();
        return lines;
    }

    private static void writeToOutputTxtFile(String filePath, List<String> lines) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(filePath, "UTF-8");
        lines.forEach(curLineItem -> {
            writer.println(curLineItem.trim());
        });
        writer.close();
    }
}


