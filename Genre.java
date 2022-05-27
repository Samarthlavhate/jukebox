public class Genre {
   private String genrename;
    private int genreid;

    public Genre(String genrename, int genreid) {
        this.genrename = genrename;
        this.genreid = genreid;
    }

    public String getGenrename() {
        return genrename;
    }

    public void setGenrename(String genrename) {
        this.genrename = genrename;
    }

    public int getGenreid() {
        return genreid;
    }

    public void setGenreid(int genreid) {
        this.genreid = genreid;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genrename='" + genrename + '\'' +
                ", genreid=" + genreid +
                '}';
    }
}

