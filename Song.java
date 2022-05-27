public class Song {
    private int songid;
    private String songname;
    private String song_album;
    private String songduration;
    private String songgenre;
    private String songartist;

    public Song(int songid, String songname, String song_album, String songduration, String songgenre, String songartist) {
        this.songid = songid;
        this.songname = songname;
        this.song_album = song_album;
        this.songduration = songduration;
        this.songgenre = songgenre;
        this.songartist = songartist;
    }

    public Song() {

    }

    public int getSongid() {
        return songid;
    }

    public void setSongid(int songid) {
        this.songid = songid;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getSong_album() {
        return song_album;
    }

    public void setSong_album(String song_album) {
        this.song_album = song_album;
    }

    public String getSongduration() {
        return songduration;
    }

    public void setSongduration(String songduration) {
        this.songduration = songduration;
    }

    public String getSonggenre() {
        return songgenre;
    }

    public void setSonggenre(String songgenre) {
        this.songgenre = songgenre;
    }

    public String getSongartist() {
        return songartist;
    }

    public void setSongartist(String songartist) {
        this.songartist = songartist;
    }

    @Override
    public String toString() {
        return String.format("%10d\t %10s\t %10s\t %10s\t %10s\t %10s\n ", songid, songname, song_album, songduration, songgenre, songartist);
    }
}