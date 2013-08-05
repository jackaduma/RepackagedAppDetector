package mk.extract;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.ow2.asmdex.ApplicationReader;
import org.ow2.asmdex.Opcodes;
import org.ow2.asmdex.tree.AbstractInsnNode;
import org.ow2.asmdex.tree.ApplicationNode;
import org.ow2.asmdex.tree.ArrayOperationInsnNode;
import org.ow2.asmdex.tree.ClassNode;
import org.ow2.asmdex.tree.InnerClassNode;
import org.ow2.asmdex.tree.MethodNode;

/**
 * @author kun_ma
 *
 */
public class Extractor {
	/*
	 *  Three step:
	 *  1. extract apk, then get dex file and META-INFO
	 *  2. get developer certificate from META-INFO, then calculate it to sha1
	 *  3. get instruction collection using ASMDEX (or baksmali), it includes optimize instructions, using my decryption tool 
	 */
	private String tempDirPath = "test/original";
	private String metaInfPath = null;
	private String dexFile = null;
	
	public void Extractor(String apkPath) throws IOException {
		// extract apk as zip file
		UnzipApk(apkPath);
		this.metaInfPath = this.tempDirPath + System.getProperty("file.separator") + "META-INF";
		this.dexFile = this.tempDirPath + System.getProperty("file.separator") + "classes.dex";
	}
	
	public void UnzipApk(String apkPath) throws IOException {
		
		String command = String.format("7z x -o %s %s", this.tempDirPath, apkPath);
		Runtime.getRuntime().exec(command);
		
	}
	
	public static int[] GetSignature(String dexFile) {
		int api = Opcodes.ASM4;
		String dexPath = "/home/kun/workspace/MKDevelopment/RepackagedAppDetector/test/original/classes.dex";
		
		ApplicationReader ar;
		List<Integer> intList = new LinkedList<Integer>();
		try {
			ar = new ApplicationReader(api, dexPath);
			ApplicationNode an = new ApplicationNode(api);		
			ar.accept(an, 0);
			System.out.println(an.classes.size());
			// 可以先按其class name排序
			for (ClassNode cn : an.classes) {
				System.out.println("***Class:"+cn.name);
				System.out.println("method count:"+cn.methods.size());
				// inner class
				for (InnerClassNode icn: cn.innerClasses) {
					if (cn.innerClasses!=null) {
						System.out.println("---Inner Class---"+icn.innerName);
					}	
				}
				// 可以先按其method name排序
				for (MethodNode mn : cn.methods) {
					System.out.print(" ---Method: "+mn.name);
					if (null == mn.instructions) {
						continue;
					}
					System.out.println("  | instructions count:"+mn.instructions.size());
					AbstractInsnNode p = mn.instructions.getFirst();
					while (p!=null) {
						int opcode = p.getOpcode();
						intList.add(opcode);
						p = p.getNext();
					}
					
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int[] buf = new int[intList.size()];
		for (int i=0; i<intList.size(); i++) {
			buf[i] = intList.get(i);
		}
		return buf;
	}
	
	public static void main(String[] args) {		
		int api = Opcodes.ASM4;
		String dexPath = "/home/kun/workspace/MKDevelopment/RepackagedAppDetector/test/original/classes.dex";
		
		ApplicationReader ar;
		List<Integer> intList = new LinkedList<Integer>();
		try {
			ar = new ApplicationReader(api, dexPath);
			ApplicationNode an = new ApplicationNode(api);		
			ar.accept(an, 0);
			System.out.println(an.classes.size());
			// 可以先按其class name排序
			for (ClassNode cn : an.classes) {
				System.out.println("***Class:"+cn.name);
				System.out.println("method count:"+cn.methods.size());
				// inner class
				for (InnerClassNode icn: cn.innerClasses) {
					if (cn.innerClasses!=null) {
						System.out.println("---Inner Class---"+icn.innerName);
					}	
				}
				// 可以先按其method name排序
				for (MethodNode mn : cn.methods) {
					System.out.println(" ---Method: "+mn.name);
					if (null == mn.instructions) {
						continue;
					}
					AbstractInsnNode p = mn.instructions.getFirst();
					while (p!=null) {
						int opcode = p.getOpcode();
						intList.add(opcode);
						p = p.getNext();
					}
					
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int[] buf = new int[intList.size()];
		for (int i=0; i<intList.size(); i++) {
			buf[i] = intList.get(i);
		}

//		FileOutputStream os = null;
//		File outFile = null;
//		ApplicationWriter aw = new ApplicationWriter();
//
//		byte [] b = aw.toByteArray();
//		try {
//			os = new FileOutputStream(outFile);
//			os.write(b);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
	
}
