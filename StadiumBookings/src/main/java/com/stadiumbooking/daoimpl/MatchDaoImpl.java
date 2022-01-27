package com.stadiumbooking.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stadiumbooking.connection.ConnectionUtill;
import com.stadiumbooking.dao.MatchDao;
import com.stadiumbooking.model.Match;

public class MatchDaoImpl implements MatchDao {

	@Override
	public void insertMatchDetalis(Match match) throws ClassNotFoundException, SQLException {
		
		/* Insert Match Details Into Database  */
		

		ConnectionUtill conUtil=new ConnectionUtill();
		Connection con=conUtil.getDBConnect();
		
		String query="insert into match_info(sportsId,stadium_name, location,match_date, match_time,teamA,teamB,teamAlogo, teamBlogo, totalseats, availableSeats, firstClass_Seats_price, secondClass_seats_price) values(?,?,?,?,? ,?,?,?,?,?,?,?,?)";
		
		PreparedStatement stmt=con.prepareStatement(query);
		
	    stmt.setInt(1, match.getSportsId());
		stmt.setString(2, match.getStadiumName());
		stmt.setString(3, match.getLocation());
		stmt.setDate(4, java.sql.Date.valueOf(match.getMatchDate()));
		stmt.setTime(5, java.sql.Time.valueOf(match.getMatchTime()));
		stmt.setString(6, match.getTeamA());
		stmt.setString(7, match.getTeamB());
		stmt.setString(8, match.getTeamAlogo());
		stmt.setString(9, match.getTeamBlogo());
		stmt.setInt(10, match.getTotalseats());
		stmt.setInt(11, match.getAvailableSeats());
		stmt.setInt(12, match.getFirstClassSeatsPrice());
		stmt.setInt(13, match.getSecondClassSeatsPrice());
     	stmt.executeUpdate();
     	stmt.close();
     	con.close();
	
		
	}

