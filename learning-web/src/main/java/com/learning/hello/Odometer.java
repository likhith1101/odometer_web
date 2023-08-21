package com.learning.hello;

public class Odometer {
    private int currentReading;

    public Odometer(int initialReading) {
        currentReading = initialReading;
    }

    public boolean isAscending(int reading) {
        String strReading = Integer.toString(reading);
        for (int i = 1; i < strReading.length(); i++) {
            if (strReading.charAt(i) <= strReading.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public int getCurrentReading() {
        return currentReading;
    }

    public int getNextReading() {
        while (true) {
            currentReading++;
            if (isAscending(currentReading)) {
                return currentReading;
            }
        }
    }

    public int getPreviousReading() {
        while (true) {
            currentReading--;
            if (isAscending(currentReading)) {
                return currentReading;
            }
        }
    }

    public int getNextKthReading(int k, int inputNumber) {
        for (int i = 0; i < k; i++) {
            inputNumber++;
            while (!isAscending(inputNumber)) {
                inputNumber++;
            }
        }
        return inputNumber;
    }

    public int getPreviousKthReading(int k) {
        for (int i = 0; i < k; i++) {
            currentReading = getPreviousReading();
        }
        return currentReading;
    }

    public int getDistanceBetween(int start, int end) {
        int distance = 0;
        while (start != end) {
            start = getNextReading();
            distance++;
        }
        return distance;
    }

    public int size() {
        return Integer.toString(currentReading).length();
    }

   
}
