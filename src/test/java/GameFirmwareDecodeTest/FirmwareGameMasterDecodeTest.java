package GameFirmwareDecodeTest;

import POGOProtos.Networking.Responses.*;
import com.google.gson.*;
import org.junit.*;

import java.io.*;
import java.util.*;

public class FirmwareGameMasterDecodeTest {
	@Test
	public void TestFirmwareGameMasterDecode() throws Exception {
		try (InputStream is = getClass().getResourceAsStream("/FIRMWARE_GAME_MASTER.protobuf")) {
			DownloadGmTemplatesResponse response = DownloadGmTemplatesResponse.parseFrom(is);
			GameMaster master = new GameMaster();
			//master.Result = response.getResult();
			master.Templates = new ArrayList();
			master.DeletedTemplates = response.getDeletedTemplateList();
			master.BatchId = response.getBatchId();
			//master.PageOffset = response.getPageOffset();
			master.ExperimentId = response.getExperimentIdList();

			for (DownloadGmTemplatesResponse.ClientGameMasterTemplate i : response.getTemplateList()) {
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
			try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("target/test-classes/FIRMWARE_GAME_MASTER.json"))) {
				gson.toJson(master, writer);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

final class GameMaster {
	//public DownloadGmTemplatesResponse.Result Result;
	public List<POGOProtos.Networking.Responses.DownloadItemTemplatesResponse.GameMasterClientTemplate> Templates;
	public List<String> DeletedTemplates;
	public long BatchId;
	//public int PageOffset;
	public List<Integer> ExperimentId;
}
