package mk.HashGenerator;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.*;

/**
 * @author kun_ma
 * 
 */
public interface FuzzyHashLib extends Library {

	FuzzyHashLib INSTANCE = (FuzzyHashLib) Native.loadLibrary("fuzzy",
			FuzzyHashLib.class);

	/*
	 * * Fuzzy hashing a buffer of text: int fuzzy_hash_buf(const unsigned char *buf, uint32_t buf_len, char *result);
	 */
	public int fuzzy_hash_buf(int[] buf, long buf_len, byte[] result);

	/*
	 * * Fuzzy hashing a file: int fuzzy_hash_file(FILE *handle, char *result);
	 */
	public int fuzzy_hash_file(Pointer handle, byte[] result);

	/*
	 * int fuzzy_hash_filename(const char * filename, char * result);
	 */
	public int fuzzy_hash_filename(String filename, byte[] result);

	/*
	 * * Compare two fuzzy hash signatures: int fuzzy_compare(const char *sig1, const char *sig2);
	 */
	public int fuzzy_compare(byte[] sig1, byte[] sig2);

	public int SPAMSUM_LENGTH = 64;
	public int FUZZY_MAX_RESULT = (SPAMSUM_LENGTH + (SPAMSUM_LENGTH / 2 + 20));

}
