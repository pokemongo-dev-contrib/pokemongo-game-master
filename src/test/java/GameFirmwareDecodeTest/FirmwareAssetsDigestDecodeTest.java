package GameFirmwareDecodeTest;

import POGOProtos.Networking.Responses.GetAssetDigestResponseOuterClass.*;
import com.google.protobuf.util.*;
import org.junit.*;

import java.io.*;

public class FirmwareAssetsDigestDecodeTest {
	@Test
	public void TestFirmwareAssetsDigestDecode() throws Exception {
		try (InputStream is = getClass().getResourceAsStream("/ASSET_DIGEST.protobuf"))
        {
			GetAssetDigestResponse response = GetAssetDigestResponse.parseFrom(is);
			JsonFormat.Printer printer = JsonFormat.printer();
			try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("target/test-classes/FIRMWARE_ASSET_DIGEST.json")))
            {
				printer.appendTo(response, writer);
			}
		}
	}
}
