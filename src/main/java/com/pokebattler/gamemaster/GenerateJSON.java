package com.pokebattler.gamemaster;

import java.io.File;
import java.io.FileInputStream;
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
		if (args.length != 1) {
			System.err.println("Missing argument, gamemaster.dat binary file location");
			return;
		}
		File f = new File(args[0]);
		if (!f.exists()) {
			System.err.println("File not found: " + args[0]);
		}
		gen.writeJSON(new FileInputStream(f), System.out);

	}

}
