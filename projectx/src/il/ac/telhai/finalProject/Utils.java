package il.ac.telhai.finalProject;

import java.util.Random;

public class Utils {
	
	/**
	 * This method return the full path of a given class
	 * @param cls the class to find the full path
	 * @return String represent the full path of the class
	 */
	public static String getFullPath(@SuppressWarnings("rawtypes") Class cls) {
		String[] fullyQualifiedName = cls.getName().split("\\.");
		String className = fullyQualifiedName[fullyQualifiedName.length -1];
		String prefix = cls.getProtectionDomain().getCodeSource().getLocation().toExternalForm().substring(6);
		return prefix + className;
	}
	
	/**
	 * initialize array with random number from 0 to n-1
	 * in the return array every each index will have
	 * different value.
	 */
	public static int[] getRandomArrayNoRepetitions(int n){
		int arr[] = new int[n];
		for (int i=0; i < arr.length; i++){
			arr[i] = i;
		}
		Random r = new Random();
		for(int i=0; i < arr.length ; i++){
			int k = r.nextInt(arr.length-i);
			int t = arr[k];
			arr[k] = arr[arr.length-1-i];
			arr[arr.length-1-i] = t;
		}
		
		return arr;
	}
}
