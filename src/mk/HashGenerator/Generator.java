package mk.HashGenerator;

import mk.HashGenerator.FuzzyHashLib;

/**
 * @author kun_ma
 *
 */
public class Generator {
	
	public static byte[] GenerateFuzzyHash(String filename) {
		byte[] result = new byte[FuzzyHashLib.FUZZY_MAX_RESULT];
		FuzzyHashLib.INSTANCE.fuzzy_hash_filename(filename, result);
		return result;
	}
	
	public static byte[] GenerateFuzzyHash(int[] byteArray) {
		byte[] result = new byte[FuzzyHashLib.FUZZY_MAX_RESULT];
		FuzzyHashLib.INSTANCE.fuzzy_hash_buf(byteArray, byteArray.length, result);
		return result;
	}
	
	public static int Compare(byte[] sig1, byte[] sig2) {
		if (sig1.equals(sig2)) {
			return 100;
		}
		int score = FuzzyHashLib.INSTANCE.fuzzy_compare(sig1, sig2);
		return score;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		System.out.println(System.getProperty("java.library.path"));
		
		byte[] result1 = new byte[FuzzyHashLib.FUZZY_MAX_RESULT];
		FuzzyHashLib.INSTANCE.fuzzy_hash_filename("/home/kun/MyWork/RepackagedAppDetect/ssdeep-2.9/ssdeep-2.9/test", result1);
		System.out.println(result1[0]);
		
		byte[] result2 = new byte[FuzzyHashLib.FUZZY_MAX_RESULT];
		FuzzyHashLib.INSTANCE.fuzzy_hash_filename("/home/kun/MyWork/RepackagedAppDetect/ssdeep-2.9/ssdeep-2.9/test1", result2);
		System.out.println(result2[0]);
		
		int score = FuzzyHashLib.INSTANCE.fuzzy_compare(result1, result2);
		System.out.println(score);
	}
}
