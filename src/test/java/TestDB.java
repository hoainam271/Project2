import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.javaweb.utils.ConnectionUtils;
import com.javaweb.utils.connectionJDBCUtil;

public class TestDB {
	@Test
	public void testConnection_0() {
		try {
			assertNull(ConnectionUtils.getMySQLConnection("","",""));
		}
		catch(SQLException e) {
			System.out.print(e);
		}
	}
	
	@Test
	public void testConnection_invalid() {
	    String hostName = "jdbc:mysql://localhost:3306/estateadvance2";  // Hoặc có thể thay đổi thành địa chỉ sai
	    String user = "wrongUser";  // Sử dụng tài khoản sai
	    String pass = "wrongPass";  // Sử dụng mật khẩu sai
	    
	    try {
	        // Test này sẽ ném ra SQLException do thông tin không đúng
	        assertNull(ConnectionUtils.getMySQLConnection(hostName, user, pass));
	    } catch (SQLException e) {
	        // Đây là trường hợp sai, không cần xử lý gì, nhưng bạn có thể kiểm tra thông báo lỗi nếu muốn
	        System.out.println("Expected SQLException: " + e.getMessage());
	    }
	}

	@Test
	public void testConnection_2() {
		String hostName= "jdbc:mysql://localhost:3306/estateadvance2";
		String user="root";
		String pass="270104";
		
		try {
			assertNotNull(ConnectionUtils.getMySQLConnection(hostName, user, pass));
		}
		catch(SQLException e) {
		}
	}
}
