package com.pokebattler.gamemaster;
import POGOProtos.Rpc.*;
import POGOProtos.Networking.Responses.*;
import com.google.protobuf.util.*;

import java.io.*;

public class GenerateJSON {
	private boolean use_old_mode;

	public GenerateJSON(boolean oldmode) {
		use_old_mode = oldmode;
	}

	public void writeJSON(InputStream is, OutputStream os) throws IOException {
		GameMasterDecoderTool response = GameMasterDecoderTool.parseFrom(is);
		//old mode used here....
		if (use_old_mode) {
			DownloadItemTemplatesResponse.Builder old_mode = DownloadItemTemplatesResponse.newBuilder();
			old_mode.setTimestampMs(response.getBatchId());

			int index = 0;
			for (GameMasterDecoderTool.ClientGameMasterTemplate template : response.getTemplateList()) {
				DownloadItemTemplatesResponse.GameMasterClientTemplate.Builder item = DownloadItemTemplatesResponse.GameMasterClientTemplate.newBuilder();
				item.mergeFrom(template.getData());
				item.setTemplateId(response.getTemplate(index).getTemplateId());
				item.build();
				index++;
				old_mode.addItemTemplate(item);
			}

			old_mode.build();
			JsonFormat.Printer printer = JsonFormat.printer();
			try (OutputStreamWriter writer = new OutputStreamWriter(os)) {
				printer.appendTo(old_mode, writer);
				System.out.println();
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Generated templates old mode:");
				System.out.println("	Decoded templates: " + old_mode.getItemTemplateCount());
				System.out.println("	TimestampMs      : " + old_mode.getTimestampMs());
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println();
			}
		}
		//new mode used in app....
		else {
			JsonFormat.Printer printer = JsonFormat.printer();
			try (OutputStreamWriter writer = new OutputStreamWriter(os)) {
				printer.appendTo(response, writer);
				System.out.println();
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println("Generated templates new mode:");
				System.out.println("	Decoded templates: " + response.getTemplateCount());
				System.out.println("	Deleted templates: " + response.getDeletedTemplateCount());
				System.out.println("	Experiment ids   : " + response.getExperimentIdCount());
				System.out.println("	BatchId          : " + response.getBatchId());
				System.out.println("-------------------------------------------------------------------------------");
				System.out.println();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		if (args.length == 0 || args.length > 3 || args.length < 2) {
			System.err.println("USAGE: java -jar pokemongo-game-master-2.46.0.jar BINARY_INPUT_FILE JSON_OUTPUT_FILE [optional --oldmode]");
			return;
		}
		File f = new File(args[0]);
		if (!f.exists()) {
			System.err.println("File not found: " + args[0]);
			return;
		}
		try (OutputStream os = args.length >= 2 ? new FileOutputStream(new File(args[1])) : System.out;
			 InputStream is = new FileInputStream(f)) {
			GenerateJSON gen = new GenerateJSON(false);
			if (args.length == 3) {
				if (args[2].toLowerCase().equals("--oldmode"))
					gen = new GenerateJSON(true);
				else {
					System.err.println("Bad option: " + args[2]);
					return;
				}
			}
			gen.writeJSON(is, os);
		}
	}
}
