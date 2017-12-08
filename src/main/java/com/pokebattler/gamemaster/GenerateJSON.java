package com.pokebattler.gamemaster;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.google.protobuf.util.JsonFormat;

import POGOProtos.Enums.PokemonMoveOuterClass.PokemonMove;
import POGOProtos.Networking.Responses.DownloadItemTemplatesResponseOuterClass.DownloadItemTemplatesResponse;
import POGOProtos.Settings.Master.PokemonSettingsOuterClass.PokemonSettings;

public class GenerateJSON {
	public GenerateJSON() {
	}

	public void writeJSON(InputStream is, OutputStream os) throws IOException {
		DownloadItemTemplatesResponse response = DownloadItemTemplatesResponse.parseFrom(is);
		// no longer needed
//		response = addLegacyMoves(response);
		JsonFormat.Printer printer = JsonFormat.printer();
		try (OutputStreamWriter writer = new OutputStreamWriter(os)) {
			printer.appendTo(response, writer);
		}
	}

	public static void main(String[] args) throws Exception {
		GenerateJSON gen = new GenerateJSON();
		if (args.length == 0 || args.length > 2 ) {
			System.err.println("USAGE: java -jar pokemongo-game-master-2.15.0.jar BINARY_INPUT_FILE [optional JSON_OUTPUT_FILE]");
			return;
		}
		File f = new File(args[0]);
		if (!f.exists()) {
			System.err.println("File not found: " + args[0]);
		}

		try (OutputStream os = args.length == 2?new FileOutputStream(new File(args[1])):System.out;
				InputStream is = new FileInputStream(f)) {
			gen.writeJSON(is, os);
		}

	}

}
