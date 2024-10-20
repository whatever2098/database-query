package projectMovie;
import java.sql.*;
import java.util.ArrayList;

public class projectMovie {
	public static void main(String[] args) throws Exception {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
			e.printStackTrace();
			return;
		}
		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/project_movie", "postgres",
					"cse3207");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println(connection);
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}

		Statement statement = connection.createStatement();

		// 1.Creating the tables
		/*try { 
		  //create director table
		  statement.executeUpdate("create table director" +
		  "(directorID varchar(2)," + "directorName varchar(20) not null," +
		  "dateOfBirth varchar(10)," +  // 알려지지않았을 수 있으므로 null허용 
		  "dateOfDeath varchar(10)," + "primary key(directorID))");
		  
		//create actor table
		  statement.executeUpdate("create table actor" + "(actorID varchar(2)," +
		  "actorName varchar(20) not null," + "dateOfBirth varchar(10)," +
		  "dateOfDeath varchar(10)," + "gender varchar(10)," +
		  "primary key(actorID))");
		  
		//create movie table
		  statement.executeUpdate("create table movie" + "(movieID varchar(2)," +
		  "movieName varchar(25) not null," +
		  "releaseYear numeric(4,0) check(releaseYear > 1850 and releaseYear < 2100),"
		  + "releaseMonth numeric(2,0) check(releaseMonth > 0 and releaseMonth < 13),"
		  + "releaseDate numeric(2,0) check(releaseDate > 0 and releaseDate < 32)," +
		  "publisherName varchar(30)," + "avgRate numeric(3,2)," +    //평점은 3.2나 4.15 이렇게 소수 둘째자리까지로 한다.
		  "primary key(movieID))");
		  
		//create customer table
		  statement.executeUpdate("create table customer" + "(customerID varchar(4)," +
		  "customerName varchar(10) not null," + "dateOfBirth char(10)," +//인스턴스 data가 05.04와 같이 월.일 두자리씩 고정되므로(fixed) char이용함
		  "gender varchar(10)," +
		  "primary key(customerID))");
		  
		//create award table
		  statement.executeUpdate("create table award" + "(awardID varchar(2)," +
		  "awardName varchar(30) not null," + "primary key(awardID))");
		  
		//create genre table
		  statement.executeUpdate("create table genre" + "(genreName varchar(10)," +
		  "primary key(genreName))");
		  
		//create movieGenre table
		  statement.executeUpdate("create table movieGenre" + "(movieID varchar(2)," +
		  "genreName varchar(10)," + "primary key(movieID, genreName)," +
		  "foreign key(movieID) references movie on delete cascade," +    //foreign key는 나중에 7,8번 문제에서 referenced data삭제시 자동삭제시키기위해 on delete cascade로 한다.
		  "foreign key(genreName) references genre on delete cascade)");
		  
		//create movieObtain table
		  statement.executeUpdate("create table movieObtain" + "(movieID varchar(2)," +
		  "awardID varchar(2)," +
		  "year numeric(4,0) check(year > 1950 and year < 2100)," +
		  "primary key(movieID, awardID)," + "foreign key(movieID) references movie on delete cascade," +
		  "foreign key(awardID) references award on delete cascade)");
		  
		//create actorObtain table
		  statement.executeUpdate("create table actorObtain" + "(actorID varchar(2)," +
		  "awardID varchar(2)," +
		  "year numeric(4,0) check(year > 1950 and year < 2100)," +
		  "primary key(actorID, awardID)," + "foreign key(actorID) references actor on delete cascade," +
		  "foreign key(awardID) references award on delete cascade)");
		  
		//create directorObtain table
		  statement.executeUpdate("create table directorObtain" +
		  "(directorID varchar(2)," + "awardID varchar(2)," +
		  "year numeric(4,0) check(year > 1950 and year < 2100)," +
		  "primary key(directorID, awardID)," +
		  "foreign key(directorID) references director on delete cascade," +
		  "foreign key(awardID) references award on delete cascade)");
		  
		//create casting table
		  statement.executeUpdate("create table casting" + "(movieID varchar(2)," +
		  "actorID varchar(2)," + "role varchar(20)," +
		  "primary key(movieID, actorID)," + "foreign key(movieID) references movie on delete cascade," +
		  "foreign key(actorID) references actor on delete cascade)");
		  
		//create make table
		  statement.executeUpdate("create table make" + "(movieID varchar(2)," +
		  "directorID varchar(2)," + "primary key(movieID, directorID)," +
		  "foreign key(movieID) references movie on delete cascade," +
		  "foreign key(directorID) references director on delete cascade)");
		  
		//create customerRate table
		  statement.executeUpdate("create table customerRate" +
		  "(customerID varchar(4)," + "movieID varchar(2)," +
		  "rate numeric(3,2) check(rate >= 0.00 and rate <= 5.00)," +
		  "primary key(customerID, movieID)," +
		  "foreign key(customerID) references customer on delete cascade," +
		  "foreign key(movieID) references movie on delete cascade)"); 
		  } catch (SQLException sqle) {
		  System.out.println("Could not create the tables. " + sqle); }
		  System.out.println("Table created!");
		 */
		
		//1.Insert the initial data
		//director, actor, movie, customer먼저 표에 있는 데이타 집어넣고, 
		//genre, movieGenre, casting, make 데이터 넣는다. 
		//award, movieObtain, actorObtain, directorObtain, customerRate는 아직 넣을 데이터가 없다.
		  /*try { 
			  // initializing director 
			  PreparedStatement preparedStatement =
		  connection.prepareStatement("insert into director values(?, ?, ?, ?)");
		  preparedStatement.setString(1, "1"); preparedStatement.setString(2,
		  "Tim Burton"); preparedStatement.setString(3, "1958.8.25");
		  preparedStatement.setString(4, null); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "2"); preparedStatement.setString(2,
		  "David Fincher"); preparedStatement.setString(3, "1962.8.28");
		  preparedStatement.setString(4, null); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "3"); preparedStatement.setString(2,
		  "Christopher Nolan"); preparedStatement.setString(3, "1970.7.30");
		  preparedStatement.setString(4, null); preparedStatement.executeUpdate();
		  
		  // initializing actor 
		  preparedStatement =
		  connection.prepareStatement("insert into actor values(?, ?, ?, ?, ?)");
		  preparedStatement.setString(1, "1"); preparedStatement.setString(2,
		  "Johnny Depp"); preparedStatement.setString(3, "1963.6.9");
		  preparedStatement.setString(4, null); preparedStatement.setString(5, "Male");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "2"); preparedStatement.setString(2,
		  "Winona Ryder"); preparedStatement.setString(3, "1971.10.29");
		  preparedStatement.setString(4, null); preparedStatement.setString(5,
		  "Female"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "3"); preparedStatement.setString(2,
		  "Anne Hathaway"); preparedStatement.setString(3, "1982.11.12");
		  preparedStatement.setString(4, null); preparedStatement.setString(5,
		  "Female"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "4"); preparedStatement.setString(2,
		  "Christian Bale"); preparedStatement.setString(3, "1974.1.30");
		  preparedStatement.setString(4, null); preparedStatement.setString(5, "Male");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "5"); preparedStatement.setString(2,
		  "Heath Ledger"); preparedStatement.setString(3, "1979.4.4");
		  preparedStatement.setString(4, "2008.1.22"); preparedStatement.setString(5,
		  "Male"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "6"); preparedStatement.setString(2,
		  "Jesse Eisenberg"); preparedStatement.setString(3, "1983.10.5");
		  preparedStatement.setString(4, null); preparedStatement.setString(5, "Male");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "7"); preparedStatement.setString(2,
		  "Andrew Garfield"); preparedStatement.setString(3, "1983.8.20");
		  preparedStatement.setString(4, null); preparedStatement.setString(5, "Male");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "8"); preparedStatement.setString(2,
		  "Fionn Whitehead"); preparedStatement.setString(3, "1997.7.18");
		  preparedStatement.setString(4, null); preparedStatement.setString(5, "Male");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "9"); preparedStatement.setString(2,
		  "Tom Hardy"); preparedStatement.setString(3, "1977.9.15");
		  preparedStatement.setString(4, null); preparedStatement.setString(5, "Male");
		  preparedStatement.executeUpdate();
		  
		  // initializing movie 
		  preparedStatement =
		  connection.prepareStatement("insert into movie values(?, ?, ?, ?, ?, ?, ?)");
		  preparedStatement.setString(1, "1"); preparedStatement.setString(2,
		  "Edward Scissorhands"); preparedStatement.setInt(3, 1991);
		  preparedStatement.setInt(4, 6); preparedStatement.setInt(5, 29);
		  preparedStatement.setString(6, "20th Century Fox Presents");
		  preparedStatement.setDouble(7, 0); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "2"); preparedStatement.setString(2,
		  "Alice In Wonderland"); preparedStatement.setInt(3, 2010);
		  preparedStatement.setInt(4, 3); preparedStatement.setInt(5, 4);
		  preparedStatement.setString(6, "Korea Sony Pictures");
		  preparedStatement.setDouble(7, 0); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "3"); preparedStatement.setString(2,
		  "The Social Network"); preparedStatement.setInt(3, 2010);
		  preparedStatement.setInt(4, 11); preparedStatement.setInt(5, 18);
		  preparedStatement.setString(6, "Korea Sony Pictures");
		  preparedStatement.setDouble(7, 0); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "4"); preparedStatement.setString(2,
		  "The Dark Knight"); preparedStatement.setInt(3, 2008);
		  preparedStatement.setInt(4, 8); preparedStatement.setInt(5, 6);
		  preparedStatement.setString(6, "Warner Brothers Korea");
		  preparedStatement.setDouble(7, 0); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "5"); preparedStatement.setString(2,
		  "Dunkirk"); preparedStatement.setInt(3, 2017); preparedStatement.setInt(4,
		  7); preparedStatement.setInt(5, 13); preparedStatement.setString(6,
		  "Warner Brothers Korea"); preparedStatement.setDouble(7, 0);
		  preparedStatement.executeUpdate();
		  
		  // initailizing customer 
		  preparedStatement =
		  connection.prepareStatement("insert into customer values(?, ?, ?, ?)");
		  preparedStatement.setString(1, "1"); preparedStatement.setString(2, "Bob");
		  preparedStatement.setString(3, "1997.11.14"); preparedStatement.setString(4,
		  "Male"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "2"); preparedStatement.setString(2, "John");
		  preparedStatement.setString(3, "1978.01.23"); preparedStatement.setString(4,
		  "Male"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "3"); preparedStatement.setString(2, "Jack");
		  preparedStatement.setString(3, "1980.05.04"); preparedStatement.setString(4,
		  "Male"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "4"); preparedStatement.setString(2, "Jill");
		  preparedStatement.setString(3, "1981.04.17"); preparedStatement.setString(4,
		  "Female"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "5"); preparedStatement.setString(2, "Bell");
		  preparedStatement.setString(3, "1990.05.14"); preparedStatement.setString(4,
		  "Female"); preparedStatement.executeUpdate();
		  
		  // initializing genre 
		  preparedStatement =
		  connection.prepareStatement("insert into genre values(?)");
		  preparedStatement.setString(1, "Fantasy"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "Romance"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "Adventure");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "Family"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "Drama"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "Action"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "Mystery"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "Thriller");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "War"); preparedStatement.executeUpdate();
		  
		  // initializing movieGenre 
		  preparedStatement =
		  connection.prepareStatement("insert into movieGenre values(?, ?)");
		  preparedStatement.setString(1, "1"); preparedStatement.setString(2,
		  "Fantasy"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "1"); preparedStatement.setString(2,
		  "Romance"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "2"); preparedStatement.setString(2,
		  "Fantasy"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "2"); preparedStatement.setString(2,
		  "Adventure"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "2"); preparedStatement.setString(2,
		  "Family"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "3"); preparedStatement.setString(2, "Drama");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "4"); preparedStatement.setString(2,
		  "Action"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "4"); preparedStatement.setString(2, "Drama");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "4"); preparedStatement.setString(2,
		  "Mystery"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "4"); preparedStatement.setString(2,
		  "Thriller"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "5"); preparedStatement.setString(2,
		  "Action"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "5"); preparedStatement.setString(2, "Drama");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "5"); preparedStatement.setString(2,
		  "Thriller"); preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "5"); preparedStatement.setString(2, "War");
		  preparedStatement.executeUpdate();
		  
		  // initializing casting 
		  preparedStatement =
		  connection.prepareStatement("insert into casting values(?, ?, ?)");
		  preparedStatement.setString(1, "1"); preparedStatement.setString(2, "1");
		  preparedStatement.setString(3, "Main actor");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "1"); preparedStatement.setString(2, "2");
		  preparedStatement.setString(3, "Main actor");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "2"); preparedStatement.setString(2, "1");
		  preparedStatement.setString(3, "Main actor");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "2"); preparedStatement.setString(2, "3");
		  preparedStatement.setString(3, "Main actor");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "3"); preparedStatement.setString(2, "6");
		  preparedStatement.setString(3, "Main actor");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "3"); preparedStatement.setString(2, "7");
		  preparedStatement.setString(3, "Supporting actor");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "4"); preparedStatement.setString(2, "4");
		  preparedStatement.setString(3, "Main actor");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "4"); preparedStatement.setString(2, "5");
		  preparedStatement.setString(3, "Main actor");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "5"); preparedStatement.setString(2, "8");
		  preparedStatement.setString(3, "Main actor");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "5"); preparedStatement.setString(2, "9");
		  preparedStatement.setString(3, "Main actor");
		  preparedStatement.executeUpdate();
		  
		  // initializing make 
		  preparedStatement =
		  connection.prepareStatement("insert into make values(?, ?)");
		  preparedStatement.setString(1, "1"); preparedStatement.setString(2, "1");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "2"); preparedStatement.setString(2, "1");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "3"); preparedStatement.setString(2, "2");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "4"); preparedStatement.setString(2, "3");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.setString(1, "5"); preparedStatement.setString(2, "3");
		  preparedStatement.executeUpdate();
		  
		  preparedStatement.close();
		  
		  }catch(SQLException sqle) { System.out.println("Could not insert values " +
		  sqle); } System.out.println("Initial data inserted!");*/
		 
		 
		//2.Inserting the proper data
		  //2.1
		  /*try { 
			  //쿼리 해석 
			  System.out.println("Winona Ryder won the \"Best supporting actor\" award in 1994\n" +
		  "means...\n" + "1. insert into award values('1', 'Best supporting actor')\n"
		  + "2. select actorID from actor where actorName = 'Winona Ryder'\n" +
		  "3. select awardID from award where awardName = 'Best supporting actor'\n" +
		  "4. insert into actorObtain values(2.actorID, 3.awardID, 1994)");
		  
		  //updating 
			  statement.
		  executeUpdate("insert into award values('1', 'Best supporting actor')");
		  String actorID = null, awardID = null; ResultSet rs = statement.
		  executeQuery("select actorID from actor where actorName = 'Winona Ryder'");
		  rs.next(); actorID = rs.getString(1); rs = statement.
		  executeQuery("select awardID from award where awardName = 'Best supporting actor'"
		  ); rs.next(); awardID = rs.getString(1); PreparedStatement
		  preparedStatement =
		  connection.prepareStatement("insert into actorObtain values(?, ?, ?)"); //앞에서 select한 값들 깔끔하게 이용위해 preparedStatement 사용
		  preparedStatement.setString(1,actorID); 
		  preparedStatement.setString(2, awardID);
		  preparedStatement.setInt(3, 1994); 
		  preparedStatement.executeUpdate();
		  System.out.println("Table updated!");
		  
		  //수행결과 출력
		  //award table출력 
		  ResultSet award = statement.executeQuery("select * from award");
		  System.out.println("--------------[award]--------------\n" + 
		  "awardID   awardName                \n" +
		  "___________________________________"); while(award.next()) {
		  System.out.print(award.getString(1)); for(int i = 0;i < (10 -
		  award.getString(1).length());i++) { System.out.print(" "); }
		  System.out.print(award.getString(2)); for(int i = 0;i < (25 -
		  award.getString(2).length());i++) { System.out.print(" "); }
		  System.out.println(); } System.out.println();
		//actorObtain table출력
		  ResultSet actorObtain = statement.executeQuery("select * from actorObtain");
		  System.out.println("------[actorObtain]------\n"  +   
		  "actorID   awardID   year \n" + "_________________________");
		  while(actorObtain.next()) { System.out.print(actorObtain.getString(1));
		  for(int i = 0;i < (10 - actorObtain.getString(1).length());i++) {
		  System.out.print(" "); } System.out.print(actorObtain.getString(2)); for(int
		  i = 0;i < (10 - actorObtain.getString(2).length());i++) {
		  System.out.print(" "); } System.out.print(actorObtain.getInt(3)); for(int i =
		  0;i < (5 - Integer.toString(actorObtain.getInt(3)).length());i++) {
		  System.out.print(" "); } System.out.println(); } System.out.println();
		  
		  
		  } catch (SQLException sqle) { System.out.println("Could not update tables " +
		  sqle); }*/
		  
		 
		
		//2.2
		/*try {
			//지시문 해석
			System.out.println("Andrew Garfield won the \"Best supporting actor\" award in 2011\n" + "means...\n"
					+ "1. select actorID from actor where actorName = 'Andrew Garfield'\n"
					+ "2. select awardID from award where awardName = 'Best supporting actor'\n"
					+ "3. insert into actorObtain values(1.actorID, 2.awardID, 2011)");
			
			//해석한 1,2,3 순서로 수행
			String actorID = null, awardID = null;
			ResultSet rs = statement.executeQuery("select actorID from actor where actorName = 'Andrew Garfield'");
			rs.next();
			actorID = rs.getString(1);
			rs = statement.executeQuery("select awardID from award where awardName = 'Best supporting actor'");
			rs.next();
			awardID = rs.getString(1);
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into actorObtain values(?, ?, ?)"); // 앞에서 select한 값들 깔끔하게 이용위해
																					// preparedStatement 사용
			preparedStatement.setString(1, actorID);
			preparedStatement.setString(2, awardID);
			preparedStatement.setInt(3, 2011);
			preparedStatement.executeUpdate();
			System.out.println("Table updated!");

			//actorObtain table출력, award table은 새로운 award가 insert되지 않았으므로 출력하지 않음.
			ResultSet actorObtain = statement.executeQuery("select * from actorObtain");
			System.out.println(
					"------[actorObtain]------\n" + "actorID   awardID   year \n" + "_________________________");
			while (actorObtain.next()) {
				System.out.print(actorObtain.getString(1));
				for (int i = 0; i < (10 - actorObtain.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(actorObtain.getString(2));
				for (int i = 0; i < (10 - actorObtain.getString(2).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(actorObtain.getInt(3));
				for (int i = 0; i < (5 - Integer.toString(actorObtain.getInt(3)).length()); i++) {
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();

		} catch (SQLException sqle) {
			System.out.println("Could not update tables " + sqle);
		}*/
		
		//2.3
		/*try {
			//지시문 해석
			System.out.println("Jesse Eisenberg won the \"Best main actor\" award in 2011\n" + "means...\n"
					+ "1. insert into award values('2', 'Best main actor')\n"
					+ "2. select actorID from actor where actorName = 'Jesse Eisenberg'\n"
					+ "3. select awardID from award where awardName = 'Best main actor'\n"
					+ "4. insert into actorObtain values(2.actorID, 3.awardID, 2011)");
			
			//해석한 1,2,3,4 수행
			statement.executeUpdate("insert into award values('2', 'Best main actor')");
			String actorID = null, awardID = null;
			ResultSet rs = statement.executeQuery("select actorID from actor where actorName = 'Jesse Eisenberg'");
			rs.next();
			actorID = rs.getString(1);
			rs = statement.executeQuery("select awardID from award where awardName = 'Best main actor'");
			rs.next();
			awardID = rs.getString(1);
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into actorObtain values(?, ?, ?)"); 
			preparedStatement.setString(1, actorID);
			preparedStatement.setString(2, awardID);
			preparedStatement.setInt(3, 2011);
			preparedStatement.executeUpdate();
			System.out.println("Table updated!");

			//award table 출력
			ResultSet award = statement.executeQuery("select * from award");
			System.out.println("--------------[award]--------------\n" 
					+ "actorID   awardID                \n"
					+ "___________________________________");
			while (award.next()) {
				System.out.print(award.getString(1));
				for (int i = 0; i < (10 - award.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(award.getString(2));
				for (int i = 0; i < (25 - award.getString(2).length()); i++) {
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();
			//actorObtain table 출력
			ResultSet actorObtain = statement.executeQuery("select * from actorObtain");
			System.out.println(
					"------[actorObtain]------\n" + "actorID   awardID   year \n" + "_________________________");
			while (actorObtain.next()) {
				System.out.print(actorObtain.getString(1));
				for (int i = 0; i < (10 - actorObtain.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(actorObtain.getString(2));
				for (int i = 0; i < (10 - actorObtain.getString(2).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(actorObtain.getInt(3));
				for (int i = 0; i < (5 - Integer.toString(actorObtain.getInt(3)).length()); i++) {
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();

		} catch (SQLException sqle) {
			System.out.println("Could not update tables " + sqle);
		}*/
		
		//2.4
		/*try {
			//지시문 해석
			System.out.println("Johnny Depp won the \"Best villain actor\" award in 2011\n" + "means...\n"
					+ "1. insert into award values('3', 'Best villain actor')\n"
					+ "2. select actorID from actor where actorName = 'Johnny Depp'\n"
					+ "3. select awardID from award where awardName = 'Best villain actor'\n"
					+ "4. insert into actorObtain values(2.actorID, 3.awardID, 2011)");

			//해석한 1,2,3,4수행
			statement.executeUpdate("insert into award values('3', 'Best villain actor')");
			String actorID = null, awardID = null;
			ResultSet rs = statement.executeQuery("select actorID from actor where actorName = 'Johnny Depp'");
			rs.next();
			actorID = rs.getString(1);
			rs = statement.executeQuery("select awardID from award where awardName = 'Best villain actor'");
			rs.next();
			awardID = rs.getString(1);
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into actorObtain values(?, ?, ?)"); 
			preparedStatement.setString(1, actorID);
			preparedStatement.setString(2, awardID);
			preparedStatement.setInt(3, 2011);
			preparedStatement.executeUpdate();
			System.out.println("Table updated!");

			//award table출력
			ResultSet award = statement.executeQuery("select * from award");
			System.out.println("--------------[award]--------------\n" 
					+ "actorID   awardID                \n"
					+ "___________________________________");
			while (award.next()) {
				System.out.print(award.getString(1));
				for (int i = 0; i < (10 - award.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(award.getString(2));
				for (int i = 0; i < (25 - award.getString(2).length()); i++) {
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();
			//actorObtain table출력
			ResultSet actorObtain = statement.executeQuery("select * from actorObtain");
			System.out.println(
					"------[actorObtain]------\n" + "actorID   awardID   year \n" + "_________________________");
			while (actorObtain.next()) {
				System.out.print(actorObtain.getString(1));
				for (int i = 0; i < (10 - actorObtain.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(actorObtain.getString(2));
				for (int i = 0; i < (10 - actorObtain.getString(2).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(actorObtain.getInt(3));
				for (int i = 0; i < (5 - Integer.toString(actorObtain.getInt(3)).length()); i++) {
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();

		} catch (SQLException sqle) {
			System.out.println("Could not update tables " + sqle);
		}*/
		
		//2.5
		/*try {
		    //지시문 해석
			System.out.println("Edward Scissorhands won the \"Best fantasy movie\" award in 1991\n" + "means...\n"
					+ "1. insert into award values('4', 'Best fantasy movie')\n"
					+ "2. select movieID from movie where movieName = 'Edward Scissorhands'\n"
					+ "3. select awardID from award where awardName = 'Best fantasy movie'\n"
					+ "4. insert into movieObtain values(2.movieID, 3.awardID, 1991)");

			//해석한 1,2,3,4수행
			statement.executeUpdate("insert into award values('4', 'Best fantasy movie')");
			String movieID = null, awardID = null;
			ResultSet rs = statement.executeQuery("select movieID from movie where movieName = 'Edward Scissorhands'");
			rs.next();
			movieID = rs.getString(1);
			rs = statement.executeQuery("select awardID from award where awardName = 'Best fantasy movie'");
			rs.next();
			awardID = rs.getString(1);
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into movieObtain values(?, ?, ?)"); 
			preparedStatement.setString(1, movieID);
			preparedStatement.setString(2, awardID);
			preparedStatement.setInt(3, 1991);
			preparedStatement.executeUpdate();
			System.out.println("Table updated!");

			//award table출력
			ResultSet award = statement.executeQuery("select * from award");
			System.out.println("--------------[award]--------------\n" 
					+ "actorID   awardID                \n"
					+ "___________________________________");
			while (award.next()) {
				System.out.print(award.getString(1));
				for (int i = 0; i < (10 - award.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(award.getString(2));
				for (int i = 0; i < (25 - award.getString(2).length()); i++) {
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();
			//movieObtain table 출력
			ResultSet movieObtain = statement.executeQuery("select * from movieObtain");
			System.out.println(
					"------[movieObtain]------\n" + "movieID   awardID   year \n" + "_________________________");
			while (movieObtain.next()) {
				System.out.print(movieObtain.getString(1));
				for (int i = 0; i < (10 - movieObtain.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(movieObtain.getString(2));
				for (int i = 0; i < (10 - movieObtain.getString(2).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(movieObtain.getInt(3));
				for (int i = 0; i < (5 - Integer.toString(movieObtain.getInt(3)).length()); i++) {
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();

		} catch (SQLException sqle) {
			System.out.println("Could not update tables " + sqle);
		}*/
		
		//2.6
		/*try {
			//지시문 해석
			System.out.println("The Dark Knight won the \"Best picture\" award in 2009\n" + "means...\n"
					+ "1. insert into award values('5', 'Best picture')\n"
					+ "2. select movieID from movie where movieName = 'The Dark Knight'\n"
					+ "3. select awardID from award where awardName = 'Best picture'\n"
					+ "4. insert into movieObtain values(2.movieID, 3.awardID, 2009)");
			
			//해석한 1,2,3,4수행
			statement.executeUpdate("insert into award values('5', 'Best picture')");
			String movieID = null, awardID = null;
			ResultSet rs = statement.executeQuery("select movieID from movie where movieName = 'The Dark Knight'");
			rs.next();
			movieID = rs.getString(1);
			rs = statement.executeQuery("select awardID from award where awardName = 'Best picture'");
			rs.next();
			awardID = rs.getString(1);
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into movieObtain values(?, ?, ?)"); 
			preparedStatement.setString(1, movieID);
			preparedStatement.setString(2, awardID);
			preparedStatement.setInt(3, 2009);
			preparedStatement.executeUpdate();
			System.out.println("Table updated!");

			//award table출력
			ResultSet award = statement.executeQuery("select * from award");
			System.out.println("--------------[award]--------------\n" 
					+ "actorID   awardID                \n"
					+ "___________________________________");
			while (award.next()) {
				System.out.print(award.getString(1));
				for (int i = 0; i < (10 - award.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(award.getString(2));
				for (int i = 0; i < (25 - award.getString(2).length()); i++) {
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();
			//movie table 출력
			ResultSet movieObtain = statement.executeQuery("select * from movieObtain");
			System.out.println(
					"------[movieObtain]------\n" + "movieID   awardID   year \n" + "_________________________");
			while (movieObtain.next()) {
				System.out.print(movieObtain.getString(1));
				for (int i = 0; i < (10 - movieObtain.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(movieObtain.getString(2));
				for (int i = 0; i < (10 - movieObtain.getString(2).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(movieObtain.getInt(3));
				for (int i = 0; i < (5 - Integer.toString(movieObtain.getInt(3)).length()); i++) {
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();

		} catch (SQLException sqle) {
			System.out.println("Could not update tables " + sqle);
		}*/
		
		//2.7
		/*try {
			//지시문 해석
			System.out.println("Alice In Wonderland won the \"Best fantasy movie\" award in 2011\n" + "means...\n"
					+ "1. select movieID from movie where movieName = 'Alice In Wonderland'\n"
					+ "2. select awardID from award where awardName = 'Best fantasy movie'\n"
					+ "3. insert into movieObtain values(1.movieID, 2.awardID, 2011)");

			//해석한 1,2,3 수행
			String movieID = null, awardID = null;
			ResultSet rs = statement.executeQuery("select movieID from movie where movieName = 'Alice In Wonderland'");
			rs.next();
			movieID = rs.getString(1);
			rs = statement.executeQuery("select awardID from award where awardName = 'Best fantasy movie'");
			rs.next();
			awardID = rs.getString(1);
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into movieObtain values(?, ?, ?)"); 
			preparedStatement.setString(1, movieID);
			preparedStatement.setString(2, awardID);
			preparedStatement.setInt(3, 2011);
			preparedStatement.executeUpdate();
			System.out.println("Table updated!");

			//award table출력
			ResultSet award = statement.executeQuery("select * from award");
			System.out.println("--------------[award]--------------\n" 
					+ "actorID   awardID                \n"
					+ "___________________________________");
			while (award.next()) {
				System.out.print(award.getString(1));
				for (int i = 0; i < (10 - award.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(award.getString(2));
				for (int i = 0; i < (25 - award.getString(2).length()); i++) {
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();
			//movieObtain table출력
			ResultSet movieObtain = statement.executeQuery("select * from movieObtain");
			System.out.println(
					"------[movieObtain]------\n" + "movieID   awardID   year \n" + "_________________________");
			while (movieObtain.next()) {
				System.out.print(movieObtain.getString(1));
				for (int i = 0; i < (10 - movieObtain.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(movieObtain.getString(2));
				for (int i = 0; i < (10 - movieObtain.getString(2).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(movieObtain.getInt(3));
				for (int i = 0; i < (5 - Integer.toString(movieObtain.getInt(3)).length()); i++) {
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();

		} catch (SQLException sqle) {
			System.out.println("Could not update tables " + sqle);
		}*/
		
		//2.8
		/*try {
			//지시문 해석
			System.out.println("David Fincher won the \"Best director\" award in 2011\n" + "means...\n"
					+ "1. insert into award values('6', 'Best director')\n"
					+ "2. select directorID from director where directorName = 'David Fincher'\n"
					+ "3. select awardID from award where awardName = 'Best director'\n"
					+ "4. insert into directorObtain values(2.directorID, 3.awardID, 2011)");

			//해석한 1,2,3,4수행
			statement.executeUpdate("insert into award values('6', 'Best director')");
			String directorID = null, awardID = null;
			ResultSet rs = statement.executeQuery("select directorID from director where directorName = 'David Fincher'");
			rs.next();
			directorID = rs.getString(1);
			rs = statement.executeQuery("select awardID from award where awardName = 'Best director'");
			rs.next();
			awardID = rs.getString(1);
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into directorObtain values(?, ?, ?)");
			preparedStatement.setString(1, directorID);
			preparedStatement.setString(2, awardID);
			preparedStatement.setInt(3, 2011);
			preparedStatement.executeUpdate();
			System.out.println("Table updated!");

			//award table출력
			ResultSet award = statement.executeQuery("select * from award");
			System.out.println("--------------[award]--------------\n" 
					+ "actorID   awardID                \n"
					+ "___________________________________");
			while (award.next()) {
				System.out.print(award.getString(1));
				for (int i = 0; i < (10 - award.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(award.getString(2));
				for (int i = 0; i < (25 - award.getString(2).length()); i++) {
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();
			//directorObtain table출력
			ResultSet directorObtain = statement.executeQuery("select * from directorObtain");
			System.out.println(
					"------[directorObtain]------\n" 
				+ "directorID   awardID   year \n" 
				+ "____________________________");
			while (directorObtain.next()) {
				System.out.print(directorObtain.getString(1));
				for (int i = 0; i < (13 - directorObtain.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(directorObtain.getString(2));
				for (int i = 0; i < (10 - directorObtain.getString(2).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(directorObtain.getInt(3));
				for (int i = 0; i < (5 - Integer.toString(directorObtain.getInt(3)).length()); i++) {
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();

		} catch (SQLException sqle) {
			System.out.println("Could not update tables " + sqle);
		}*/
		
		//3.Inserting data to the tables and update avgRate
		//3.1
		/*try {
			//지시문 해석
			System.out.println("Bob rates 5 to \"The Dark Knight\".\n" + "means...\n"
					+ "1. select customerID from customer where customerName = 'Bob'\n"
					+ "2. select distinct movieID from movie where movieName = 'The Dark Knight'\n"
					+ "3. insert into customerRate values(1.customerID, 2.movieID, 5)\n"
					+ "4. update movie set avgRate = (select avg(rate) from customerRate where movieID = 2.movieID)\n"
					+ "\twhere movieID = 2.movieID");

			//해석한 1,2,3,4수행
			String customerID = null, movieID = null;
			ResultSet rs = statement.executeQuery("select customerID from customer where customerName = 'Bob'");
			rs.next();
			customerID = rs.getString(1);
			rs = statement.executeQuery("select distinct movieID from movie where movieName = 'The Dark Knight'");
			rs.next();
			movieID = rs.getString(1);
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into customerRate values(?, ?, ?)"); 
			preparedStatement.setString(1, customerID);
			preparedStatement.setString(2, movieID);
			preparedStatement.setInt(3, 5);
			preparedStatement.executeUpdate();
			preparedStatement = connection
					.prepareStatement("update movie set avgRate = (select avg(rate) from customerRate where movieID = ?) where movieID = ?");
			preparedStatement.setString(1, movieID);
			preparedStatement.setString(2, movieID);
			preparedStatement.executeUpdate();
			System.out.println("Table updated!");

			//customerRate table 출력
			ResultSet customerRate = statement.executeQuery("select * from customerRate");
			System.out.println(
					"-------[customerRate]-------\n" 
				    + "customerID   movieID   rate \n" 
					+ "____________________________");
			while (customerRate.next()) {
				System.out.print(customerRate.getString(1));
				for (int i = 0; i < (13 - customerRate.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(customerRate.getString(2));
				for (int i = 0; i < (10 - customerRate.getString(2).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(Double.parseDouble(String.format("%.2f",customerRate.getDouble(3))));
				System.out.println(" ");
			}
			System.out.println();
			//movie table출력
			ResultSet movie = statement.executeQuery("select * from movie");
			System.out.println(
					"-------[movie]-------\n" 
				    + "movieID   movieName                releaseYear  releaseMonth releaseDate  publisherName                 avgRate   \n" 
					+ "__________________________________________________________________________________________________________________");
			while (movie.next()) {
				System.out.print(movie.getString(1));   //movieID 출력
				for (int i = 0; i < (10 - movie.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(movie.getString(2));   //movieName출력
				for (int i = 0; i < (25 - movie.getString(2).length()); i++) {
					System.out.print(" ");
				}
				for(int i = 3;i < 6;i++) {              //releaseYear, releaseMonth, releaseDate 출력
					System.out.print(movie.getInt(i));
					for (int j = 0; j < (13 - Integer.toString(movie.getInt(i)).length()); j++) {
						System.out.print(" ");
					}
				}
				System.out.print(movie.getString(6));   //publisherName출력
				for (int i = 0; i < (30 - movie.getString(6).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(Double.parseDouble(String.format("%.2f",movie.getDouble(7))));      //avgRate출력
				for (int i = 0; i < 6; i++) {
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();

		} catch (SQLException sqle) {
			System.out.println("Could not update tables " + sqle);
		}*/
		
		//3.2
		/*try {
			//주어진 request를 sql식으로 해석
			System.out.println("Bell rates 5 to the movies whose director is \"Tim Burton\".\n" + "means...\n"
					+ "1. select customerID from customer where customerName = 'Bell'\n"
					+ "2. select distinct movieID from director natural join make where directorName = 'Tim Burton'\n"
					+ "3. for each movieID, insert into customerRate values(1.customerID, 2.movieID, 5)\n"
					+ "4. for each movieID, update movie set avgRate = (select avg(rate) from customerRate where movieID = 2.movieID)\n"
					+ "\twhere movieID = 2.movieID");
			
			//sql문 실행하여 update tables
			String customerID = null;
			ArrayList<String> movieIDs = new ArrayList<String>();   //movieID가 여러개 select될 수 있으므로 arraylist 이용
			//위의 해석에서 1번 수행
			ResultSet rs = statement.executeQuery("select customerID from customer where customerName = 'Bell'");
			rs.next();
			customerID = rs.getString(1);
			//2번 수행
			rs = statement.executeQuery("select distinct movieID from director natural join make where directorName = 'Tim Burton'");
			while(rs.next()) {
				movieIDs.add(rs.getString(1));
			}
			//3번 수행
			for(int i = 0;i < movieIDs.size();i++) {
				PreparedStatement preparedStatement = connection
						.prepareStatement("insert into customerRate values(?, ?, ?)"); 
				preparedStatement.setString(1, customerID);
				preparedStatement.setString(2, movieIDs.get(i));
				preparedStatement.setInt(3, 5);
				preparedStatement.executeUpdate();
			}
			//4번 수행
			for(int i = 0;i < movieIDs.size();i++) {
				PreparedStatement preparedStatement = connection
						.prepareStatement("update movie set avgRate = (select avg(rate) from customerRate where movieID = ?) where movieID = ?");
				preparedStatement.setString(1, movieIDs.get(i));
				preparedStatement.setString(2, movieIDs.get(i));
				preparedStatement.executeUpdate();
			}
			System.out.println("Table updated!");

			//실행 결과 출력
			//customerRate table출력
			ResultSet customerRate = statement.executeQuery("select * from customerRate");
			System.out.println(
					"-------[customerRate]-------\n" 
				    + "customerID   movieID   rate \n" 
					+ "____________________________");
			while (customerRate.next()) {
				System.out.print(customerRate.getString(1));
				for (int i = 0; i < (13 - customerRate.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(customerRate.getString(2));
				for (int i = 0; i < (10 - customerRate.getString(2).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(Double.parseDouble(String.format("%.2f",customerRate.getDouble(3))));
				System.out.println(" ");
			}
			System.out.println();
			//movie table출력
			ResultSet movie = statement.executeQuery("select * from movie");
			System.out.println(
					"-------[movie]-------\n" 
				    + "movieID   movieName                releaseYear  releaseMonth releaseDate  publisherName                 avgRate   \n" 
					+ "__________________________________________________________________________________________________________________");
			while (movie.next()) {
				System.out.print(movie.getString(1));   //movieID 출력
				for (int i = 0; i < (10 - movie.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(movie.getString(2));   //movieName출력
				for (int i = 0; i < (25 - movie.getString(2).length()); i++) {
					System.out.print(" ");
				}
				for(int i = 3;i < 6;i++) {              //releaseYear, releaseMonth, releaseDate 출력
					System.out.print(movie.getInt(i));
					for (int j = 0; j < (13 - Integer.toString(movie.getInt(i)).length()); j++) {
						System.out.print(" ");
					}
				}
				System.out.print(movie.getString(6));   //publisherName출력
				for (int i = 0; i < (30 - movie.getString(6).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(Double.parseDouble(String.format("%.2f",movie.getDouble(7))));      //avgRate출력
				for (int i = 0; i < 6; i++) {
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();

		} catch (SQLException sqle) {
			System.out.println("Could not update tables " + sqle);
		}*/
		
		//3.3
		/*try {
			//주어진 request를 sql식으로 해석
			System.out.println("Jill rates 4 to the movies whose main actor is female.\n" + "means...\n"
					+ "1. select customerID from customer where customerName = 'Jill'\n"
					+ "2. select distinct movieID from actor natural join casting where role = 'Main actor' and gender = 'Female'\n"
					+ "3. for each movieID, insert into customerRate values(1.customerID, 2.movieID, 4)\n"
					+ "4. for each movieID, update movie set avgRate = (select avg(rate) from customerRate where movieID = 2.movieID)\n"
					+ "\twhere movieID = 2.movieID");
			
			//sql문 실행하여 update tables
			String customerID = null;
			ArrayList<String> movieIDs = new ArrayList<String>();   //movieID가 여러개 select될 수 있으므로 arraylist 이용
			//위의 해석에서 1번 수행
			ResultSet rs = statement.executeQuery("select customerID from customer where customerName = 'Jill'");
			rs.next();
			customerID = rs.getString(1);
			//2번 수행
			rs = statement.executeQuery("select distinct movieID from actor natural join casting where role = 'Main actor' and gender = 'Female'");
			while(rs.next()) {
				movieIDs.add(rs.getString(1));
			}
			//3번 수행
			for(int i = 0;i < movieIDs.size();i++) {
				PreparedStatement preparedStatement = connection
						.prepareStatement("insert into customerRate values(?, ?, ?)"); 
				preparedStatement.setString(1, customerID);
				preparedStatement.setString(2, movieIDs.get(i));
				preparedStatement.setInt(3, 4);
				preparedStatement.executeUpdate();
			}
			//4번 수행
			for(int i = 0;i < movieIDs.size();i++) {
				PreparedStatement preparedStatement = connection
						.prepareStatement("update movie set avgRate = (select avg(rate) from customerRate where movieID = ?) where movieID = ?");
				preparedStatement.setString(1, movieIDs.get(i));
				preparedStatement.setString(2, movieIDs.get(i));
				preparedStatement.executeUpdate();
			}
			System.out.println("Table updated!");

			//실행 결과 출력
			//customerRate table출력
			ResultSet customerRate = statement.executeQuery("select * from customerRate");
			System.out.println(
					"-------[customerRate]-------\n" 
				    + "customerID   movieID   rate \n" 
					+ "____________________________");
			while (customerRate.next()) {
				System.out.print(customerRate.getString(1));
				for (int i = 0; i < (13 - customerRate.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(customerRate.getString(2));
				for (int i = 0; i < (10 - customerRate.getString(2).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(Double.parseDouble(String.format("%.2f",customerRate.getDouble(3))));
				System.out.println(" ");
			}
			System.out.println();
			//movie table출력
			ResultSet movie = statement.executeQuery("select * from movie");
			System.out.println(
					"-------[movie]-------\n" 
				    + "movieID   movieName                releaseYear  releaseMonth releaseDate  publisherName                 avgRate   \n" 
					+ "__________________________________________________________________________________________________________________");
			while (movie.next()) {
				System.out.print(movie.getString(1));   //movieID 출력
				for (int i = 0; i < (10 - movie.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(movie.getString(2));   //movieName출력
				for (int i = 0; i < (25 - movie.getString(2).length()); i++) {
					System.out.print(" ");
				}
				for(int i = 3;i < 6;i++) {              //releaseYear, releaseMonth, releaseDate 출력
					System.out.print(movie.getInt(i));
					for (int j = 0; j < (13 - Integer.toString(movie.getInt(i)).length()); j++) {
						System.out.print(" ");
					}
				}
				System.out.print(movie.getString(6));   //publisherName출력
				for (int i = 0; i < (30 - movie.getString(6).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(Double.parseDouble(String.format("%.2f",movie.getDouble(7))));      //avgRate출력
				for (int i = 0; i < 6; i++) {
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();

		} catch (SQLException sqle) {
			System.out.println("Could not update tables " + sqle);
		}*/
		
		//3.4
		/*try {
			//주어진 request를 sql식으로 해석
			System.out.println("Jack rates 4 to the fantasy movies.\n" + "means...\n"
					+ "1. select customerID from customer where customerName = 'Jack'\n"
					+ "2. select distinct movieID from movieGenre where genreName = 'Fantasy'\n"
					+ "3. for each movieID, insert into customerRate values(1.customerID, 2.movieID, 4)\n"
					+ "4. for each movieID, update movie set avgRate = (select avg(rate) from customerRate where movieID = 2.movieID)\n"
					+ "\twhere movieID = 2.movieID");
			
			//sql문 실행하여 update tables
			String customerID = null;
			ArrayList<String> movieIDs = new ArrayList<String>();   //movieID가 여러개 select될 수 있으므로 arraylist 이용
			//위의 해석에서 1번 수행
			ResultSet rs = statement.executeQuery("select customerID from customer where customerName = 'Jack'");
			rs.next();
			customerID = rs.getString(1);
			//2번 수행
			rs = statement.executeQuery("select distinct movieID from movieGenre where genreName = 'Fantasy'");
			while(rs.next()) {
				movieIDs.add(rs.getString(1));
			}
			//3번 수행
			for(int i = 0;i < movieIDs.size();i++) {
				PreparedStatement preparedStatement = connection
						.prepareStatement("insert into customerRate values(?, ?, ?)"); 
				preparedStatement.setString(1, customerID);
				preparedStatement.setString(2, movieIDs.get(i));
				preparedStatement.setInt(3, 4);
				preparedStatement.executeUpdate();
			}
			//4번 수행
			for(int i = 0;i < movieIDs.size();i++) {
				PreparedStatement preparedStatement = connection
						.prepareStatement("update movie set avgRate = (select avg(rate) from customerRate where movieID = ?) where movieID = ?");
				preparedStatement.setString(1, movieIDs.get(i));
				preparedStatement.setString(2, movieIDs.get(i));
				preparedStatement.executeUpdate();
			}
			System.out.println("Table updated!");

			//실행 결과 출력
			//customerRate table출력
			ResultSet customerRate = statement.executeQuery("select * from customerRate");
			System.out.println(
					"-------[customerRate]-------\n" 
				    + "customerID   movieID   rate \n" 
					+ "____________________________");
			while (customerRate.next()) {
				System.out.print(customerRate.getString(1));
				for (int i = 0; i < (13 - customerRate.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(customerRate.getString(2));
				for (int i = 0; i < (10 - customerRate.getString(2).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(Double.parseDouble(String.format("%.2f",customerRate.getDouble(3))));
				System.out.println(" ");
			}
			System.out.println();
			//movie table출력
			ResultSet movie = statement.executeQuery("select * from movie");
			System.out.println(
					"-------[movie]-------\n" 
				    + "movieID   movieName                releaseYear  releaseMonth releaseDate  publisherName                 avgRate   \n" 
					+ "__________________________________________________________________________________________________________________");
			while (movie.next()) {
				System.out.print(movie.getString(1));   //movieID 출력
				for (int i = 0; i < (10 - movie.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(movie.getString(2));   //movieName출력
				for (int i = 0; i < (25 - movie.getString(2).length()); i++) {
					System.out.print(" ");
				}
				for(int i = 3;i < 6;i++) {              //releaseYear, releaseMonth, releaseDate 출력
					System.out.print(movie.getInt(i));
					for (int j = 0; j < (13 - Integer.toString(movie.getInt(i)).length()); j++) {
						System.out.print(" ");
					}
				}
				System.out.print(movie.getString(6));   //publisherName출력
				for (int i = 0; i < (30 - movie.getString(6).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(Double.parseDouble(String.format("%.2f",movie.getDouble(7))));      //avgRate출력
				for (int i = 0; i < 6; i++) {
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();

		} catch (SQLException sqle) {
			System.out.println("Could not update tables " + sqle);
		}*/
		
		//3.5
		/*try {
			//주어진 request를 sql식으로 해석
			System.out.println("John rates 5 to the movies whose director won the \"Best director\" award.\n" + "means...\n"
					+ "1. select customerID from customer where customerName = 'John'\n"
					+ "2. select distinct movieID from award natural join directorObtain natural join make where awardName = 'Best director'\n"
					//이 때 distinct movieID한 이유: 현재는 그런 인스턴스가 없지만 세개 natural join시 
					//(movieID, directorID,...)에서 (1,1,...)과 (1,2,...)이렇게 다른 경우가 되어 같은movieID가 여러개 select되는 상황이 발생가능. 이럴경우 4.avgRate계산값이 달라지게 됨.
					+ "3. for each movieID, insert into customerRate values(1.customerID, 2.movieID, 5)\n"
					+ "4. for each movieID, update movie set avgRate = (select avg(rate) from customerRate where movieID = 2.movieID)\n"
					+ "\twhere movieID = 2.movieID");
			
			//sql문 실행하여 update tables
			String customerID = null;
			ArrayList<String> movieIDs = new ArrayList<String>();   //movieID가 여러개 select될 수 있으므로 arraylist 이용
			//위의 해석에서 1번 수행
			ResultSet rs = statement.executeQuery("select customerID from customer where customerName = 'John'");
			rs.next();
			customerID = rs.getString(1);
			//2번 수행
			rs = statement.executeQuery("select distinct movieID from award natural join directorObtain natural join make where awardName = 'Best director'");
			while(rs.next()) {
				movieIDs.add(rs.getString(1));
			}
			//3번 수행
			for(int i = 0;i < movieIDs.size();i++) {
				PreparedStatement preparedStatement = connection
						.prepareStatement("insert into customerRate values(?, ?, ?)"); 
				preparedStatement.setString(1, customerID);
				preparedStatement.setString(2, movieIDs.get(i));
				preparedStatement.setInt(3, 5);
				preparedStatement.executeUpdate();
			}
			//4번 수행
			for(int i = 0;i < movieIDs.size();i++) {
				PreparedStatement preparedStatement = connection
						.prepareStatement("update movie set avgRate = (select avg(rate) from customerRate where movieID = ?) where movieID = ?");
				preparedStatement.setString(1, movieIDs.get(i));
				preparedStatement.setString(2, movieIDs.get(i));
				preparedStatement.executeUpdate();
			}
			System.out.println("Table updated!");

			//실행 결과 출력
			//customerRate table출력
			ResultSet customerRate = statement.executeQuery("select * from customerRate");
			System.out.println(
					"-------[customerRate]-------\n" 
				    + "customerID   movieID   rate \n" 
					+ "____________________________");
			while (customerRate.next()) {
				System.out.print(customerRate.getString(1));
				for (int i = 0; i < (13 - customerRate.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(customerRate.getString(2));
				for (int i = 0; i < (10 - customerRate.getString(2).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(Double.parseDouble(String.format("%.2f",customerRate.getDouble(3))));
				System.out.println(" ");
			}
			System.out.println();
			//movie table출력
			ResultSet movie = statement.executeQuery("select * from movie");
			System.out.println(
					"-------[movie]-------\n" 
				    + "movieID   movieName                releaseYear  releaseMonth releaseDate  publisherName                 avgRate   \n" 
					+ "__________________________________________________________________________________________________________________");
			while (movie.next()) {
				System.out.print(movie.getString(1));   //movieID 출력
				for (int i = 0; i < (10 - movie.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(movie.getString(2));   //movieName출력
				for (int i = 0; i < (25 - movie.getString(2).length()); i++) {
					System.out.print(" ");
				}
				for(int i = 3;i < 6;i++) {              //releaseYear, releaseMonth, releaseDate 출력
					System.out.print(movie.getInt(i));
					for (int j = 0; j < (13 - Integer.toString(movie.getInt(i)).length()); j++) {
						System.out.print(" ");
					}
				}
				System.out.print(movie.getString(6));   //publisherName출력
				for (int i = 0; i < (30 - movie.getString(6).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(Double.parseDouble(String.format("%.2f",movie.getDouble(7))));      //avgRate출력
				for (int i = 0; i < 6; i++) {
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();

		} catch (SQLException sqle) {
			System.out.println("Could not update tables " + sqle);
		}*/
		
		//4. selection
		/*try {
			//지시문 해석
			System.out.println("Select the names of the movies whose actor are dead.\n"
					+ "means...\n"
					+ "select movieName from movie where movieID in (select movieID from actor natural join casting where dateOfDeath is not null)");
			
			//select 수행
			ResultSet rs = statement.executeQuery("select movieName from movie where movieID in (select movieID from actor natural join casting where dateOfDeath is not null)");
			System.out.println("arguments selected!\n");
			
			//selected result 출력
			while(rs.next()) {
				System.out.print(rs.getString(1) + "   ");
			}
			System.out.println();
			
		}catch(SQLException sqle) {
			System.out.println("Could not select column " + sqle);
		}*/
		
		//5. selection
		/*try {
			//지시문 해석
			System.out.println("Select the names of the directors who cast the same actor more than once.\n" //같은 영화상에서 같은 배우 다른 역할로 두번이상 캐스팅하는 경우도 포함하는 것으로 해석함.
					+ "means...\n"
					+ "select directorName from director where directorID in \n"
					+ "\t\t(select directorID from casting natural join make group by actorID, directorID \n"
					+ "\t\thaving count(distinct movieID) > 1 or count(distinct role) > 1)");
			
			//select 수행
			ResultSet rs = statement.executeQuery("select directorName from director where directorID in (select directorID from casting natural join make group by actorID, directorID having count(distinct movieID) > 1 or count(distinct role) > 1)");
			System.out.println("arguments selected!\n");
			
			//selected result 출력
			while(rs.next()) {
				System.out.print(rs.getString(1) + "   ");
			}
			System.out.println();
			
		}catch(SQLException sqle) {
			System.out.println("Could not select column " + sqle);
		}*/
		
		//6. selection
		/*try {
			//지시문 해석
			System.out.println("Select the names of the movies and the genres, where movies have the common genre.\n"
					+ "means...\n"
					+ "select distinct movieName, genreName from movie natural join movieGenre where genreName in \n"
					+ "\t\t(select genreName from movie natural join movieGenre group by genreName having count(distinct movieID) > 1)");
			
			//select 수행
			ResultSet rs = statement.executeQuery("select distinct movieName, genreName from movie natural join movieGenre where genreName in (select genreName from movie natural join movieGenre group by genreName having count(distinct movieID) > 1)");
			System.out.println("arguments selected!\n");
			
			//selected result 출력
			while(rs.next()) {
				System.out.println("(" + rs.getString(1) + ", " + rs.getString(2) + ")");
			}
			
		}catch(SQLException sqle) {
			System.out.println("Could not select column " + sqle);
		}*/
		
		//7. deletion
		/*try {
			//지시문 해석
			System.out.println("Delete the movies whose director or actor did not get any award and delete data from related tables.\n"
					+ "means...\n"
					+ "delete from movie where movieID in (select movieID from movie \n"
					+ "\t\twhere movieID not in (select distinct movieID from actorObtain natural join casting) \n"
					+ "\t\tand movieID not in (select distinct movieID from directorObtain natural join make))");
					
			//delete수행 - 앞에 foreign key마다 on delete cascade 해놓았으므로 movie가 (referenced) 지워지면 해당하는 다른 table data들도 지워짐
			statement.executeUpdate("delete from movie where movieID in (select movieID from movie where movieID not in (select distinct movieID from actorObtain natural join casting) and movieID not in (select distinct movieID from directorObtain natural join make))");
			System.out.println("Successfully deleted!\n");
			
			//delete결과출력
			//movie table출력
			ResultSet movie = statement.executeQuery("select * from movie");
			System.out.println(
					"-------[movie]-------\n" 
				    + "movieID   movieName                releaseYear  releaseMonth releaseDate  publisherName                 avgRate   \n" 
					+ "__________________________________________________________________________________________________________________");
			while (movie.next()) {
				System.out.print(movie.getString(1));   //movieID 출력
				for (int i = 0; i < (10 - movie.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(movie.getString(2));   //movieName출력
				for (int i = 0; i < (25 - movie.getString(2).length()); i++) {
					System.out.print(" ");
				}
				for(int i = 3;i < 6;i++) {              //releaseYear, releaseMonth, releaseDate 출력
					System.out.print(movie.getInt(i));
					for (int j = 0; j < (13 - Integer.toString(movie.getInt(i)).length()); j++) {
						System.out.print(" ");
					}
				}
				System.out.print(movie.getString(6));   //publisherName출력
				for (int i = 0; i < (30 - movie.getString(6).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(Double.parseDouble(String.format("%.2f",movie.getDouble(7))));      //avgRate출력
				for (int i = 0; i < 6; i++) {
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();
			
			//movieGenre table출력
			ResultSet movieGenre = statement.executeQuery("select * from movieGenre");
			System.out.println(
					"----[movieGenre]----\n" 
				    + "movieID   genreName \n" 
					+ "____________________");
			while (movieGenre.next()) {
				System.out.print(movieGenre.getString(1));
				for (int i = 0; i < (10 - movieGenre.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(movieGenre.getString(2));
				for (int i = 0; i < (10 - movieGenre.getString(2).length()); i++) {
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();
			
			//casting table출력
			ResultSet casting = statement.executeQuery("select * from casting");
			System.out.println(
					"---------------[casting]---------------\n" 
				    + "movieID   actorID   role                \n" 
					+ "________________________________________");
			while (casting.next()) {
				for(int j = 1;j < 3;j++) {
					System.out.print(casting.getString(j));
					for (int i = 0; i < (10 - casting.getString(j).length()); i++) {
						System.out.print(" ");
					}
				}
				System.out.print(casting.getString(3));
				for (int i = 0; i < (20 - casting.getString(3).length()); i++) {
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();
			
			//make table출력
			ResultSet make = statement.executeQuery("select * from make");
			System.out.println(
					"-------[make]-------\n" 
				    + "movieID   directorID \n" 
					+ "_____________________");
			while (make.next()) {
				System.out.print(make.getString(1));
				for (int i = 0; i < (10 - make.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(make.getString(2));
				for (int i = 0; i < (11 - make.getString(2).length()); i++) {
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println();
			
			//customerRate table출력
			ResultSet customerRate = statement.executeQuery("select * from customerRate");
			System.out.println(
					"-------[customerRate]-------\n" 
				    + "customerID   movieID   rate \n" 
					+ "____________________________");
			while (customerRate.next()) {
				System.out.print(customerRate.getString(1));
				for (int i = 0; i < (13 - customerRate.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(customerRate.getString(2));
				for (int i = 0; i < (10 - customerRate.getString(2).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(Double.parseDouble(String.format("%.2f",customerRate.getDouble(3))));
				System.out.println(" ");
			}
			System.out.println();

		}catch(SQLException sqle) {
			System.out.println("Could not delete " + sqle);
		}*/
		
		//8. deletion
		/*try {
			//지시문 해석
			System.out.println("Delete all customers and delete data from related tables.\n"
					+ "means...\n"
					+ "delete from customer");
			
			//delete문 실행-앞의 create table에서 foreign key마다 on delete cascade해주었으므로 customer(referenced)삭제시 customerRate자동으로 삭제됨
			statement.executeUpdate("delete from customer");
			System.out.println("Successfully deleted!");
			
			//실행결과 출력
			//customer table출력
			ResultSet customer = statement.executeQuery("select * from customer");
			System.out.println(
					"-----------------------[customer]-----------------------\n" 
				    + "customerID    customerName  dateOfBirth   gender        \n" 
					+ "________________________________________________________");
			while (customer.next()) {
				for(int j = 1;j < 5;j++) {
				System.out.print(customer.getString(j));
				for (int i = 0; i < (14 - customer.getString(j).length()); i++) {
					System.out.print(" ");
				}
				System.out.println();
				}
			}
			System.out.println();
			//customerRate table출력
			ResultSet customerRate = statement.executeQuery("select * from customerRate");
			System.out.println(
					"-------[customerRate]-------\n" 
				    + "customerID   movieID   rate \n" 
					+ "____________________________");
			while (customerRate.next()) {
				System.out.print(customerRate.getString(1));
				for (int i = 0; i < (13 - customerRate.getString(1).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(customerRate.getString(2));
				for (int i = 0; i < (10 - customerRate.getString(2).length()); i++) {
					System.out.print(" ");
				}
				System.out.print(Double.parseDouble(String.format("%.2f",customerRate.getDouble(3))));
				System.out.println(" ");
			}
			System.out.println();
			
		}catch(SQLException sqle) {
			System.out.println("Could not delete " + sqle);
		}*/
		
		//9. drop
		/*try {
			//지시문 해석
			System.out.println("Delete all tables and data.\n"
					+ "means...\n"
					+ "가장 바깥쪽 referencing table부터 차례로 drop");
			
			//drop table문들 수행
			//1. movieGenre, movieObtain, actorObtain, directorObtain, casting, make, customerRate 먼저 drop하고
			//2. award, genre를 drop
			//3. 마지막으로 director, actor, movie, customer을 drop
			statement.executeUpdate("drop table movieGenre");
			statement.executeUpdate("drop table movieObtain");
			statement.executeUpdate("drop table actorObtain");
			statement.executeUpdate("drop table directorObtain");
			statement.executeUpdate("drop table casting");
			statement.executeUpdate("drop table make");
			statement.executeUpdate("drop table customerRate");
			statement.executeUpdate("drop table award");
			statement.executeUpdate("drop table genre");
			statement.executeUpdate("drop table director");
			statement.executeUpdate("drop table actor");
			statement.executeUpdate("drop table movie");
			statement.executeUpdate("drop table customer");
			System.out.println("All tables dropped!");
			
		}catch(SQLException sqle) {
			System.out.println("Could not drop " + sqle);
		}*/
		
		statement.close();
		
		connection.close();
	}
	
}