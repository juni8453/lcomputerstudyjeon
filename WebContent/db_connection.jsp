<%@ page import="java.sql.*"%>

<%
	Connection conn = null;
	
	String url = "jdbc:mysql://localhost:3306/jeon";
	String user = "root";
	String dbPassword = "1234";
	
	Class.forName("org.mariadb.jdbc.Driver");
	conn = DriverManager.getConnection(url, user, dbPassword);
%>	