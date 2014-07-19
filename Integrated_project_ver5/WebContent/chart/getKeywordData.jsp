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

        String query = "select count(k.keywords_id_pk) as keywords_count, k.keywords_name "
		+ "from keywords as k "
		+ "inner join webtoon_keyword_maps as wk on k.keywords_id_pk = wk.keywords_id_fk "
		+ "inner join webtoons as w on w.webtoons_id_pk = wk.webtoons_id_fk "
		+ "inner join user_webtoon_maps as uw on uw.webtoons_id_fk = w.webtoons_id_pk "
		+ "group by k.keywords_name "
		+ "order by count(k.keywords_id_pk) desc limit 15";
        
        PreparedStatement pstm= conn.prepareStatement(query);

        rs = pstm.executeQuery();
        JSONObject empObj = null;

        while (rs.next()) {
	String keyword = rs.getString("keywords_name");
            int keywordCount = rs.getInt("keywords_count");
            empObj = new JSONObject();
            empObj.put("keyword", keyword);
            empObj.put("keyword_count", keywordCount);
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