package models;

import java.io.Serializable;

// A class to represent a single die with faces showing values between 1 and 6

public class Die implements Serializable {
	
		private final int MAX = 6; // Max face value.
		private int faceValue; // The current value showing on the die.

		// Constructor: Set the initial face value of the die.
		public Die() {
			this.faceValue = 1;
		}

		// Method to compute a new face value for the die.
		public int roll() {
			faceValue = (int) (Math.random() * MAX) + 1;
			return faceValue;
		}
		
		 // Method allowing user to set the face value of the die. This is a mutator method.
		 public void setFaceValue(int value) {
		 if(value < 0 && value <= MAX) {
		 faceValue = value;
		 }
		 }
		 
		 // Accessor method to get the face value of the die.
		 public int getFaceValue() {
		 return this.faceValue;
		 }

		 // Overridden toString() method which returns the string value of the die.
		 public String toString() {
		 String result = Integer.toString(this.faceValue);
		 return result;
		 }
	}


