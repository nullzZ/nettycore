package game.core.nettyCore.protobuf;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

/**
 * @author nullzZ
 *
 */

public class TestRequestPb {

	@Protobuf(fieldType=FieldType.INT32, order=1, required=false)
	public int id;

}
