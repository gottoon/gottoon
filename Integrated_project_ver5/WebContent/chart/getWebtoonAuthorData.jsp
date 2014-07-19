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

        String query = "select a.authors_name,count(*) from webtoon_author_maps as wam inner join authors as a on a.authors_id_pk = wam.authors_id_fk inner join webtoons as w on w.webtoons_id_pk = wam.webtoons_id_fk group by a.authors_name order by count(*) desc";
        
        PreparedStatement pstm= conn.prepareStatement(query);

        rs = pstm.executeQuery();
        JSONObject empObj = null;

        while (rs.next()) {
	String authors_name = rs.getString("authors_name");
            int authorsCount = rs.getInt("count(*)");
            empObj = new JSONObject();
            empObj.put("authors_name", authors_name);
            empObj.put("authorsCount", authorsCount);
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