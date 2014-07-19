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

        String query = "select g.genres_name,w.webtoons_title, COUNT(*) from webtoons as w inner join genres as g on g.genres_id_pk = w.genre_id_fk group by g.genres_name order by COUNT(*) desc limit 15";
        
        PreparedStatement pstm= conn.prepareStatement(query);

        rs = pstm.executeQuery();
        JSONObject empObj = null;

        while (rs.next()) {
	String genre = rs.getString("genres_name");
            int genreCount = rs.getInt("COUNT(*)");
            empObj = new JSONObject();
            empObj.put("genre", genre);
            empObj.put("genreCount", genreCount);
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