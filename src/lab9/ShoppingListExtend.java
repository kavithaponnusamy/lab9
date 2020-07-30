package lab9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ShoppingListExtend {
	private static Scanner scnr; 
	private static Map< String,Double> items=new TreeMap<>();
	private static List<String> orderNames=new ArrayList<>();
	private static List<Double> orderPrice=new ArrayList<>();
	private static List<Integer> orderQty=new ArrayList<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		scnr=new Scanner(System.in);
		String itemName="";
		boolean yesNo;
		Double itemPrice,avgCost;
		int highCostIndex;
		int lowCostIndex;
		 
		fillItemsMap();
		System.out.println("Welcome to Walmart!");
		printMenu();
		do {
			//System.out.println("What item would you like to order?");
			//itemName=scnr.nextLine();
			itemName=getItem(scnr,"What item would you like to order?");
			itemPrice=items.get(itemName);
			System.out.println("Adding "+itemName+" to cart at $"+itemPrice);
			orderNames.add(itemName);
			orderPrice.add(itemPrice);
			yesNo=Validator.getYesNo(scnr,"Would you like to order anything else(y/n)?");
			if(yesNo) {			
			printMenu();
			}
		}while(yesNo);	
		System.out.println("Thanks for your order!\nHere's what you got:");
		for(int i=0;i<orderNames.size();i++)
		{
			System.out.printf("%-15s%-15f\n",orderNames.get(i),orderPrice.get(i));
			
		}
		avgCost=getAverage(orderPrice);
		System.out.println("Average price per item in order was "+avgCost);
		highCostIndex=getHighestCostIndex(orderPrice);
		System.out.println("Index of highest cost item: "+highCostIndex);
		lowCostIndex=getLowestCostIndex(orderPrice);
		System.out.println("Index of lowest cost item: "+lowCostIndex);
		
	}
	private static void fillItemsMap() {
		items.put("Apple",.99);
		items.put("Orange",1.99);
		items.put("Cherry",1.99);
		items.put("Tissue",4.99);
		items.put("Wipes",7.99);
		items.put("Tide",12.99);
		items.put("Fruit Snack",6.99);
		items.put("Cereal",3.99);		
	}
	private static void printMenu() {
		System.out.printf("%-15s","Item");
		System.out.printf("%-15s\n","Price");
		System.out.println("==============================");
		for(Map.Entry<String,Double> entry : items.entrySet()) {
			System.out.printf("%-15s%-15f\n",entry.getKey(),entry.getValue());
		}
		 
	}
	private static String getItem(Scanner scnr,String prompt) {
		String itemName;
		do {
			System.out.println(prompt);
			itemName=scnr.nextLine();
			//itemName=capitalize(itemName);//Added
			if(items.containsKey(itemName)) {
				return itemName;				
			}
			else {
				System.out.println("Sorry, we don't have those. Please try again.");
				printMenu();				
			}		
			}while(true);
	}
	private static double getAverage(List<Double> price) {
		double sum=0,avg;
		for(double val:price) {
			sum+=val;
		}
		avg=sum/price.size();
		return avg;
	}
	private static int getHighestCostIndex(List<Double> price) {
		double max=price.get(0);
		int index=0;
		for(int i=1;i<price.size();i++) {
			if(max<price.get(i)) {
				max=price.get(i);
				index=i;
			}			
		}return index+1;
	}

	private static int getLowestCostIndex(List<Double> price) {
		double min=price.get(0);
		int index=0;
		for(int i=1;i<price.size();i++) {
			if(min>price.get(i)) {
				min=price.get(i);
				index=i;
			}			
		}return index+1;
	}

	private static String capitalize(String str) {
		String itemNameTemp="";
		String[] ss = str.split(" ");
		for (int i=0;i<ss.length;i++) {
			itemNameTemp=ss[i].substring(0,1).toUpperCase()+ss[i].substring(1).toLowerCase()+" ";
		} 
		return itemNameTemp.trim();
	}
}
