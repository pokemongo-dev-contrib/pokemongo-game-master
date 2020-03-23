package com.pokebattler.gamemaster;

import POGOProtos.Networking.Responses.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.*;

public class GenerateJSON {
	public GenerateJSON() {
	}

	public void writeJSON(InputStream is, OutputStream os) throws IOException {
		DownloadGmTemplatesResponse response = DownloadGmTemplatesResponse.parseFrom(is);
		GameMaster master = new GameMaster();
		//master.Result = response.getResult();
		master.Templates = new ArrayList();
		master.DeletedTemplates = response.getDeletedTemplateList();
		master.BatchId = response.getBatchId();
		//master.PageOffset = response.getPageOffset();
		master.ExperimentId = response.getExperimentIdList();

		for (DownloadGmTemplatesResponse.ClientGameMasterTemplate i : response.getTemplateList())
		{
			master.Templates.add(DownloadItemTemplatesResponse.GameMasterClientTemplate.parseFrom(i.getData()));
		}

		//TODO: this is modified response and get error try to fix this
		/*
		JsonFormat.Printer printer = JsonFormat.printer();
		try (OutputStreamWriter writer = new OutputStreamWriter(os)) {
			printer.appendTo(master, writer);
		}
		*/

		// pretty print
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// Java objects to File
		try (OutputStreamWriter writer = new OutputStreamWriter(os)) {
			gson.toJson(master, writer);
		} catch (IOException e) {
			e.printStackTrace();
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

final class GameMaster {
	//public DownloadGmTemplatesResponse.Result Result;
	public List<DownloadItemTemplatesResponse.GameMasterClientTemplate> Templates;
	public List<String> DeletedTemplates;
	public long BatchId;
	//public int PageOffset;
	public List<Integer> ExperimentId;
}
