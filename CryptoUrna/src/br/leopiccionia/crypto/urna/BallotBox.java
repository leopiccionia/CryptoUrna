package br.leopiccionia.crypto.urna;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class BallotBox{

	private static final String HMAC_ALGORITHM = "HmacSHA256";
	
	public static void main(String[] args){
		ConwayGameOfLife game = new ConwayGameOfLife(System.nanoTime());
		Thread generator = new Thread(game);
		generator.start();
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()){	
			String texto = scanner.nextLine();
			String key = game.getCurrentValue();
			SecretKeySpec macKey = new SecretKeySpec(key.getBytes(), HMAC_ALGORITHM);
			Mac mac;
			try {
				mac = Mac.getInstance(HMAC_ALGORITHM);
				mac.init(macKey);
				byte[] tag = mac.doFinal(texto.getBytes());	
				System.out.println(tag);
			}
			catch (InvalidKeyException e){
				System.err.println("ERROR: Invalid key.");
			}
			catch (NoSuchAlgorithmException e1){
				System.err.println("ERROR: No such algorithm '" + HMAC_ALGORITHM + "'");
			}
		}
		scanner.close();
	}
	
}