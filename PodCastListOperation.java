import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class PodCastListOperation {
    ArrayList<podcast> podcast;
    ArrayList<podcast> filteredPodCastList;

    public PodCastListOperation(ArrayList<podcast> podcast) {
        this.podcast = podcast;
        this.filteredPodCastList = new ArrayList<>();
    }

    protected ArrayList<podcast> filterPodcastByName(String PodcastName){

        Optional<podcast> opt= podcast.stream().filter(podCast->podCast.getPodcastname().equalsIgnoreCase(PodcastName)).findAny();
        if(opt.isEmpty()){
        System.out.println("no podcast found");
        }
        else{
        filteredPodCastList = (ArrayList<podcast>) podcast.stream().filter(podCast->podCast.getPodcastname().equalsIgnoreCase(PodcastName)).collect(Collectors.toList());
        }
        return filteredPodCastList;
        }
   protected ArrayList<podcast> filterPodcastByCelebName(String CelebName){



        Optional<podcast> opt= podcast.stream().filter(podCast->podCast.getCelebname().equalsIgnoreCase(CelebName)).findAny();
        if(opt.isEmpty()){
        System.out.println("no podcast found");
        }
        else{
        filteredPodCastList = (ArrayList<podcast>) podcast.stream().filter(podCast->podCast.getPodcastname().equalsIgnoreCase(CelebName)).collect(Collectors.toList());
        }
        return filteredPodCastList;
        }
    protected ArrayList<podcast> filterPodcastByNameAndEpisode(String PodcastName ,int episode){


        Optional<podcast> opt= podcast.stream().filter(podCast->( podCast.getPodcastname().equalsIgnoreCase(PodcastName) &&
        podCast.getEpisode()==episode)).findAny();
        if(opt.isEmpty()){
        System.out.println("no podcast found");
        }
        else{
        filteredPodCastList = (ArrayList<podcast>) podcast.stream().filter(podCast->( podCast.getPodcastname().equalsIgnoreCase(PodcastName) &&
        podCast.getEpisode()==episode)).collect(Collectors.toList());
        }
        return filteredPodCastList;
        }}
