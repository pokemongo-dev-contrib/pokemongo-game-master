package com.pokebattler.gamemaster;

import POGOProtos.Networking.Responses.GetAssetDigestResponseOuterClass.*;
import com.google.protobuf.util.*;

import java.io.*;

public class GenerateAssetDigestJSON {
	public GenerateAssetDigestJSON() {
	}

	public void writeJSON(InputStream is, OutputStream os) throws IOException {
		GetAssetDigestResponse response = GetAssetDigestResponse.parseFrom(is);
		//no longer needed
		//response = addLegacyMoves(response);
		JsonFormat.Printer printer = JsonFormat.printer();
		try (OutputStreamWriter writer = new OutputStreamWriter(os)) {
			printer.appendTo(response, writer);
		}
	}

	public static void main(String[] args) throws Exception {
		if (args.length == 0 || args.length > 2) {
			System.err.println("USAGE: java -jar pokemongo-game-master-2.46.0.jar BINARY_INPUT_FILE [optional JSON_OUTPUT_FILE]");
			return;
		}

		File f = new File(args[0]);
		if (!f.exists()) {
			System.err.println("File not found: " + args[0]);
			return;
		}

		GenerateAssetDigestJSON gen = new GenerateAssetDigestJSON();
		try (OutputStream os = args.length == 2 ? new FileOutputStream(new File(args[1])) : System.out;
			 InputStream is = new FileInputStream(f)) {
			gen.writeJSON(is, os);
		}
	}
}
