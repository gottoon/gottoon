<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="org.json.JSONObject"%>

<%
	Connection conn= null;
	try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://localhost/test_toondb","root","1234");

        ResultSet rs = null;
        List<JSONObject> empdetails = new LinkedList<JSONObject>();
        JSONObject responseObj = new JSONObject();

        String query = "select webtoons_publisher,count(*) from webtoons group by webtoons_publisher order by count(*) desc limit 15";
        
        PreparedStatement pstm= conn.prepareStatement(query);

        rs = pstm.executeQuery();
        JSONObject empObj = null;

        while (rs.next()) {
	String webtoons_publisher = rs.getString("webtoons_publisher");
            int publisherCount = rs.getInt("count(*)");
            empObj = new JSONObject();
            empObj.put("webtoons_publisher", webtoons_publisher);
            empObj.put("publisherCount", publisherCount);
            empdetails.add(empObj);
        }
        response.setCharacterEncoding("UTF-8");
        responseObj.put("empdetails", empdetails);
        out.print(responseObj.toString());
    }
    catch(Exception e){
        e.printStackTrace();
    }finally{
		try{
        	if(conn!= null) conn.close();
		} catch(Exception e) {
	e.printStackTrace();
        }
    }
%>