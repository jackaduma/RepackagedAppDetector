package mk.d;

import mk.HashGenerator.Generator;
import mk.extract.Extractor;

/**
 * @author kun_ma
 *
 */
public class Detector {
	
	public static void main(String[] args) {
		String dex1Path = "/home/kun/workspace/MKDevelopment/RepackagedAppDetector/test/original/classes.dex";
		String dex2Path = "/home/kun/workspace/MKDevelopment/RepackagedAppDetector/test/other/classes.dex";
		
		byte[] sig1 = Generator.GenerateFuzzyHash(Extractor.GetSignature(dex1Path));
		System.out.println("### signature 1 calculate over!");
		byte[] sig2 = Generator.GenerateFuzzyHash(Extractor.GetSignature(dex2Path));
		System.out.println("### signature 2 calculate over!");
		int score = Generator.Compare(sig1, sig2);
		System.out.println("### Compare over!");
		System.out.println("Score:"+score);
	}
}
