import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.*;


public class JDBCDemo {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/dhruv";
            String user = "system";
            String pass = "config";
            Connection con = DriverManager.getConnection(url, user, pass);

            if (con != null) {
                System.out.println("connection success");
            }

            PreparedStatement ps = con.prepareStatement("insert emp(empId,name,salary,mobile) values(?,?,?,?)");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter EmpId : ");
            int id = Integer.parseInt(br.readLine());

            System.out.println("Enter Name : ");
            String name = br.readLine();

            System.out.println("Enter Salary : ");
            int salary = Integer.parseInt(br.readLine());

            System.out.println("Enter Mobile No : ");

            int mobile = Integer.parseInt(br.readLine());

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, salary);
            ps.setInt(4, mobile);
            ps.executeUpdate();

            Statement stmt = con.createStatement();
            ResultSet set = stmt.executeQuery("select * from emp");

            PreparedStatement psUpdate = con.prepareStatement("update emp set name = ?, salary = ? where empId = ?");
            PreparedStatement psDelete = con.prepareStatement("delete from emp where empId = ?");

            while (set.next()) {
                int id1 = set.getInt("empId");
                String name1 = set.getString("name");
                int salary1 = set.getInt("salary");
                int mobile1 = set.getInt("mobile");
                System.out.println("Id : " + id1 + "\tName : " + name1 + "\tSalary : " + salary1 + "\tMobile : " + mobile1);
            }

            Scanner sc = new Scanner(System.in);
            System.out.println("enter name1 : ");
            String name3 = sc.next();

            psUpdate.setString(1, name3);
            psUpdate.setInt(2, 5000);
            psUpdate.setInt(3, 2);
            psUpdate.executeUpdate();

            Statement stmt1 = con.createStatement();
            ResultSet set1 = stmt1.executeQuery("select * from emp");

            System.out.println("\n");

            while (set1.next()) {
                int id1 = set1.getInt("empId");
                String name1 = set1.getString("name");
                int salary1 = set1.getInt("salary");
                int mobile1 = set1.getInt("mobile");
                System.out.println("Id : " + id1 + "\tName : " + name1 + "\tSalary : " + salary1 + "\tMobile : " + mobile1);
            }


            psDelete.setInt(1, 444);
            psDelete.executeUpdate();

            Statement stmt2 = con.createStatement();
            ResultSet set2 = stmt2.executeQuery("select * from emp");

            System.out.println("\n");

            while (set2.next()) {
                int id1 = set2.getInt("empId");
                String name1 = set2.getString("name");
                int salary1 = set2.getInt("salary");
                int mobile1 = set2.getInt("mobile");
                System.out.println("Id : " + id1 + "\tName : " + name1 + "\tSalary : " + salary1 + "\tMobile : " + mobile1);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}