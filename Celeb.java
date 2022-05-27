public class Celeb {
   private   int celebid;
    private String celebname;

    public Celeb(int celebid, String celebname) {
        this.celebid = celebid;
        this.celebname = celebname;
    }

    public int getCelebid() {
        return celebid;
    }

    public void setCelebid(int celebid) {
        this.celebid = celebid;
    }

    public String getCelebname() {
        return celebname;
    }

    public void setCelebname(String celebname) {
        this.celebname = celebname;
    }

    @Override
    public String toString() {
        return "Celeb{" +
                "celebid=" + celebid +
                ", celebname='" + celebname + '\'' +
                '}';
    }
}