	@Override
	public List<Match> getAllMatchDetalis() throws ClassNotFoundException, SQLException {
		
		/* Get All Match Details  */
		
		ConnectionUtill conUtil=new ConnectionUtill();
		Connection con=conUtil.getDBConnect();
		
		String query="select MATCH_ID,SPORTSID,STADIUM_NAME,LOCATION,to_char(MATCH_DATE,'yyyy-mm-dd'),to_char(MATCH_TIME,'HH:MI'),TEAMA,TEAMB,TEAMALOGO,TEAMBLOGO,TOTALSEATS,AVAILABLESEATS,FIRSTCLASS_SEATS_PRICE,SECONDCLASS_SEATS_PRICE from match_info order by match_date desc";
		PreparedStatement stmt=con.prepareStatement(query);
		ResultSet rs=stmt.executeQuery(query);

		List<Match> matchList=new ArrayList<>();
		String dateInStering;
		String tiemString;
		LocalDate localDate = null;
		LocalTime localTime=null;
		while(rs.next()) {
			dateInStering=rs.getString(5);
			tiemString=rs.getString(6);

			localDate=LocalDate.parse(dateInStering);
			localTime=LocalTime.parse(tiemString);
			Match match=new Match(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),localDate,localTime,rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getInt(11),rs.getInt(12),rs.getInt(13),rs.getInt(14));
		
		matchList.add(match);
		}
		rs.close();
		stmt.close();
		con.close();
		return matchList;
	}

	@Override
	public LocalDate getDate() throws ClassNotFoundException, SQLException {
		
		/*Get Date System Date */
		ConnectionUtill conUtil=new ConnectionUtill();
		Connection con=conUtil.getDBConnect();

         String query="select to_char(sysdate,'yyyy-mm-dd')  as today  from dual";
         PreparedStatement stmt=con.prepareStatement(query);
		ResultSet rs=stmt.executeQuery(query);
		rs.next();
		String dateinString=rs.getString(1);
		return LocalDate.parse(dateinString);
		
	}

	@Override
	public List<Match> getMatchByMatchId(int matchId) throws ClassNotFoundException, SQLException {
		
		/*Get a Single Match Details By Match Id*/
		
		ConnectionUtill conUtil=new ConnectionUtill();
		Connection con=conUtil.getDBConnect();
		
		String query="select MATCH_ID,SPORTSID,STADIUM_NAME,LOCATION,to_char(MATCH_DATE,'yyyy-mm-dd'),to_char(MATCH_TIME,'HH:MI'),TEAMA,TEAMB,TEAMALOGO,TEAMBLOGO,TOTALSEATS,AVAILABLESEATS,FIRSTCLASS_SEATS_PRICE,SECONDCLASS_SEATS_PRICE from match_info where match_id=?";
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1, matchId);
		ResultSet rs=pst.executeQuery();
		List<Match> matchList=new ArrayList<>();
		String dateInStering;
		String tiemString;
		LocalDate localDate = null;
		LocalTime localTime=null;
		while(rs.next()) {
			dateInStering=rs.getString(5);
			tiemString=rs.getString(6);

			localDate=LocalDate.parse(dateInStering);
			localTime=LocalTime.parse(tiemString);
			Match match=new Match(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),localDate,localTime,rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getInt(11),rs.getInt(12),rs.getInt(13),rs.getInt(14));
		
		matchList.add(match);
		}
		
		return matchList;
	}

	@Override
	public void updateAvailableSeats(int seatsCount, int matchId) throws ClassNotFoundException, SQLException {
		
		/* Update The Available Seats in Database */
		
		ConnectionUtill conUtil=new ConnectionUtill();
		Connection con=conUtil.getDBConnect();
		String query="update match_info set availableSeats=availableSeats-? where match_id=?";
		
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1, seatsCount);
		pst.setInt(2, matchId);
		pst.executeUpdate();		
	}

	@Override
	public void updateCancelledSeats(int seatsCount, int matchId) throws ClassNotFoundException, SQLException {
		/* Update The Available Seats in Database */
		
		ConnectionUtill conUtil=new ConnectionUtill();
		Connection con=conUtil.getDBConnect();
		
		String query="update match_info set availableSeats=availableSeats+? where match_id=?";
		
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1, seatsCount);
		pst.setInt(2, matchId);
		pst.executeUpdate();
			
	}

	@Override
	public List<Match> searchByTeam(String teamName) throws ClassNotFoundException, SQLException {
		
		ConnectionUtill conUtil=new ConnectionUtill();
		
		Connection con=conUtil.getDBConnect();
		String query="select MATCH_ID,SPORTSID,STADIUM_NAME,LOCATION,to_char(MATCH_DATE,'yyyy-mm-dd'),to_char(MATCH_TIME,'HH:MI'),TEAMA,TEAMB,TEAMALOGO,TEAMBLOGO,TOTALSEATS,AVAILABLESEATS,FIRSTCLASS_SEATS_PRICE,SECONDCLASS_SEATS_PRICE from match_info where teama like '"+teamName.toUpperCase()+"%' or teamb like '"+teamName.toUpperCase()+"%' ";
		PreparedStatement pst=con.prepareStatement(query);
		ResultSet rs=pst.executeQuery();
		List<Match> matchList=new ArrayList<>();
		String dateInStering;
		String tiemString;
		LocalDate localDate = null;
		LocalTime localTime=null;
		while(rs.next()) {
			dateInStering=rs.getString(5);
			tiemString=rs.getString(6);

			localDate=LocalDate.parse(dateInStering);
			localTime=LocalTime.parse(tiemString);
			Match match=new Match(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),localDate,localTime,rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getInt(11),rs.getInt(12),rs.getInt(13),rs.getInt(14));
		
		matchList.add(match);
		}
		
		return matchList;
	}

	@Override
	public void updateMatchDetails(Match match) throws ClassNotFoundException, SQLException {
				/* Update Single Match Details */
		ConnectionUtill conUtil=new ConnectionUtill();
		Connection con=conUtil.getDBConnect();
		String que = "update match_info set match_date=?,match_time=? where match_id=?";
		PreparedStatement pstmt = con.prepareStatement(que);
		pstmt.setDate(1, java.sql.Date.valueOf(match.getMatchDate()));
		pstmt.setTime(2, java.sql.Time.valueOf(match.getMatchTime()));
		pstmt.setInt(3, match.getMatchId());
		
		pstmt.executeUpdate();

	}

	@Override
	public int checkAvilableSeats(int matchId) throws ClassNotFoundException, SQLException {
		ConnectionUtill conUtil=new ConnectionUtill();
		Connection con=conUtil.getDBConnect();
		int totalseats=0;
		
		String query="select availableSeats from match_info where match_id=?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, matchId);
		ResultSet rs=pstmt.executeQuery();
       if(rs.next()) {
   		 totalseats=rs.getInt(1);
		
	}
       return totalseats;
	}
	

}
