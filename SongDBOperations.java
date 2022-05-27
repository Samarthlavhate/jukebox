import java.sql.*;
import java.util.ArrayList;

public class SongDBOperations {

    Connection connection;
    SongDBOperations()
    {
        try {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "samarthlavhate00@gmail.com");
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_PURPLE = "\u001B[35m";

    protected boolean addSong(Song song) {
            boolean output=false;
            int artistId= checkAndInsertArtist(song.getSongartist());
            int genreId= checkAndInsertGenre(song.getSonggenre());

            try {
            PreparedStatement p1 = connection.prepareStatement("insert into song(songName,song_album,genreId, artistId, songDuration)values(?,?,?,?,?)");
            p1.setString(1,song.getSongname());
            p1.setString(2, song.getSong_album());
            p1.setInt(3,genreId);
            p1.setInt(4,artistId);
            p1.setString(5, song.getSongduration());
            int row= p1.executeUpdate();
            if(row>0){
            System.out.println(TEXT_GREEN+"Insertion Done");
            output=true;}
            else {
            System.out.println("Insertion failed");
            }
            } catch (SQLException e) {
            e.printStackTrace();
            }
            return output;
            }

    private int checkAndInsertArtist(String artistName) {
            int id = 0;
            try {
            PreparedStatement p2 = connection.prepareStatement("select artistId from artist where artist_name=?");
            p2.setString(1, artistName);
            ResultSet r = p2.executeQuery();
            if(r.next()) {
            System.out.println(TEXT_PURPLE+"Artist Found");
            System.out.println();
            id = r.getInt(1);
            }
            else{
            System.out.println(TEXT_RED +"Artist not present.Inserting Artist into the table");
            System.out.println();
            System.out.println("please wait...");
            System.out.println();
            id=insertArtist(artistName);
            }
            } catch (SQLException e) {
            e.printStackTrace();
            }
            return id;
                                      }
    private int insertArtist(String artistName) {
            int id = 0;
            try {
            PreparedStatement pc = connection.prepareStatement("insert into artist(artist_name)values(?)",Statement.RETURN_GENERATED_KEYS);
            pc.setString(1, artistName);
            int rowAffected = pc.executeUpdate();
            if (rowAffected > 0) {
            System.out.println(TEXT_GREEN+"Artist Creation Successful");
            System.out.println();
            ResultSet r = pc.getGeneratedKeys();
            if (r.next()) {
            id = r.getInt(1);
            }
            }
            } catch (SQLException e) {
            e.printStackTrace();
            }

            return id;
            }

   private int checkAndInsertGenre(String genre){
            int genreId=0;
            try {
            PreparedStatement p2 = connection.prepareStatement("select genreId from Genre where genre=?");
            p2.setString(1, genre);
            ResultSet r = p2.executeQuery();
            if(r.next()) {
            System.out.println(TEXT_PURPLE+"Genre Found");
            genreId = r.getInt(1);
            }
            else{
            System.out.println(TEXT_RED +"Genre not present.Inserting Genre into the table");
            System.out.println();
            System.out.print("please wait");
            System.out.print(TEXT_PURPLE+"..."+TEXT_RED+"..."+TEXT_GREEN+"...");
            System.out.println();
            genreId=insertGenre(genre);
            }
            } catch (SQLException e) {
            e.printStackTrace();
            }
            return genreId;
            }
    private int insertGenre(String genre) {
            int id = 0;
            try {
            PreparedStatement pc = connection.prepareStatement("insert into Genre(genre)values(?)",Statement.RETURN_GENERATED_KEYS);
            pc.setString(1,genre);
            int rowAffected = pc.executeUpdate();
            if (rowAffected > 0) {
            System.out.println(TEXT_GREEN+"Genre Creation Successful");
            System.out.println();
            ResultSet r = pc.getGeneratedKeys();
            if (r.next()) {
            id = r.getInt(1);
            }

            }
            } catch (SQLException e) {
            e.printStackTrace();
            }
            return id;
            }

           protected ArrayList<Song> songList(){
                 ArrayList<Song>  songArrayList =new ArrayList<>();
                 try {
                 PreparedStatement PV = connection.prepareStatement("select*from songList");
                 ResultSet rs=PV.executeQuery();
                 while(rs.next()){
                 songArrayList.add(new Song(rs.getInt(1), rs.getString(2),rs.getString(3),
                 rs.getString(4),rs.getString(5) ,rs.getString(6)));

                 }
                 } catch (SQLException e) {
                 e.printStackTrace();
                 }
                 return songArrayList;
                 }
                 }

