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

        String query = "select w.webtoons_title,count(*) from user_webtoon_maps as uwm inner join webtoons as w on uwm.webtoons_id_fk = w.webtoons_id_pk group by w.webtoons_title order by count(*) desc";
        
        PreparedStatement pstm= conn.prepareStatement(query);

        rs = pstm.executeQuery();
        JSONObject empObj = null;

        while (rs.next()) {
	String webtoons_title = rs.getString("webtoons_title");
            int userWebtoonCount = rs.getInt("count(*)");
            empObj = new JSONObject();
            empObj.put("webtoons_title", webtoons_title);
            empObj.put("userWebtoonCount", userWebtoonCount);
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