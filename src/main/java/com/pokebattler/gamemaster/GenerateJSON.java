package com.pokebattler.gamemaster;

import POGOProtos.Networking.Responses.*;
import com.google.protobuf.util.*;

import java.io.*;

public class GenerateJSON {
	public GenerateJSON() {
	}

	public void writeJSON(java.io.InputStream is, java.io.OutputStream os) throws java.io.IOException {
		POGOProtos.Networking.Responses.DownloadGmTemplatesResponse response = POGOProtos.Networking.Responses.DownloadGmTemplatesResponse.parseFrom(is);

		/*
		//C# code mode needs fix it for java
		com.pokebattler.gamemaster.GameMaster master = new com.pokebattler.gamemaster.GameMaster();
		master.Result = response.Result;
		master.Templates = new RepeatedField<GameMasterClientTemplate>();
		master.DeletedTemplates = response.DeletedTemplate;
		master.BatchId = response.BatchId;
		master.PageOffset = response.PageOffset;
		master.ExperimentId = response.ExperimentId;

		for (var i : response.Template)
		{
			master.Templates.Add(GameMasterClientTemplate.parseFrom(i.Data));
		}
        */

		com.google.protobuf.util.JsonFormat.Printer printer = com.google.protobuf.util.JsonFormat.printer();
		try (java.io.OutputStreamWriter writer = new java.io.OutputStreamWriter(os)) {
			//printer.appendTo(master, writer);
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

		GenerateJSON gen = new GenerateJSON();
		try (OutputStream os = args.length == 2 ? new FileOutputStream(new File(args[1])) : System.out;
			 InputStream is = new FileInputStream(f)) {
			gen.writeJSON(is, os);
		}
	}
}

final class GameMaster
{
	public DownloadGmTemplatesResponse.Result Result;
	public java.util.List<POGOProtos.Networking.Responses.DownloadItemTemplatesResponse.GameMasterClientTemplate> Templates;
	public java.util.List<String> DeletedTemplates;
	public long BatchId;
	public int PageOffset;
	public java.util.List<Integer> ExperimentId;
}
