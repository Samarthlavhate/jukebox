import java.sql.*;
import java.util.ArrayList;

public class PodCastDBOperation {

    Connection connections;
   PodCastDBOperation()
    {
        try {
        connections = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "samarthlavhate00@gmail.com");
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_PURPLE = "\u001B[35m";

    protected boolean addPodcast(podcast podcast) {
    boolean output=false;
    int celebId= checkAndInsertCeleb(podcast.getCelebname());


        try {
            PreparedStatement p3 = connections.prepareStatement("insert into podcast(podCastName,releaseDate,duration,celebId)values(?,?,?,?)");
            p3.setString(1, podcast.getPodcastname());
            p3.setDate(2, podcast.getReleasedate());
            p3.setString(3,podcast.getDuration());
            p3.setInt(4,celebId);
            int row= p3.executeUpdate();
            if(row>0){
            System.out.println(TEXT_GREEN+"Insertion Done");
            output=true;}
            else {
            System.out.println("not Inserted");
            }
            } catch (SQLException e) {
            e.printStackTrace();
            }
            return output;
            }

    private int checkAndInsertCeleb(String celebName) {
        int id = 0;
        try {
            PreparedStatement p4 = connections.prepareStatement("select celebId from celeb where celebName=?");
            p4.setString(1, celebName);
            ResultSet r = p4.executeQuery();
            if(r.next()) {
            System.out.println(TEXT_PURPLE+"Celeb Found");
            System.out.println();
            id = r.getInt(1);
            }
            else
            {

            System.out.println(TEXT_RED +"Celeb not present.Inserting celeb into the table");
            System.out.println();
            System.out.print("please wait");
            System.out.print(TEXT_PURPLE+"..."+TEXT_RED+"..."+TEXT_GREEN+"...");
            System.out.println();
            id=insertArtist(celebName);
            }
            } catch (SQLException e) {
            e.printStackTrace();
            }
            return id;
            }
    private int insertArtist(String celebName) {
            int id = 0;
            try {
            PreparedStatement pcC = connections.prepareStatement("insert into celeb(celebName)values(?)",Statement.RETURN_GENERATED_KEYS);
            pcC.setString(1, celebName);
            int rowAffected = pcC.executeUpdate();
            if (rowAffected > 0) {
            System.out.println(TEXT_GREEN+"Celeb Creation Successful");
            System.out.println();
            ResultSet r = pcC.getGeneratedKeys();
            if (r.next()) {
            id = r.getInt(1);
            }
            }
            } catch (SQLException e) {
            e.printStackTrace();
            }
            return id;
    }
    ArrayList<podcast> podcastList(){
         ArrayList<podcast> podcastArrayList=new ArrayList<>();
        try {
            PreparedStatement pd= connections.prepareStatement("select*from podCaste_view");
            ResultSet rs= pd.executeQuery();
            while(rs.next()){
            podcastArrayList.add(new podcast(rs.getInt(1),rs.getString(2),rs.getDate(3),
            rs.getString(4),rs.getString(5)));
            }
            } catch (SQLException e) {
            e.printStackTrace();
            }
            return podcastArrayList;
            }
            }
