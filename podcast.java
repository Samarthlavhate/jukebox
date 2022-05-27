import java.sql.Date;

public class podcast {
    private int episode;
    private String podcastname;
    private Date releasedate;
    private String duration;
    private String celebname;


    public podcast(int episode, String podcastname, Date releasedate, String duration, String celebname) {
        this.episode = episode;
        this.podcastname = podcastname;
        this.releasedate = releasedate;
        this.duration = duration;
        this.celebname = celebname;
    }

    public podcast() {

    }

    public String getPodcastname() {
        return podcastname;
    }

    public void setPodcastname(String podcastname) {
        this.podcastname = podcastname;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public String getCelebname() {
        return celebname;
    }

    public void setCelebname(String celebname) {
        this.celebname = celebname;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    @Override
    public String toString() {
        return String.format("%4d\t %10s\t %12s\t %10s\t %13s\n",episode,podcastname,releasedate,duration,celebname);

    }
}