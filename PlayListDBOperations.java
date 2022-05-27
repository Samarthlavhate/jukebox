
import java.sql.*;

public class PlayListDBOperations {


    Connection connection;
    PlayListDBOperations()
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
    protected boolean addPlayList(String playListName,int TrackId) {
        boolean output=false;
        int playlistId= checkAndInsertPlayList(playListName);

        try {
        PreparedStatement p1 = connection.prepareStatement("insert into playlistContentTable(playlistId,TrackID)values(?,?)");

        p1.setInt(1,playlistId);
        p1.setInt(2,TrackId);
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

    private int checkAndInsertPlayList(String playListName) {
       int id = 0;
       try {
       PreparedStatement p2 = connection.prepareStatement("select playlistId from playlist where playlistName=?");
       p2.setString(1, playListName);
       ResultSet r = p2.executeQuery();
       if(r.next()) {
       System.out.println(TEXT_PURPLE+"playlist Found");
       System.out.println();
       id = r.getInt(1);
       }
       else{
        System.out.println(TEXT_RED +"playlist not present.Inserting Playlist into the table");
        System.out.println();
        System.out.println("please wait...");
        System.out.println();
        id= insertPlayList(playListName);
        }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return id;
    }
    private int insertPlayList(String playListName) {
        int id = 0;
        try {
        PreparedStatement pc = connection.prepareStatement("insert into playlist(playlistName)values(?)",Statement.RETURN_GENERATED_KEYS);
        pc.setString(1, playListName);
        int rowAffected = pc.executeUpdate();
        if (rowAffected > 0) {
        System.out.println(TEXT_GREEN+"PlayList Creation Successful");
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
    protected int songId(String SongName) throws Exception {
        int Id=0;
        try {
        PreparedStatement P1=connection.prepareStatement("select songId from song where songName=? ");
        P1.setString(1,SongName);
        ResultSet rs =P1.executeQuery();
        if(rs.next()){
        Id=rs.getInt(1);
        }
        else{
            throw new Exception("songId not found");
        }

        } catch (SQLException e) {
        e.printStackTrace();
        }


        return Id;
    }
    protected int PodCastId(String podcastNAME)throws Exception{
        int Id=0;
        try {
        PreparedStatement P1=connection.prepareStatement("select episode from podcast where podcastName=? ");
        P1.setString(1,podcastNAME);
        ResultSet rs =P1.executeQuery();
        if(rs.next()){
        Id=rs.getInt(1);
        } else{
            throw new Exception("PodcastId not found");
        }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return Id;
    }
    protected int songIdByAlbumName(String AlbumName)throws Exception{
        int Id=0;
        try {
        PreparedStatement P1=connection.prepareStatement("select songId from song where song_album=? ");
        P1.setString(1,AlbumName);
        ResultSet rs =P1.executeQuery();
        if(rs.next()){
        Id=rs.getInt(1);
        } else{
            throw new Exception("songId not found");
        }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return Id;
    }
    protected int PodCastIdByCelebName(String CelebNAME)throws Exception{
        int Id=0;
        try {
        PreparedStatement P1=connection.prepareStatement("select episode from podcast where celebName=? ");
        P1.setString(1,CelebNAME);
        ResultSet rs =P1.executeQuery();
        if(rs.next()){
        Id=rs.getInt(1);
        }
        else{
            throw new Exception("PodcastId not found");
        }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return Id;
    }
    protected  void displayPlaylist(String playlistName){
        try {
        PreparedStatement p1=connection.prepareStatement("select playlistContentTable.TrackId from playlistContentTable join playlist on playlist.playlistId=playlistContentTable.playlistId where playlistName=? ");
        p1.setString(1,playlistName);
        ResultSet rs = p1.executeQuery();

        while(rs.next()){
        int id=rs.getInt(1);
        if(id>20004){
        SongInPlaylist(id);
        }
        else{
        podInPlaylist(id);
        }
        }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        }
     private void SongInPlaylist(int TrackId){
         try {
         PreparedStatement P2=connection.prepareStatement("select songName , songDuration from song where songId=?");
         P2.setInt(1,TrackId);
         ResultSet rp = P2.executeQuery();
         while(rp.next()){
         System.out.printf("%10s\t %s\n",rp.getString(1),rp.getString(2));
         }
         } catch (SQLException e) {
         e.printStackTrace();
         }
         }
    private void podInPlaylist(int TrackId) {
        try {
            PreparedStatement P3 = connection.prepareStatement("select podcastName , CelebName from podcast join celeb on podcast.celebid =celeb.celebid where episode=?");
            P3.setInt(1, TrackId);
            ResultSet rp = P3.executeQuery();
            while (rp.next()) {
                System.out.println(rp.getString(1) + "\t" + rp.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     protected ResultSet trackId(String playlistName) {

ResultSet rp = null;

         try {

             PreparedStatement preparedStatement = connection.prepareStatement("select trackid from playlistcontenttable where playlistid= (select playlistid from playlist where playlistname=?)");

             preparedStatement.setString(1, playlistName);

             rp = preparedStatement.executeQuery();


         } catch (SQLException e) {
             e.printStackTrace();
         }
 return rp;

     }
     protected  String songNameHAI(int trackId){
     String songsNAME = null;

         try {
             PreparedStatement   preparedStatement = connection.prepareStatement("select songname from song where songid=?");

         preparedStatement.setInt(1,trackId);
         ResultSet RS=preparedStatement.executeQuery();
         if(RS.next()){
             songsNAME=RS.getString(1);
         }
         } catch (SQLException e) {
             e.printStackTrace();
         }
        return songsNAME ;
     }
     //////
     protected void playlistAvailable(){

        try {
             PreparedStatement preparedStatement=connection.prepareStatement("select playListName from playlist");
             ResultSet rs=preparedStatement.executeQuery();
             while(rs.next()){
                 System.out.println("->"+rs.getString(1));
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }

     }
        }
