package GameFirmwareDecodeTest;

import POGOProtos.Data.*;
import com.google.protobuf.util.*;
import org.junit.*;

import java.io.*;

public class FirmwareGameMasterDecodeTest {
	@Test
	public void TestFirmwareGameMasterDecode() throws Exception {
		try (InputStream is = getClass().getResourceAsStream("/FIRMWARE_GAME_MASTER.protobuf")) {
			GameMasterDecoder response = GameMasterDecoder.parseFrom(is);
			JsonFormat.Printer printer = JsonFormat.printer();
			try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("target/test-classes/FIRMWARE_GAME_MASTER.json"))) {
				printer.appendTo(response, writer);
			}
		}
	}
}
