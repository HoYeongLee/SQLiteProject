import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main {

	public static void main(String[] args) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			
			String dbFile ="myfirst.db";
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			System.out.println("\n*** ������ ��ȸ ***");
			Statement stat1 = con.createStatement();
			String sql1 = "select * from g_artists";
			ResultSet rs1 = stat1.executeQuery(sql1);
			while(rs1.next()) {
				String id = rs1.getString("id");
				String name = rs1.getString("name");
				System.out.println(id + " " + name);
			}
			stat1.close();
			// ������ �߰�
			System.out.println("\n*** �� ������ ��ȸ ***");
			Statement stat2 = con.createStatement();
			String sql2 = "insert into g_artists (name, a_type, a_year, debut, regdate)" + 
			"values ('(����)���̵�', '����', '2010���, 2020���', '2018��', datetime('now','localtime'));";
			int cnt = stat2.executeUpdate(sql2);
			if(cnt > 0)
				System.out.println("���ο� �����Ͱ� �߰��Ǿ����ϴ�!");
			else
				System.out.println("[Error] ������ �߰� ����!");
			stat2.close();
			//������ ����
			System.out.println("\n*** ������ ���� ***");
			Statement stat3 = con.createStatement();
			String sql3 = "update g_artists set debut=' 2003�� / �״� ���ƿ��� '" + " where id=2 ;";
			int cnt3 = stat3.executeUpdate(sql3);
			if(cnt3 > 0)
				System.out.println("�����Ͱ� �����Ǿ����ϴ�!");
			else
				System.out.println("[Error] ������ ���� ����!");
			stat3.close();
			// ������ ����
			System.out.println("\n*** ������ ���� ***");
			Statement stat4 = con.createStatement();
			String sql4 = "delete from g_artists where id = 7;";
			int cnt4 = stat4.executeUpdate(sql4);
			if(cnt4 > 0)
				System.out.println("�����Ͱ� �����Ǿ����ϴ�!");
			else
				System.out.println("[Error] ������ ���� ����!");
			stat4.close();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(con != null)
				try {
					con.close();
				} catch (Exception e) {}
		}
	}

}