import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class jukebox_main {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static void main(String[] args) throws SQLException {
        Scanner sc =new Scanner(System.in);
                SongDBOperations SongDBOperationsObject = new SongDBOperations();
                Song SongObject = new Song();
                PodCastDBOperation PodCastDBOperationObject = new PodCastDBOperation();
                podcast podcastObject = new podcast();
                PlayListDataClass PlayListData =new PlayListDataClass();
                PlayListDBOperations playListObject=new PlayListDBOperations();

                songListOperation SongListOperationObject =new songListOperation(SongDBOperationsObject.songList());
                PodCastListOperation PodCastListOperationObject =new PodCastListOperation(PodCastDBOperationObject.podcastList());
        System.out.println(ANSI_CYAN+"*"+ANSI_RESET+ANSI_YELLOW+"     ====================================================     "+ANSI_RESET+ANSI_CYAN+"*"+ANSI_RESET);
        System.out.println(ANSI_CYAN+"* *"+ANSI_RED+"   ||                                                ||  "+ANSI_CYAN+" * *");
        System.out.println(ANSI_CYAN+"* * *"+ANSI_RED+" ||                  "+ANSI_PURPLE+"|---------|"+ANSI_RESET+ANSI_RED+"                   || "+ANSI_CYAN+"* * *"+ANSI_RESET);
        System.out.println(ANSI_CYAN+"* * * *"+ANSI_GREEN+"==================="+ANSI_PURPLE+"|"+ANSI_YELLOW+"Sound Box"+ANSI_PURPLE+"|"+ANSI_GREEN+"===================="+ANSI_CYAN+"* * * *");
        System.out.println(ANSI_CYAN+"* * *"+ANSI_RED+" ||                  "+ANSI_PURPLE+"|---------|"+ANSI_RESET+ANSI_RED+"                   || "+ANSI_CYAN+"* * *"+ANSI_RESET);
        System.out.println(ANSI_CYAN+"* *"+ANSI_RED+"   ||                                                ||  "+ANSI_CYAN+" * *");
        System.out.println(ANSI_CYAN+"*"+ANSI_RESET+ANSI_YELLOW+"     ====================================================     "+ANSI_RESET+ANSI_CYAN+"*"+ANSI_RESET);
        char MainLoop='#';
        while(MainLoop=='#') {
            System.out.println(ANSI_CYAN + "==================== OPTIONS ====================");
            System.out.println(ANSI_YELLOW + "1: PlaySong\n2: Add-Search-Display Menu\n3: Playlist Menu\n4: Exit " + ANSI_RESET);
            System.out.println(ANSI_CYAN + "==================== Select any option ====================");

            // variable use for use of switch cases and looping statement;//
            int choice;
            int loopChoice = sc.nextInt();



            while (loopChoice == 2) {
                System.out.println(ANSI_CYAN + "==================== Song and PodCast Menu ====================");
                System.out.println(ANSI_YELLOW + "1: Add song\n2: Add podCast\n3: Search song\n4: Search podCast\n5: Display all songs\n6: Display all podcast \n7: exit" + ANSI_RESET);
                System.out.println(ANSI_CYAN + "==================== Select any option ====================");
                choice = sc.nextInt();

                switch (choice) {
                    case 1 -> {
                        do {
                            System.out.println(ANSI_CYAN + "==================== ADD SONG ====================");
                            System.out.println(ANSI_BLUE + "enter artist name" + ANSI_RESET);
                            String artistName = sc.next();
                            System.out.println(ANSI_BLUE + "enter genre" + ANSI_RESET);
                            String genre = sc.next();
                            SongObject.setSongartist(artistName);
                            SongObject.setSonggenre(genre);
                            System.out.println(ANSI_BLUE + "enter album name" + ANSI_RESET);
                            String album = sc.next();
                            SongObject.setSong_album(album);
                            System.out.println(ANSI_BLUE + "enter song name" + ANSI_RESET);
                            String songName = sc.next();
                            SongObject.setSongname(songName);
                            System.out.println(ANSI_BLUE + "enter duration" + ANSI_RESET);
                            String duration = sc.next();
                            SongObject.setSongduration(duration);
                            System.out.println(SongDBOperationsObject.addSong(SongObject));
                            System.out.println(ANSI_YELLOW + "1: add more song\n2: Add & Search menu\n0:EXIT" + ANSI_RESET);
                            loopChoice = sc.nextInt();
                        } while (loopChoice == 1);
                    }

                    case 2 -> {
                        do {
                            System.out.println(ANSI_CYAN + "==================== ADD PodCast ====================");
                            System.out.println(ANSI_BLUE + "enter celeb name" + ANSI_RESET);
                            String celebName = sc.next();
                            System.out.println(ANSI_BLUE + "podcast name" + ANSI_RESET);
                            String podCasTName = sc.next();
                            podcastObject.setPodcastname(podCasTName);
                            podcastObject.setCelebname(celebName);
                            System.out.println(ANSI_BLUE + "release date" + ANSI_RESET);
                            Date date = Date.valueOf(sc.next());
                            podcastObject.setReleasedate(date);
                            System.out.println(ANSI_BLUE + "enter duration" + ANSI_RESET);
                            String podDuration = sc.next();
                            podcastObject.setDuration(podDuration);
                            System.out.println(PodCastDBOperationObject.addPodcast(podcastObject));
                            System.out.println(ANSI_BLUE + "1: add more podcast\n2: Add and Search Menu\n0:Exit " + ANSI_RESET);
                            loopChoice = sc.nextInt();
                        } while (loopChoice == 1);
                    }
                    case 3 -> {
                        System.out.println(ANSI_CYAN + "==================== SEARCHING.... ====================");
                        System.out.println(ANSI_YELLOW + "1: search by song name\n2: search by album name \n3: search by genre \n4: search by Artist" + ANSI_RESET);
                        System.out.println(ANSI_CYAN + "==================== Select any option ====================");
                        choice = sc.nextInt();
                        switch (choice) {
                            case 1 -> {
                                System.out.println(ANSI_BLUE + "enter song name" + ANSI_RESET);
                                String songName = sc.next();
                                System.out.println();
                                System.out.println(ANSI_RED + "SONG TABLE->" + ANSI_RESET);
                                System.out.printf(ANSI_PURPLE + " %10s\t%11s\t%10s\t%12s\t%9s\t%7s%n", "SongID", "SongName", "Album", "Duration", "Genre", "Artist" + ANSI_RESET);
                                SongListOperationObject.filterSongByName(songName).forEach(System.out::println);
                                System.out.println(ANSI_CYAN + "-------Search Completed------- ");
                                System.out.println(ANSI_YELLOW + "0:Exit\n2:Add and search Menu" + ANSI_RESET);
                                loopChoice = sc.nextInt();
                            }

                            case 2 -> {
                                System.out.println(ANSI_BLUE + "enter album name" + ANSI_RESET);
                                String songAlbum = sc.next();
                                System.out.println();
                                System.out.println(ANSI_RED + "SONG TABLE->" + ANSI_RESET);
                                System.out.printf(ANSI_PURPLE + " %10s\t%11s\t%10s\t%12s\t%9s\t%7s%n", "SongID", "SongName", "Album", "Duration", "Genre", "Artist" + ANSI_RESET);
                                SongListOperationObject.filterSongByAlbum(songAlbum).forEach(System.out::println);
                                System.out.println(ANSI_CYAN + "-------Search Completed------- ");
                                System.out.println(ANSI_YELLOW + "0:Exit\n2:Add and search Menu" + ANSI_RESET);
                                loopChoice = sc.nextInt();
                            }

                            case 3 -> {
                                System.out.println(ANSI_BLUE + "enter genre type" + ANSI_RESET);
                                String songGenre = sc.next();
                                System.out.println();
                                System.out.println(ANSI_RED + "SONG TABLE->" + ANSI_RESET);
                                System.out.printf(ANSI_PURPLE + " %10s\t%11s\t%10s\t%12s\t%9s\t%7s%n", "SongID", "SongName", "Album", "Duration", "Genre", "Artist" + ANSI_RESET);
                                SongListOperationObject.filterSongByGenre(songGenre).forEach(System.out::println);
                                System.out.println(ANSI_CYAN + "-------Search Completed------- ");
                                System.out.println(ANSI_YELLOW + "0:Exit\n2:Add and search Menu" + ANSI_RESET);
                                loopChoice = sc.nextInt();
                            }
                            case 4 -> {
                                System.out.println(ANSI_BLUE + "enter Artist name" + ANSI_RESET);
                                String songArtist = sc.next();
                                System.out.println();
                                System.out.println(ANSI_RED + "SONG TABLE->" + ANSI_RESET);
                                System.out.printf(ANSI_PURPLE + " %10s\t%11s\t%10s\t%12s\t%9s\t%7s%n", "SongID", "SongName", "Album", "Duration", "Genre", "Artist" + ANSI_RESET);
                                SongListOperationObject.filterSongByArtist(songArtist).forEach(System.out::println);
                                System.out.println(ANSI_CYAN + "-------Search Completed------- ");
                                System.out.println(ANSI_YELLOW + "0:Exit\n2:Add and search Menu" + ANSI_RESET);
                                loopChoice = sc.nextInt();

                            }
                        }
                    }
                    case 4 -> {
                        System.out.println(ANSI_CYAN + "==================== SEARCHING.... ====================");
                        System.out.println(ANSI_YELLOW + "1: search podcast by name\n2: search podcast by celeb name\n3: search podcast by episode" + ANSI_RESET);
                        System.out.println(ANSI_CYAN + "==================== Select any option ====================");

                        choice = sc.nextInt();
                        switch (choice) {
                            case 1 -> {
                                System.out.println(ANSI_BLUE + "enter podcast name" + ANSI_RESET);
                                String PodcastName = sc.next();
                                System.out.println();
                                System.out.println(ANSI_RED + "PodCast TABLE->" + ANSI_RESET);
                                System.out.printf(ANSI_PURPLE + "%5s\t%14s\t%6s\t%18s\t%11s%n", "Episode", "PodcastName", "Date", "Duration", "CelebName" + ANSI_RESET);
                                PodCastListOperationObject.filterPodcastByName(PodcastName).forEach(System.out::println);
                                System.out.println(ANSI_CYAN + "-------Search Completed------- ");
                                System.out.println(ANSI_YELLOW + "0:Exit\n2:Add and search Menu" + ANSI_RESET);
                                loopChoice = sc.nextInt();

                            }
                            case 2 -> {
                                System.out.println(ANSI_BLUE + "enter Celeb Name" + ANSI_RESET);
                                String CelebName = sc.next();
                                System.out.println();
                                System.out.println(ANSI_RED + "PodCast TABLE->");
                                System.out.printf(ANSI_PURPLE + "%5s\t%14s\t%6s\t%18s\t%11s%n", "Episode", "PodcastName", "Date", "Duration", "CelebName" + ANSI_RESET);
                                PodCastListOperationObject.filterPodcastByCelebName(CelebName).forEach(System.out::println);
                                System.out.println(ANSI_CYAN + "-------Search Completed------- ");
                                System.out.println(ANSI_YELLOW + "0:Exit\n2:Add and search Menu" + ANSI_RESET);
                                loopChoice = sc.nextInt();

                            }
                            case 3 -> {
                                System.out.println(ANSI_BLUE + "enter podcast name" + ANSI_RESET);
                                String PodcastName = sc.next();
                                System.out.println(ANSI_BLUE + "enter episode" + ANSI_RESET);
                                int episode = sc.nextInt();
                                System.out.println(ANSI_RED + "PodCast TABLE->" + ANSI_RESET);
                                System.out.println();
                                System.out.printf(ANSI_PURPLE + "%5s\t%14s\t%6s\t%18s\t%11s%n", "Episode", "PodcastName", "Date", "Duration", "CelebName" + ANSI_RESET);
                                PodCastListOperationObject.filterPodcastByNameAndEpisode(PodcastName, episode).forEach(System.out::println);
                                System.out.println(ANSI_CYAN + "-------Search Completed------- ");
                                System.out.println(ANSI_YELLOW + "0:Exit\n2:Add and search Menu" + ANSI_RESET);
                                loopChoice = sc.nextInt();

                            }

                        }
                    }

                    case 7 -> {
                        System.out.println("Are you sure want to exit");
                        System.out.println(ANSI_YELLOW + "0: Confirm\n2: Continue with add search menu" + ANSI_RESET);
                        loopChoice = sc.nextInt();
                        System.out.println(ANSI_CYAN + "-------------------------------------------- "+ANSI_RESET);
                    }
                    case 6->{
                        System.out.println(ANSI_RED + "Podcast list------->>> "+ANSI_RESET);
                        System.out.printf(ANSI_PURPLE + "%s\t%14s\t%6s\t%18s\t%13s %n", "Episode", "PodcastName", "Date", "Duration", "CelebName" + ANSI_RESET);
                        PodCastDBOperationObject.podcastList().forEach(name-> System.out.print("---------------------------------------------------------------\n"+name));
                        System.out.println(ANSI_CYAN + "--------------------------------------------------------------- "+ANSI_RESET);
                        System.out.println(ANSI_YELLOW + "0:Exit\n2: Continue with Add and search Menu" + ANSI_RESET);
                        loopChoice = sc.nextInt();
                        System.out.println(ANSI_CYAN + "-------------------------------------------- "+ANSI_RESET);
                    }
                    case 5->{
                        System.out.println(ANSI_RED + "Song list------->>> "+ANSI_RESET);
                        System.out.printf(ANSI_PURPLE + " %10s\t%11s\t%10s\t%12s\t%9s\t%7s %n", "SongID", "SongName", "Album", "Duration", "Genre", "Artist" + ANSI_RESET);
                        SongDBOperationsObject.songList().forEach(name-> System.out.print("-----------------------------------------------------------------------\n"+name));
                        System.out.println(ANSI_CYAN + "----------------------------------------------------------------------- "+ANSI_RESET);
                        System.out.println(ANSI_YELLOW + "0:Exit\n2: Continue with Add and search Menu" + ANSI_RESET);
                        loopChoice = sc.nextInt();
                        System.out.println(ANSI_CYAN + "-------------------------------------------- "+ANSI_RESET);
                    }
                }
            }


            ///playlist part start..


            while (loopChoice == 3) {
                System.out.println(ANSI_CYAN + "==================== Playlist Menu ====================");
                System.out.println(ANSI_YELLOW + "1: Create And Song or Podcast into playlist\n2: Display the playlist" + ANSI_RESET);
                System.out.println(ANSI_CYAN + "==================== Select any option ====================");
                choice = sc.nextInt();
                switch (choice) {

                    case 1 -> {
                        System.out.println(ANSI_BLUE + "enter playlist Name" + ANSI_RESET);
                        String playListName = sc.next();
                        PlayListData.setPlaylistName(playListName);
                        System.out.println(ANSI_CYAN + "==================== Operation Complete ====================");

                        System.out.println(ANSI_YELLOW + "1:Add a song into the playlist by song Name\n2: Add a podcast in a playlist by podcastName\n3:Add a song into the playlist by song albumNAME\n4: Add a podcast in a playlist by celebName" + ANSI_RESET);
                        do {
                            System.out.println(ANSI_BLUE + "  enter your choice" + ANSI_RESET);
                            choice = sc.nextInt();
                            switch (choice) {

                                case 1 -> {
                                    System.out.println(ANSI_BLUE + "enter song name" + ANSI_RESET);
                                    String songName = sc.next();
                                    int TrackId = 0;
                                    try {
                                    TrackId = playListObject.songId(songName);
                                    } catch (Exception e) {
                                      e.printStackTrace();
                                    }
                                    if(TrackId!=0){
                                    PlayListData.setTrackId(TrackId);
                                    playListObject.addPlayList(playListName, TrackId);
                                    System.out.println(ANSI_GREEN + "found");}
                                    System.out.println(ANSI_CYAN + "==================== Operation Complete ====================");


                                }
                                case 2 -> {
                                    System.out.println(ANSI_BLUE + "enter podcast name" + ANSI_RESET);
                                    String podcastName = sc.next();
                                    int TrackId = 0;
                                    try {
                                        TrackId = playListObject.PodCastId(podcastName);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }if(TrackId!=0){
                                    PlayListData.setTrackId(TrackId);
                                    playListObject.addPlayList(playListName, TrackId);
                                    System.out.println(ANSI_GREEN + "found");}
                                    System.out.println(ANSI_CYAN + "==================== Operation Complete ====================");

                                }
                                case 3 -> {
                                    System.out.println(ANSI_BLUE + "enter song album name" + ANSI_RESET);
                                    String SongAlbumName = sc.next();
                                    int TrackId = 0;
                                    try {
                                        TrackId = playListObject.songIdByAlbumName(SongAlbumName);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }if(TrackId!=0){
                                    PlayListData.setTrackId(TrackId);
                                    playListObject.addPlayList(playListName, TrackId);
                                    System.out.println(ANSI_GREEN + "found");}
                                    System.out.println(ANSI_CYAN + "==================== Operation Complete ====================");

                                }
                                case 4 -> {
                                    System.out.println(ANSI_BLUE + "enter celeb Name" + ANSI_RESET);
                                    String CelebName = sc.next();
                                    int TrackId = 0;
                                    try {
                                        TrackId = playListObject.PodCastIdByCelebName(CelebName);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }if(TrackId!=0){
                                    PlayListData.setTrackId(TrackId);
                                    playListObject.addPlayList(playListName, TrackId);
                                    System.out.println(ANSI_GREEN + "found");}
                                    System.out.println(ANSI_CYAN + "==================== Operation Complete ====================");

                                }
                            }
                            System.out.println(ANSI_YELLOW + "1: Add More Song\n2: Exit" + ANSI_RESET);
                            loopChoice = sc.nextInt();
                        } while (loopChoice == 1);
                    }


                    case 2 -> {

                        System.out.println(ANSI_GREEN + "_____________________________________________________________"+ANSI_RESET);
                        System.out.println(ANSI_BLUE +"playlist Available-->"+ANSI_RESET);
                        playListObject.playlistAvailable();
                        System.out.println(ANSI_GREEN + "_____________________________________________________________"+ANSI_RESET);
                        System.out.println(ANSI_BLUE + "enter playlist name" + ANSI_RESET);
                        String playlistName = sc.next();
                        System.out.println(ANSI_GREEN + "_____________________________________________________________"+ANSI_RESET);
                        System.out.println(ANSI_RED + "Playlist details->" + ANSI_RESET);
                        System.out.printf(ANSI_PURPLE + "%10s\t%9s\n", "SongName", "Duration" + ANSI_RESET);
                        playListObject.displayPlaylist(playlistName);
                        System.out.println(ANSI_GREEN + "_____________________________________________________________"+ANSI_RESET);

                    }
                }
                System.out.println();
                System.out.println(ANSI_YELLOW + "0: exit Playlist menu\n3: Continue in Playlist Menu" + ANSI_RESET);
                loopChoice = sc.nextInt();
            }

            while (loopChoice == 1) {
                System.out.println(ANSI_GREEN + "_____________________________________________________________" + ANSI_RESET);
                System.out.println("| " + ANSI_PURPLE + "****      ****" + ANSI_RESET + " |" + ANSI_RED + " **     ** " + ANSI_RESET + "|" + ANSI_YELLOW + " ******* " + ANSI_RESET + "|" + ANSI_CYAN + " ******* " + ANSI_RESET + "|" + ANSI_GREEN + " ******** " + ANSI_RESET + "|");
                System.out.println("| " + ANSI_PURPLE + "**  **  **  **" + ANSI_RESET + " |" + ANSI_RED + " **     ** " + ANSI_RESET + "|" + ANSI_YELLOW + " *       " + ANSI_RESET + "|" + ANSI_CYAN + "    *    " + ANSI_RESET + "|" + ANSI_GREEN + " *        " + ANSI_RESET + "|");
                System.out.println("| " + ANSI_PURPLE + "**    **    **" + ANSI_RESET + " |" + ANSI_RED + " **     ** " + ANSI_RESET + "|" + ANSI_YELLOW + " ******* " + ANSI_RESET + "|" + ANSI_CYAN + "    *    " + ANSI_RESET + "|" + ANSI_GREEN + " *        " + ANSI_RESET + "|");
                System.out.println("| " + ANSI_PURPLE + "**          **" + ANSI_RESET + " |" + ANSI_RED + " **     ** " + ANSI_RESET + "|" + ANSI_YELLOW + "       * " + ANSI_RESET + "|" + ANSI_CYAN + "    *    " + ANSI_RESET + "|" + ANSI_GREEN + " *        " + ANSI_RESET + "|");
                System.out.println("| " + ANSI_PURPLE + "**          **" + ANSI_RESET + " |" + ANSI_RED + " ********* " + ANSI_RESET + "|" + ANSI_YELLOW + " ******* " + ANSI_RESET + "|" + ANSI_CYAN + " ******* " + ANSI_RESET + "|" + ANSI_GREEN + " ******** " + ANSI_RESET + "|");
                System.out.println(ANSI_GREEN + "_____________________________________________________________");


                System.out.println(ANSI_BLUE +"playlist Available"+ANSI_RESET);
                System.out.println(ANSI_GREEN + "_____________________________________________________________"+ANSI_RESET);
                 playListObject.playlistAvailable();
                System.out.println();
                System.out.println(ANSI_CYAN + "=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=" + ANSI_RESET);

                System.out.println(ANSI_BLUE +"playlist choice");
                System.out.println(ANSI_CYAN + "=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=");
                String playListSong;
                playListSong = sc.next();

                System.out.println(ANSI_CYAN + "=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=");
                if (playListSong.equalsIgnoreCase("motivation")) {

                ResultSet rp= playListObject.trackId(playListSong);
                int newPlay=0;
                   while(rp.next()&&loopChoice!=2 &&newPlay!=1) {


                 //copy previous part here
                        try {
                            File file = new File("src/"+rp.getInt(1)+".wav");
                            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                            Clip clip = AudioSystem.getClip();
                            clip.open(audioInputStream);
                            System.out.println(ANSI_RED + "Response->" + ANSI_RESET);
                            System.out.println(ANSI_YELLOW + "P: play\nS: stop \nR: reset\nN: next song\nQ: quit" + ANSI_RESET);
                            System.out.println(ANSI_CYAN + "=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=");
                            String response = "p";


                            A:
                            while (!response.equals("")) {

                                switch (response) {

                                    case "p" -> {
                                        System.out.println(ANSI_CYAN + "=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=");
                                        clip.start();
                                        AudioFormat format = clip.getFormat();
                                        long frames = clip.getFrameLength();
                                        double durationInSeconds = (frames + 0.0) / format.getFrameRate();
                                        int p1 = (int) durationInSeconds;
                                        int p3 = p1 % 60;
                                        int p2 = p1 / 60;
                                        int x = p2 % 60;
                                        System.out.printf(ANSI_RED + "%6s\t%12s\t%26s\n", "status", "SongName", "Duration" + ANSI_RESET);
                                        //

                                        //

                                          System.out.printf("%s %s -%23s ","playing->  ",playListObject.songNameHAI(rp.getInt(1)), "  |  " + x+ ":"+ p3+"\n"); //name// );




                                        System.out.println(ANSI_CYAN + "=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=" + ANSI_RESET);
                                        for (int i = 0; i < 5; i++) {
                                            System.out.printf("%20s", "");
                                            for (int j = 0; j <= i; j++) {
                                                System.out.print("# ");

                                            }
                                            System.out.println();
                                        }
                                        for (int k = 5; k > 0; k--) {
                                            System.out.printf("%20s", "");
                                            for (int l = 1; l < k && l != 5; l++) {
                                                System.out.print("# ");
                                            }
                                            System.out.println();
                                        }

                                        System.out.println(ANSI_CYAN + "=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=");
                                    }
                                    case "s" -> {
                                        clip.stop();
                                        System.out.println(ANSI_CYAN + "=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=" + ANSI_RESET);
                                        System.out.println("Song is paused");
                                        System.out.println(ANSI_CYAN + "=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=" + ANSI_RESET);
                                        System.out.println();
                                        for (int i = 0; i < 5; i++) {
                                            System.out.printf("%25s%n", "||     ||");
                                        }
                                        System.out.println();
                                        System.out.println(ANSI_CYAN + "=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=" + ANSI_RESET);
                                    }
                                    case "r" -> {
                                        System.out.println(ANSI_CYAN + "=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=" + ANSI_RESET);
                                        System.out.println("Reverse and  Forward");
                                        System.out.println(ANSI_CYAN + "=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=" + ANSI_RESET);

                                        ///===========================
                                        System.out.printf("%30s%n", "   #    #    ||    #    #");
                                        System.out.printf("%31s%n", "  ##   ##    ||    ##   ##");
                                        System.out.printf("%32s%n", " ###  ###    ||    ###  ###");
                                        System.out.printf("%33s%n", "#### ####    ||    #### ####");
                                        System.out.printf("%32s%n", " ###  ###    ||    ###  ###");
                                        System.out.printf("%31s%n", "  ##   ##    ||    ##   ##");
                                        System.out.printf("%30s%n", "   #    #    ||    #    #");
                                        System.out.println(ANSI_CYAN + "=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=" + ANSI_RESET);
                                        ///===========================

                                        System.out.print(ANSI_BLUE + "Enter seconds -> " + ANSI_RESET);
                                        long RF = sc.nextInt();
                                        long second = 1000000;
                                        clip.setMicrosecondPosition((RF) * (second));

                                        System.out.println(ANSI_CYAN + "=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=" + ANSI_RESET);
                                    }
                                    case "q" -> {
                                        clip.close();
                                        System.out.println("player stopped");
                                        System.out.println(ANSI_CYAN + "=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=" + ANSI_RESET);
                                        break A;

                                    }
                                    case "n"->{
                                        clip.close();
                                        System.out.println(ANSI_CYAN + "=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=" + ANSI_RESET);
                                        loopChoice=1;
                                        break A;
                                    }


                                }
                                System.out.print(ANSI_BLUE + "WHAT TO DO-> " + ANSI_RESET);
                                response = sc.next();
                            }
                            if(!response.equals("n")) {
                                System.out.println(ANSI_YELLOW + "1: play another playlist\n2: exit player " + ANSI_RESET);
                                System.out.println(ANSI_CYAN + "=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=" + ANSI_RESET);
                                loopChoice = sc.nextInt();
                                newPlay=loopChoice;
                            }

                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                            System.err.println("Invalid Input/Try again.....");
                        }
                    }
                }
                /////////////////////////////
                else {
                    System.out.println(ANSI_CYAN + "=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=" + ANSI_RESET);
                    System.err.println("playlist not found");
                    System.out.println(ANSI_YELLOW + "1: try again \n2: go back to main menu to create playlist " + ANSI_RESET);
                    System.out.println(ANSI_CYAN + "=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=" + ANSI_RESET);
                    loopChoice = sc.nextInt();
                }
            }


            System.out.println(ANSI_CYAN + "=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "Are you sure want to exit" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "#: Go to Main Menu\n1: End the program" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=" + ANSI_RESET);
            MainLoop = sc.next().charAt(0);
            System.out.println(ANSI_CYAN + "=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=#=" + ANSI_RESET);
            if (MainLoop == '1') {

                System.out.println(ANSI_YELLOW+"   ******************    "+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"  ***"+ANSI_RED+"     ****"+ANSI_RESET+ANSI_YELLOW+"     ***   ");
                System.out.println(" ***   "+ANSI_RED+"  *    *"+ANSI_RESET+ANSI_YELLOW+"     *** ");
                System.out.println("***      "+ANSI_RED+" ****"+ANSI_RESET+ANSI_YELLOW+"       ***"+ANSI_RESET);
                System.out.println(ANSI_PURPLE+"_________________________");
                System.out.println("_______"+ANSI_YELLOW+"|"+ANSI_RESET+ANSI_PURPLE+"_________"+ANSI_YELLOW+"|"+ANSI_PURPLE+"_______"+ANSI_RESET);
                System.out.println(ANSI_CYAN+"|||"+ANSI_RESET+"     "+ANSI_GREEN+"__________    "+ANSI_CYAN+"|||");
                System.out.println(ANSI_BLUE+"|||"+ANSI_RESET+"     "+ANSI_GREEN+"|"+ANSI_RESET+"  BYE   "+ANSI_GREEN+"|    "+ANSI_BLUE+"|||"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"___"+ANSI_RESET+"    "+ANSI_GREEN+" |"+ANSI_RESET+"  BACK  "+ANSI_GREEN+"|    "+ANSI_YELLOW+"___");
                System.out.println(ANSI_BLUE+"| |"+ANSI_RESET+"     "+ANSI_GREEN+"|__"+ANSI_RESET+"SOON"+ANSI_GREEN+"__|    "+ANSI_BLUE+"| |");
                System.out.println(ANSI_CYAN+"| |"+ANSI_RESET+ANSI_PURPLE+"___________________"+ANSI_CYAN+"| |");

            }

        }

        }
    }






