import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class songListOperation {

    ArrayList<Song> songs ;
    ArrayList<Song> filteredSongList;

    public songListOperation(ArrayList<Song> songs) {
        this.songs = songs;
        this.filteredSongList = new ArrayList<>();
    }

    protected ArrayList<Song> filterSongByName(String songName){



        Optional<Song> opt= songs.stream().filter(song->song.getSongname().equalsIgnoreCase(songName)).findAny();
        if(opt.isEmpty()){
        System.out.println("no song found");
        }
        else{
        filteredSongList = (ArrayList<Song>) songs.stream().filter(song->song.getSongname().equalsIgnoreCase(songName)).collect(Collectors.toList());
        }
        return filteredSongList;
        }

    protected ArrayList<Song> filterSongByAlbum(String songAlbum){

        Optional<Song> opt= songs.stream().filter(song->song.getSong_album().equalsIgnoreCase(songAlbum)).findAny();
        if(opt.isEmpty()){
        System.out.println("no song found");
        }
        else{
        filteredSongList = (ArrayList<Song>) songs.stream().filter(song->song.getSongname().equalsIgnoreCase(songAlbum)).collect(Collectors.toList());
        }
        return filteredSongList;
        }


    protected ArrayList<Song> filterSongByGenre(String songGenre){

        Optional<Song> opt= songs.stream().filter(song->song.getSonggenre().equalsIgnoreCase(songGenre)).findAny();
        if(opt.isEmpty()){
        System.out.println("no song found");
        }
        else{
        filteredSongList = (ArrayList<Song>) songs.stream().filter(song->song.getSongname().equalsIgnoreCase(songGenre)).collect(Collectors.toList());
        }
        return filteredSongList;
        }
    protected ArrayList<Song> filterSongByArtist(String songArtist){

        Optional<Song> opt= songs.stream().filter(song->song.getSong_album().equalsIgnoreCase(songArtist)).findAny();
        if(opt.isEmpty()){
        System.out.println("no song found");
        }
        else{
        filteredSongList = (ArrayList<Song>) songs.stream().filter(song->song.getSongname().equalsIgnoreCase(songArtist)).collect(Collectors.toList());
        }
        return filteredSongList;
        }
        }
